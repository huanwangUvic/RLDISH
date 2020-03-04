package com.servlet.analysis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.ResproDAO;
import com.dao.TaskDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.ResproDAOImpl;
import com.dao.impl.TaskDAOImpl;
import com.domain.Respro;
import com.domain.Task;

public class AnalyClearServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AnalyClearServlet() {
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
		int proid = -1;
		try{
		proid = Integer.parseInt(id);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
		String sql = "select * from task where proid = '"+proid+"' ";
		String sql2 = "select * from task where proid = '"+proid+"' and task_condition=2 ";
		String sql3 = "select * from task where proid = '"+proid+"' and task_condition!=0 and DATEDIFF(end_time,now())<0 ";
		TaskDAO dao = new TaskDAOImpl();
		ArrayList<Task> list = new ArrayList<Task>();
		ArrayList<Task> list2 = new ArrayList<Task>();
		ArrayList<Task> list3 = new ArrayList<Task>();
		list = dao.getAlltask(sql);
		list2 = dao.getAlltask(sql2);
		list3 = dao.getAlltask(sql3);
		double tasknum = list.size();//任务数
		double taskfinishednum = list2.size();//任务完成数
		System.out.println("任务完成数"+taskfinishednum);
		double exceednum = list3.size();//超期任务数
		double finishedratio=0;
		double exceedratio=0;
		if(tasknum!=0)
		{
		 finishedratio = taskfinishednum/tasknum;//任务完成率
		 exceedratio = exceednum/tasknum;//任务超期率
		}
		
		
		
		ResproDAO dao2 = new ResproDAOImpl();
		int taskingid = dao2.getpro(proid).getTaskingid();
		//System.out.println("this print tasking id "+taskingid);
		String taskname = null;
		if(taskingid!=-1){
		taskname = dao.gettask(taskingid).getTaskname();//当前进行的任务名
		}
		else
			 taskname = "无";
		double moneyratio = (double)dao2.getpro(proid).getNowMoney()/(double)dao2.getpro(proid).getPlanmoney();//资金到账率
		Respro pro = new Respro();
		pro=dao2.getpro(proid);//实体类pro
		
		
		
		request.setAttribute("num", tasknum);
		request.setAttribute("num1", taskfinishednum);
		request.setAttribute("ratio1", finishedratio);
		request.setAttribute("num2", exceednum);
		request.setAttribute("ratio2", exceedratio);
		request.setAttribute("nowtask", taskname);
		request.setAttribute("respro",pro );
		request.setAttribute("mratio", moneyratio);
		request.getRequestDispatcher("/analysis/clear.jsp").forward(request, response);
		
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
