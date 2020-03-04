package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.OperecordDAO;
import com.dao.ResproDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.OperecordDAOImpl;
import com.dao.impl.ResproDAOImpl;
import com.domain.Department;
import com.domain.Operecord;
import com.domain.Respro;

public class UpdateproServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateproServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		int proid=0;
		try{
			proid=Integer.parseInt(id);
		}catch(Exception e)
		{}
		Respro pro = new Respro();
		ResproDAO dao = new ResproDAOImpl();
		pro = dao.getpro(proid);
		int depnum = pro.getProdep();
		DepartmentDAO dao3 = new DepartmentDAOImpl();
		String name = dao3.getdep(depnum).getDepname();
		String sql = "select * from department where deprole=2 ";
		ArrayList<Department> list = new ArrayList<Department>();
		DepartmentDAO dao2 = new DepartmentDAOImpl();
		list = dao2.getAlldep(sql); 
		request.setAttribute("list", list);
		request.setAttribute("pro", pro);
		request.setAttribute("name", name);
		request.getRequestDispatcher("/respage/updatepro.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String proname = request.getParameter("proname");
		String protype = request.getParameter("protype");
		String apptime = request.getParameter("apptime");
		String probatch = request.getParameter("probatch");
		String pronum = request.getParameter("pronum");
		String planmoney = request.getParameter("planmoney");
		String resman = request.getParameter("resman");
		String content = request.getParameter("content");
		int proid = 0;
		int type = 0;
		int money = 0;
		int res = 0;
		try{
			proid = Integer.parseInt(id);
			type = Integer.parseInt(protype);
			money = Integer.parseInt(planmoney);
			res = Integer.parseInt(resman);
			
		}
		catch (Exception e)
		{}
		Respro pro = new Respro();
		pro.setProname(proname);
		pro.setProtype(type);
		pro.setApptime(apptime);
		pro.setProbatch(probatch);
		pro.setProid(proid);
		pro.setPronum(pronum);
		pro.setPlanmoney(money);
		pro.setResman(res);
		pro.setProexplain(content);
		
		ResproDAO dao = new ResproDAOImpl();
		
		pro.setProdep(dao.getpro(proid).getProdep());
		pro.setNowMoney(dao.getpro(proid).getNowMoney());
		pro.setPlanCon(dao.getpro(proid).getPlanCon());
		pro.setProCon(dao.getpro(proid).getProCon());
		pro.setProkapp(dao.getpro(proid).getProkapp());
		pro.setTaskingid(dao.getpro(proid).getTaskingid());
		dao.updatepro(pro);
		
		/**
		 * 以下添加操作日志
		 */
		Operecord ope = new Operecord();
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("修改储备项目信息");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(3);
	    OperecordDAO dao3  = new OperecordDAOImpl();
	    dao3.addope(ope);
		
		request.setAttribute("erroinfo", "更新项目信息成功！");
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
