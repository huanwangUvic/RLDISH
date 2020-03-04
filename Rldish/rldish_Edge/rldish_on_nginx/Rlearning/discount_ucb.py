# -*- coding: utf-8 -*-
r""" The Discounted-UCB index policy, with a discount factor of :math:`\gamma\in(0,1]`.

- Reference: ["On Upper-Confidence Bound Policies for Non-Stationary Bandit Problems", by A.Garivier & E.Moulines, ALT 2011](https://arxiv.org/pdf/0805.3415.pdf)
- :math:`\gamma` should not be 1, otherwise you should rather use :class:`Policies.UCBalpha.UCBalpha` instead.
- The smaller the :math:`\gamma`, the shorter the *"memory"* of the algorithm is.
"""
from __future__ import division, print_function  # Python 2 compatibility

from math import sqrt, log
import numpy as np 

#np.seterr(divide='ignore')  # XXX dangerous in general, controlled here!

#: Default parameter for alpha.
ALPHA = 0.1

#: Default parameter for gamma.
GAMMA = 0.98
upper_bound_reward = 0.9


class DiscountedUCB():
    r""" The Discounted-UCB index policy, with a discount factor of :math:`\gamma\in(0,1]`.

    - Reference: ["On Upper-Confidence Bound Policies for Non-Stationary Bandit Problems", by A.Garivier & E.Moulines, ALT 2011](https://arxiv.org/pdf/0805.3415.pdf)
    """

    def __init__(self, nbArms,
                 alpha=ALPHA, gamma=GAMMA,
                 useRealDiscount=True,
                 *args, **kwargs):

        self.nbArms = nbArms
        self.discounted_pulls = np.zeros(nbArms, dtype=float)  #: Number of pulls of each arms
        self.discounted_rewards = np.zeros(nbArms)  #: Cumulated rewards of each arms
        assert alpha >= 0, "Error: the 'alpha' parameter for DiscountedUCB class has to be >= 0."  # DEBUG
        self.alpha = alpha  #: Parameter alpha
        assert 0 < gamma <= 1, "Error: the 'gamma' parameter for DiscountedUCB class has to be 0 < gamma <= 1."  # DEBUG
        if np.isclose(gamma, 1):
            print("Warning: using DiscountedUCB with 'gamma' too close to 1 will result in UCBalpha, you should rather use it...")  # DEBUG
        self.gamma = gamma  #: Parameter gamma
        self.delta_time_steps = np.zeros(self.nbArms, dtype=int)  #: Keep memory of the :math:`\Delta_k(t)` for each time step.
        self.useRealDiscount = useRealDiscount  #: Flag to know if the real update should be used, the one with a multiplication by :math:`\gamma^{1+\Delta_k(t)}` and not simply a multiplication by :math:`\gamma`.
        self.index = []
        self.t = 0
        
    def getReward(self, arm, reward):
        r""" Give a reward: increase t, pulls, and update cumulated sum of rewards for that arm (normalized in [0, 1]).

        - Keep up-to date the following two quantities, using different definition and notation as from the article, but being consistent w.r.t. my project:

        .. math::

            N_{k,\gamma}(t+1) &:= \sum_{s=1}^{t} \gamma^{t - s} N_k(s), \\
            X_{k,\gamma}(t+1) &:= \sum_{s=1}^{t} \gamma^{t - s} X_k(s).

        - Instead of keeping the whole history of rewards, as expressed in the math formula, we keep the sum of discounted rewards from ``s=0`` to ``s=t``, because updating it is easy (2 operations instead of just 1 for classical :class:`Policies.UCBalpha.UCBalpha`, and 2 operations instead of :math:`\mathcal{O}(t)` as expressed mathematically). Denote :math:`\Delta_k(t)` the number of time steps during which the arm ``k`` was *not* selected (maybe 0 if it is selected twice in a row). Then the update can be done easily by multiplying by :math:`\gamma^{1+\Delta_k(t)}`:

        .. math::

            N_{k,\gamma}(t+1) &= \gamma^{1+\Delta_k(t)} \times N_{k,\gamma}(\text{last pull}) + \mathbb{1}(A(t+1) = k), \\
             .
        """
        self.t += 1
        self.discounted_pulls[arm] = ((self.gamma ** (1 + self.delta_time_steps[arm])) * self.discounted_pulls[arm]) + 1
        # XXX self.discounted_pulls[arm] += 1  # if we were using N_k(t) and not N_{k,gamma}(t).
        # reward = (reward - self.lower) / self.amplitude
        self.discounted_rewards[arm] = ((self.gamma ** (1 + self.delta_time_steps[arm])) * self.discounted_rewards[arm]) + reward
        # Ok and we saw this arm so no delta now
        if self.useRealDiscount:
            self.delta_time_steps += 1  # increase delay for each algorithms
            self.delta_time_steps[arm] = 0
    
    def getReward2(self, arm, reward):
    
        self.t += 1
        self.discounted_pulls = self.discounted_pulls * self.gamma
        self.discounted_pulls[arm] = self.discounted_pulls[arm] + 1
        
        self.discounted_rewards = self.discounted_rewards * self.gamma
        self.discounted_rewards[arm] = self.discounted_rewards[arm] + reward 

    def computeIndex(self, arm):
        r""" Compute the current index, at time :math:`t` and after :math:`N_{k,\gamma}(t)` *"discounted"* pulls of arm k, and :math:`n_{\gamma}(t)` *"discounted"* pulls of all arms:

        .. math::

            I_k(t) &:= \frac{X_{k,\gamma}(t)}{N_{k,\gamma}(t)} + \sqrt{\frac{\alpha \log(n_{\gamma}(t))}{2 N_{k,\gamma}(t)}}, \\
            \text{where}\;\; n_{\gamma}(t) &:= \sum_{k=1}^{K} N_{k,\gamma}(t).
        """
        if self.discounted_pulls[arm] < 1:
            return float('+inf')
        else:
            n_t_gamma = np.sum(self.discounted_pulls)
            assert n_t_gamma <= self.t, "Error: n_t_gamma was computed as {:.3g} but should be < t = {:.3g}...".format(n_t_gamma, self.t)  # DEBUG
            return (self.discounted_rewards[arm] / self.discounted_pulls[arm]) + sqrt((self.alpha * log(n_t_gamma)) / (2 * self.discounted_pulls[arm]))

    def computeAllIndex(self):
        """ Compute the current indexes for all arms, in a vectorized manner."""
        n_t_gamma = np.sum(self.discounted_pulls)
        assert n_t_gamma <= self.t, "Error: n_t_gamma was computed as {:.3g} but should be < t = {:.3g}...".format(n_t_gamma, self.t)  # DEBUG
        padding_func = 2 * upper_bound_reward * np.sqrt((self.alpha * np.log(n_t_gamma)) / self.discounted_pulls)
        indexes = self.discounted_rewards / self.discounted_pulls + padding_func
        #indexes[self.discounted_pulls < 1] = float('+inf')
        self.index[:] = indexes


if __name__ == "__main__":
    
    horizon = 5000
    list_of_means = [0.4, 0.6, 0.9]
    
    s0 = np.random.uniform(0.1, 0.3, horizon)
    s1 = np.random.uniform(0.4, 0.7, horizon)
    s2 = np.random.uniform(0.9, 0.95, horizon)
    rewardList = [s0, s1, s2]
    
    ducb = DiscountedUCB(nbArms = 3)

    for t in range(horizon):
        if t < 3:
            ducb.getReward2(t, rewardList[t][t])
            ducb.computeAllIndex()
        else:
            cur_choice = np.argmax(ducb.index)
            print(cur_choice)
            ducb.getReward2(cur_choice, rewardList[cur_choice][t])
            ducb.computeAllIndex()
    