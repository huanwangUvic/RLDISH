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
import com.dao.ResproDAO;
import com.dao.TaskDAO;
import com.dao.impl.OperecordDAOImpl;
import com.dao.impl.ResproDAOImpl;
import com.dao.impl.TaskDAOImpl;
import com.domain.Department;
import com.domain.Operecord;
import com.domain.Respro;
import com.domain.Task;

public class UpdatetaskServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdatetaskServlet() {
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
		String id = request.getParameter("id");
		String taskname = request.getParameter("taskname");
		String plantime = request.getParameter("plantime");
		String endtime = request.getParameter("endtime");
		String starttime = request.getParameter("starttime");
		String subfile = request.getParameter("subfile");
		String taskcon = request.getParameter("taskcon");
		String content = request.getParameter("content");
		int taskid = -1;
		int con =-1;
		try {
			taskid = Integer.parseInt(id);
			con= Integer.parseInt(taskcon);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		/*
		 * 
		 */
		Task task = new Task();
		TaskDAO dao = new TaskDAOImpl();
		task=dao.gettask(taskid);
		
		//task.setTaskid(taskid);
		task.setTaskname(taskname);
		task.setPlanTime(plantime);
		task.setEndTime(endtime);
		task.setStartTime(starttime);
		task.setSubFile(subfile);
		task.setTaskCondition(con);
		task.setTaskexplain(content);
		
		
		if(con==1)//修改任务时如果把任务置为进行中， 则把pro表中的当前进行任务重新赋值
		{
		
		ResproDAO dao2 = new ResproDAOImpl();
		Respro pro = new Respro();
		pro=dao2.getpro(task.getProid());
		pro.setTaskingid(task.getTaskid());
		dao2.updatepro(pro);
		
		dao.updatetask3();
		
		
		}
		dao.updatetask(task);
		/**
		 * 以下添加操作日志
		 */
		Operecord ope = new Operecord();
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("修改项目里程碑计划某阶段信息");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(3);
	    OperecordDAO dao3  = new OperecordDAOImpl();
	    dao3.addope(ope);
		
		
		request.setAttribute("erroinfo", "4");
		request.setAttribute("proid", task.getProid());
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
