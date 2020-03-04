package com.servlet.sysma;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Department;
import com.domain.Operecord;

public class OpeRecordPageServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public OpeRecordPageServlet() {
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

		 ArrayList<Operecord> listall = (ArrayList<Operecord>) request.getSession().getAttribute("listall");
		 ArrayList<String> liststr = (ArrayList<String>) request.getSession().getAttribute("liststr");
		 ArrayList<Integer> liststr2 = (ArrayList<Integer>) request.getSession().getAttribute("liststr2");
			
			List list = (List) request.getSession().getAttribute("listx");
			List list2 = (List) request.getSession().getAttribute("liststrx");
			List list3 = (List) request.getSession().getAttribute("liststrx2");
			
			int page = Integer.parseInt(request.getParameter("pg"));
			
			int pageall = (Integer) request.getSession().getAttribute("pageall");
			
			
			System.out.println("!!!!!!!!!!!!!!!" + page);
			System.out.println("++++++++++++++++" + pageall);
			
			if (pageall == 1) {
		request.getSession().setAttribute("listx",listall.subList(5 * (pageall - 1), listall.size()));
		request.getSession().setAttribute("liststrx",liststr.subList(5 * (pageall - 1), listall.size()));
		request.getSession().setAttribute("liststrx2",liststr2.subList(5 * (pageall - 1), listall.size()));
		
		request.getSession().setAttribute("curpage", pageall);
		} 
			else if (page <= 0) {
		request.getSession().setAttribute("listx",
				listall.subList(5 * (pageall - 1), listall.size()));
		request.getSession().setAttribute("liststrx",
				liststr.subList(5 * (pageall - 1), listall.size()));
		request.getSession().setAttribute("liststrx2",
				liststr2.subList(5 * (pageall - 1), listall.size()));
		
		request.getSession().setAttribute("curpage", pageall);
		} 
			
		    else if (page > pageall) {
		request.getSession().setAttribute("listx", listall.subList(0, 5));
		request.getSession().setAttribute("liststrx", liststr.subList(0, 5));
		request.getSession().setAttribute("liststrx2", liststr2.subList(0, 5));
		request.getSession().setAttribute("curpage", 1);
		} 
		    
		   else if (page == pageall) {
		request.getSession().setAttribute("listx",
				listall.subList(5 * (page - 1), listall.size()));
		request.getSession().setAttribute("liststrx",
				liststr.subList(5 * (page - 1), liststr.size()));
		request.getSession().setAttribute("liststrx2",
				liststr2.subList(5 * (page - 1), liststr2.size()));
		request.getSession().setAttribute("curpage", page);
		} 
		   else {
		request.getSession().setAttribute("listx",
				listall.subList(5 * (page - 1), 5 * page));
		request.getSession().setAttribute("liststrx",
				liststr.subList(5 * (page - 1), 5 * page));
		request.getSession().setAttribute("liststrx2",
				liststr2.subList(5 * (page - 1), 5 * page));
		request.getSession().setAttribute("curpage", page);
	}
	// ��תҳ��
			request.getRequestDispatcher("/syspage/opedaily.jsp").forward(
			request, response);
		
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
