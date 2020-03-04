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

public class AddtaskServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddtaskServlet() {
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
		String i = request.getParameter("i");
		int proid =-1;
		int ii=-1;
		try{
			proid = Integer.parseInt(id);
			ii = Integer.parseInt(i);
		}catch(Exception e)
		{}
		ii++;
		request.setAttribute("id",proid);
		request.setAttribute("i", ii);
		request.getRequestDispatcher("/implpage/dotask.jsp").forward(request, response);
		
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
		String flag = request.getParameter("hid");
		System.out.println("this is flag--"+flag);
		String taskname=request.getParameter("taskname");
		String plantime = request.getParameter("plantime");
		String endtime = request.getParameter("endtime");
		String file = request.getParameter("file");
		String content = request.getParameter("content");
		//System.out.println(id);
		String i = request.getParameter("i");
		int proid =-1;
		int ii=-1;
		try{
			proid = Integer.parseInt(id);
			ii = Integer.parseInt(i);
		}catch(Exception e)
		{}
		ii++;
		
		/**
		 * 下列是更新该项目plancon(里程碑计划情况)为已提交)
		 */
		Respro pro = new Respro();
		ResproDAO dao2 = new ResproDAOImpl();
		pro= dao2.getpro(proid);
		pro.setPlanCon(1);
		dao2.updatepro(pro);
		
		/**
		 * 以下把项目任务写入数据库
		 */
		Task task = new Task();
		task.setEndTime(endtime);
		task.setFileCon(0);
		task.setPlanTime(plantime);
		task.setProid(proid);
		task.setSubFile(file);
		task.setTaskCondition(0);
		task.setFilePath(-1);
		task.setTaskexplain(content);
		task.setTaskname(taskname);
		TaskDAO dao = new TaskDAOImpl();
		dao.addtask(task);
		//System.out.println(ii);
		//System.out.println(proid);
		
		/**
		 * 以下添加操作日志
		 */
		Operecord ope = new Operecord();
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("添加项目里程碑计划任务");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(1);
	    OperecordDAO dao3  = new OperecordDAOImpl();
	    dao3.addope(ope);
		
		
		
		
		if(flag.equals("1")){
		request.setAttribute("id",proid);
		request.setAttribute("i", ii);
		request.getRequestDispatcher("/implpage/dotask.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("erroinfo", "3");
			request.setAttribute("id", proid);
			request.getRequestDispatcher("/erro.jsp").forward(request, response);
		}
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
