package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DepartmentDAO;
import com.dao.OperecordDAO;
import com.dao.ResproDAO;
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.OperecordDAOImpl;
import com.dao.impl.ResproDAOImpl;
import com.domain.Department;
import com.domain.Operecord;
import com.domain.Respro;

public class QueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public QueryServlet() {
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
		String type = (String)request.getSession().getAttribute("proleixing");
		String proname = request.getParameter("proname");
		String prodep = request.getParameter("prodep");
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		int role = dep.getDeprole();
		int depnum = dep.getDepnum();
		String sql = null;
		int protype = 0;
		try {
			protype = Integer.parseInt(type);
			 sql="select * from respro,department where pro_con=0 and respro.prodep=department.depnum ";
				
				
					
					if(proname!=null && !"".equals(proname)){
						sql += " and proname like '%"+proname+"%' ";
					}
					if(role==3||role==4){
					if(prodep!=null && !"".equals(prodep)){
						sql += " and depname like '%"+prodep+"%' ";
					}}
					
					sql+=" and protype = '"+protype+"' ";
					
					if(role==1)
						sql+=" and prodep = '"+depnum+"' ";
					
					if(role==2)
						sql+=" and resman = '"+depnum+"' ";
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(sql);
			ResproDAO dao = new ResproDAOImpl();
			ArrayList<Respro> list = new ArrayList<Respro>();
			list = dao.getAllpro(sql);
			ArrayList<String> liststr = new ArrayList<String>();
			int i;
			DepartmentDAO dao2 = new DepartmentDAOImpl();
			for(i=0;i<list.size();i++)
			{
				
				Respro pro = new Respro();
				pro = list.get(i);
				liststr.add(dao2.getdep(pro.getProdep()).getDepname());
			}
			
			
			Operecord ope = new Operecord();
			//Department dep = (Department)request.getSession().getAttribute("userinfo");
			ope.setOpeman(dep.getDepnum());
			ope.setOpecontent("查询储备项目");
			
			Date now = new Date();//获取当前时间
		    DateFormat d2 = DateFormat.getDateTimeInstance();
		    String str = d2.format(now);
		    ope.setOpetime(str);
		    ope.setOpetype(4);
		    OperecordDAO dao3  = new OperecordDAOImpl();
		    dao3.addope(ope);
			
			request.setAttribute("depname", liststr);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/respage/respro.jsp").forward(request, response);
			
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
