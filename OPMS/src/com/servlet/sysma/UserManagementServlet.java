package com.servlet.sysma;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.domain.Department;
import com.domain.Task;

public class UserManagementServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserManagementServlet() {
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
		String sql = "select * from department";
		DepartmentDAO dao = new DepartmentDAOImpl();
		ArrayList<Department> listall = dao.getAlldep(sql);
		
		int pageall = 1;
		int page = 1;
		List listx = null;
		if (listall != null && listall.size()>=5) {
			// 分页了

			pageall = (int) Math.ceil((double) listall.size() / 5); // 用于分页显示
			System.out.println(pageall);
			listx = listall.subList((page - 1) * 5, page * 5);
		}
		else if(listall.size() >= 1){
			listx = listall.subList((page - 1) * 5, listall.size());
		}
		
		request.getSession().setAttribute("listx", listx);
		request.getSession().setAttribute("pageall", pageall);
		request.getSession().setAttribute("curpage", page);
		//request.getSession().setAttribute("listall", listall);
		request.getSession().setAttribute("listall", listall);
		
		request.getRequestDispatcher("/syspage/user.jsp").forward(request, response);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
