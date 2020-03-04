package com.servlet.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.OperecordDAO;
import com.dao.TaskDAO;
import com.dao.impl.OperecordDAOImpl;
import com.dao.impl.TaskDAOImpl;
import com.domain.Department;
import com.domain.Operecord;

public class DeletetaskServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeletetaskServlet() {
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
		int taskid = -1;
		try{
			taskid = Integer.parseInt(id);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		TaskDAO dao = new TaskDAOImpl();
		int proid = dao.gettask(taskid).getProid();
		
		dao.deltask2(taskid);
		
		/**
		 * 以下添加操作日志
		 */
		Operecord ope = new Operecord();
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("删除项目某阶段任务");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(2);
	    OperecordDAO dao3  = new OperecordDAOImpl();
	    dao3.addope(ope);
		
		
		System.out.println("this print task id "+taskid);
		request.setAttribute("erroinfo", "5");
		request.setAttribute("id", proid);
		request.getRequestDispatcher("/erro.jsp").forward(request, response);
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
