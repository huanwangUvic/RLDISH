package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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




public class ResproServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ResproServlet() {
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
		String type = request.getParameter("a");
		
		ArrayList<Respro> listall = new ArrayList<Respro>();
		ArrayList<String> liststr = new ArrayList<String>();
 		ResproDAO dao = new ResproDAOImpl();
		String sql = null;
		if(type.equals("1"))
		{
			if(role==4||role==3)
			sql = "select * from respro where protype=1 and pro_con=0 ";
			if(role == 2)
			sql = "select * from respro where protype=1 and resman='"+depnum+"' and pro_con=0 ";
			if(role==1)
				sql = "select * from respro where protype=1 and prodep='"+depnum+"' and pro_con=0 ";
			
			
			listall = dao.getAllpro(sql);
		}
		if(type.equals("2"))
		{
			if(role==4||role==3)
				sql = "select * from respro where protype=2 and pro_con=0 ";
				if(role == 2)
				sql = "select * from respro where protype=2 and resman='"+depnum+"' and pro_con=0 ";
				if(role==1)
					sql = "select * from respro where protype=2 and prodep='"+depnum+"' and pro_con=0";
				
				listall = dao.getAllpro(sql);
		}
		if(type.equals("3"))
		{
			if(role==4||role==3)
				sql = "select * from respro where protype=3 and pro_con=0 ";
				if(role == 2)
				sql = "select * from respro where protype=3 and resman='"+depnum+"' and pro_con=0";
				if(role==1)
					sql = "select * from respro where protype=3 and prodep='"+depnum+"' and pro_con=0";
				
				listall = dao.getAllpro(sql);
		}
		if(type.equals("4"))
		{
			if(role==4||role==3)
				sql = "select * from respro where protype=4 and pro_con=0";
				if(role == 2)
				sql = "select * from respro where protype=4 and resman='"+depnum+"'and pro_con=0 ";
				if(role==1)
					sql = "select * from respro where protype=4 and prodep='"+depnum+"' and pro_con=0";
				
				listall = dao.getAllpro(sql);
		}
		if(type.equals("5"))
		{
			if(role==4||role==3)
				sql = "select * from respro where protype=5 and pro_con=0 ";
				if(role == 2)
				sql = "select * from respro where protype=5 and resman='"+depnum+"' and pro_con=0 ";
				if(role==1)
					sql = "select * from respro where protype=5 and prodep='"+depnum+"' and pro_con=0";
				
				listall = dao.getAllpro(sql);
		}
		if(type.equals("6"))
		{
			if(role==4||role==3)
				sql = "select * from respro where protype=6 and pro_con=0";
				if(role == 2)
				sql = "select * from respro where protype=6 and resman='"+depnum+"' and pro_con=0";
				if(role==1)
					sql = "select * from respro where protype=6 and prodep='"+depnum+"' and pro_con=0";
				
				listall = dao.getAllpro(sql);
		}
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
		request.getSession().setAttribute("proleixing", type);
		
		request.getRequestDispatcher("/respage/respro.jsp").forward(request, response);
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
