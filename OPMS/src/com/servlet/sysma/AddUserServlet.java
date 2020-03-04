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

public class AddUserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddUserServlet() {
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		String usernum = request.getParameter("usernum");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String role = request.getParameter("userrole");
		int num=-1;
		
		int userrole = -1;
		try {
			num = Integer.parseInt(usernum);
			userrole = Integer.parseInt(role);
		}catch (Exception e)
		{}
		if(pwd.equals(""))
		{
			pwd="111111";
		}
		DepartmentDAO dao = new DepartmentDAOImpl();
		Department dep = new Department();
		dep.setDepname(username);
		dep.setDepnum(num);
		dep.setDeppwd(pwd);
		dep.setDeprole(userrole);
		dao.adddep(dep);
		
		/**
		 * 以下添加操作日志
		 */
		Operecord ope = new Operecord();
		//Department dep = (Department)request.getSession().getAttribute("userinfo");
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("添加新用户账号");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(1);
	    OperecordDAO dao3  = new OperecordDAOImpl();
	    dao3.addope(ope);
		
		
		request.setAttribute("erroinfo", "7");
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
