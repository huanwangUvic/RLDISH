package com.servlet.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ResproDAO;
import com.dao.TaskDAO;
import com.dao.impl.ResproDAOImpl;
import com.dao.impl.TaskDAOImpl;
import com.domain.Respro;
import com.domain.Task;

public class TaskclearServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TaskclearServlet() {
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
		String id = request.getParameter("taskid");
		String flag= request.getParameter("flag");
		int taskid= -1;
		try{
			taskid = Integer.parseInt(id);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		String proname=null;
		TaskDAO dao = new TaskDAOImpl();
		ResproDAO dao2 = new ResproDAOImpl();
		Respro pro = new Respro();
		pro=dao2.getpro(dao.gettask(taskid).getProid());
		int con = pro.getPlanCon();
		proname = pro.getProname();
		
		Task task = new Task();
		task = dao.gettask(taskid);
		request.setAttribute("task", task);
		request.setAttribute("proname",proname);
		request.setAttribute("plancon", con);
		if(flag.equals("1"))
		request.getRequestDispatcher("/implpage/taskclear.jsp").forward(request, response);
		else
		{
			request.getRequestDispatcher("/implpage/taskupdate.jsp").forward(request, response);
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
