package com.servlet.sysma;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.OperecordDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.OperecordDAOImpl;
import com.domain.Department;
import com.domain.Operecord;

public class PwdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PwdServlet() {
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
		String num = request.getParameter("num");
		String flag = request.getParameter("flag");
		
		int depnum = -1;
		try{
			depnum = Integer.parseInt(num);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		if(flag.equals("1")){
			
			DepartmentDAO dao = new DepartmentDAOImpl();
			Department dep  = dao.getdep(depnum);
			dep.setDeppwd("111111");
			dao.updatedep(dep);
			
			/**
			 * 以下添加操作日志
			 */
			Operecord ope = new Operecord();
			Department dep2 = (Department)request.getSession().getAttribute("userinfo");
			ope.setOpeman(dep2.getDepnum());
			ope.setOpecontent("重置用户密码");
			
			Date now = new Date();//获取当前时间
		    DateFormat d2 = DateFormat.getDateTimeInstance();
		    String str = d2.format(now);
		    ope.setOpetime(str);
		    ope.setOpetype(3);
		    OperecordDAO dao3  = new OperecordDAOImpl();
		    dao3.addope(ope);
		    
			
			request.setAttribute("erroinfo", "8");
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
		else
		{
		DepartmentDAO dao = new DepartmentDAOImpl();
		dao.deldep(depnum);
		
		/**
		 * 以下添加操作日志
		 */
		Operecord ope = new Operecord();
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("注销某用户账号");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(2);
	    OperecordDAO dao3  = new OperecordDAOImpl();
	    dao3.addope(ope);
		
		request.setAttribute("erroinfo", "9");
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
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
