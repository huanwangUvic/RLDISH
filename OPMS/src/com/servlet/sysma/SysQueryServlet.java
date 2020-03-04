package com.servlet.sysma;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class SysQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SysQueryServlet() {
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
		
		String username = request.getParameter("username");
		
		String sql = null;
		
		try {
			
			 sql="select * from department where 1=1 ";
				
				
					
					if(username!=null && !"".equals(username)){
						sql += " and depname like '%"+username+"%' ";
					}
					
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			DepartmentDAO dao = new DepartmentDAOImpl();
			ArrayList<Department> listall = new ArrayList<Department>();
			listall = dao.getAlldep(sql);
			
			
	    int pageall = 1;
		int page = 1;
		List listx = null;
		if (listall != null && listall.size()>=5) {
			// 分页了

			pageall = (int) Math.ceil((double) listall.size() / 5); // 用于分页显示
			System.out.println(pageall);
			listx = listall.subList((page - 1) * 5, page * 5);
		}
		else if(listall.size() >= 1){
			listx = listall.subList((page - 1) * 5, listall.size());
		}
		
		request.getSession().setAttribute("listx", listx);
		request.getSession().setAttribute("pageall", pageall);
		request.getSession().setAttribute("curpage", page);
		//request.getSession().setAttribute("listall", listall);
		request.getSession().setAttribute("listall", listall);
			
			
			/**
			 * 以下添加操作日志
			 */
			Operecord ope = new Operecord();
			Department dep2 = (Department)request.getSession().getAttribute("userinfo");
			ope.setOpeman(dep2.getDepnum());
			ope.setOpecontent("通过关键字查找用户账号信息");
			
			Date now = new Date();//获取当前时间
		    DateFormat d2 = DateFormat.getDateTimeInstance();
		    String str = d2.format(now);
		    ope.setOpetime(str);
		    ope.setOpetype(4);
		    OperecordDAO dao3  = new OperecordDAOImpl();
		    dao3.addope(ope);
			
			
			
			request.getRequestDispatcher("/syspage/user.jsp").forward(request, response);
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
