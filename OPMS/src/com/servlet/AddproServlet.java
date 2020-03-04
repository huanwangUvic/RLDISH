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

public class AddproServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddproServlet() {
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
		String sql = "select * from department where deprole=2 ";
		ArrayList<Department> list = new ArrayList<Department>();
		DepartmentDAO dao = new DepartmentDAOImpl();
		list = dao.getAlldep(sql);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/respage/addpro.jsp").forward(request, response);
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
		String proname = request.getParameter("proname");
		String protype = request.getParameter("protype");
		String apptime = request.getParameter("apptime");
		String planmoney = request.getParameter("planmoney");
		String resman = request.getParameter("resman");
		String explain = request.getParameter("content");
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		int depnum = dep.getDepnum();
		int type=0;
		int money = 0;
		int res=0;
		try{ 
			type = Integer.parseInt(protype);
			money = Integer.parseInt(planmoney);
			res = Integer.parseInt(resman);
			
		}catch (Exception e)
		{}
		Respro pro = new Respro();
		pro.setProname(proname);
		pro.setProtype(type);
		pro.setApptime(apptime);
		pro.setPlanmoney(money);
		pro.setResman(res);
		pro.setProexplain(explain);
		pro.setProdep(depnum);
		pro.setProkapp(0);
		pro.setProCon(0);
		pro.setNowMoney(0);
		pro.setPlanCon(0);
		pro.setTaskingid(-1);
		ResproDAO dao = new ResproDAOImpl();
		dao.addpro(pro);
		/**
		 * 以下为添加操作日志内容
		 */
		Operecord ope = new Operecord();
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("申报新的储备项目");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(1);
	    OperecordDAO dao2  = new OperecordDAOImpl();
	    dao2.addope(ope);
	    
		request.setAttribute("erroinfo","申请项目成功！等待审核");
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
