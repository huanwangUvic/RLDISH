package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.ResproDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.ResproDAOImpl;
import com.domain.Respro;

public class ProclearServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProclearServlet() {
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
		String num = request.getParameter("num");
		
		int proid = 0;
		int depnum = 0;
		try 
		{
			proid = Integer.parseInt(id);
			depnum = Integer.parseInt(num);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(proid);
		ResproDAO dao  = new ResproDAOImpl();
		DepartmentDAO dao2 = new DepartmentDAOImpl();
		String name = dao2.getdep(depnum).getDepname();
		Respro pro = new Respro();
		pro = dao.getpro(proid);
		int respeople = pro.getResman();
		String resman = dao2.getdep(respeople).getDepname();
		request.setAttribute("pro", pro);
		request.setAttribute("name", name);
		request.setAttribute("resman",resman );
		request.getRequestDispatcher("/respage/proclear.jsp").forward(request, response);
		
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
