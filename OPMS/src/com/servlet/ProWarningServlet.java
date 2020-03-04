package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.Date;

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
import com.domain.Department;
import com.domain.Respro;
import com.domain.Task;


public class ProWarningServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProWarningServlet() {
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
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		int depnum = dep.getDepnum();
		TaskDAO dao = new TaskDAOImpl();
		
		dao.updatetask2();
		
		String sql="select * from task,respro where respro.proid=task.proid  ";
		int role = dep.getDeprole();
		if(role==1)
		{
			sql+=" and prodep = '"+depnum+"' ";
		}
		if(role==2)
		{
			sql+="and resman = '"+depnum+"' ";
		}
		sql+=" and DATEDIFF(task.end_time,now())<=5  ";
		sql+=" and task_condition!=2";
		
		System.out.println(sql);
		
		//select * from respro,department where pro_con=0 and respro.prodep=department.depnum
		ArrayList<Task> list = dao.getAlltask(sql);
		
		ArrayList<String> liststr = new ArrayList<String>();
		int i;
		ResproDAO dao2 =new  ResproDAOImpl();
		for(i=0;i<list.size();i++)
		{
			Task task=list.get(i);
			liststr.add(dao2.getpro(task.getProid()).getProname());
		}
		
		request.setAttribute("list", list);
		request.setAttribute("proname", liststr);
		request.getRequestDispatcher("/files/mainfra.jsp").forward(request, response);
		
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
