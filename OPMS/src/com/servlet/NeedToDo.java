package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.ResproDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.ResproDAOImpl;
import com.domain.Department;
import com.domain.Respro;

public class NeedToDo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NeedToDo() {
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
		int role = dep.getDeprole();
		int depnum = dep.getDepnum();
		//获取页面信息
		
		
		ArrayList<Respro> listall = new ArrayList<Respro>();
		ArrayList<String> liststr = new ArrayList<String>();
 		ResproDAO dao = new ResproDAOImpl();
		String sql = null;
		
			if(role==4||role==3)
			{
			sql = "select * from respro where pro_con=0 ";
			sql+= " and (prokapp=0 or prokapp=2 or prokapp=3 or prokapp=5 or prokapp=6 or prokapp=8) ";
			}
			if(role == 2)
			{
			sql = "select * from respro where resman='"+depnum+"' and pro_con=0 ";
			sql+= " and (prokapp=0 or prokapp=2 or prokapp=3 or prokapp=5 or prokapp=6 or prokapp=8) ";
			}
			if(role==1)
			{
				sql = "select * from respro where prodep='"+depnum+"' and pro_con=0 ";
				sql+="and (prokapp=1 or prokapp=4 or prokapp=7 )";
			
			}
			
			listall = dao.getAllpro(sql);
		
		
		int i;
		DepartmentDAO dao2 = new DepartmentDAOImpl();
		for(i=0;i<listall.size();i++)
		{
			
			Respro pro = new Respro();
			pro = listall.get(i);
			liststr.add(dao2.getdep(pro.getProdep()).getDepname());
		}
		
		request.setAttribute("list", listall);
		request.setAttribute("depname", liststr);
		
		
		request.getRequestDispatcher("/needtodo.jsp").forward(request, response);
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
