package com.servlet;

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

public class DeleteServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DeleteServlet() {
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
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		int proid = 0;
		try{
			proid = Integer.parseInt(id);
		}catch (Exception e) {
			// TODO: handle exception
		}
		ResproDAO dao = new ResproDAOImpl();
		TaskDAO dao2 = new TaskDAOImpl();
		dao.delpro(proid);
		dao2.deltask(proid);
		
		Operecord ope = new Operecord();
		Department dep = (Department)request.getSession().getAttribute("userinfo");
		ope.setOpeman(dep.getDepnum());
		ope.setOpecontent("删除储备项目及其各阶段任务");
		
		Date now = new Date();//获取当前时间
	    DateFormat d2 = DateFormat.getDateTimeInstance();
	    String str = d2.format(now);
	    ope.setOpetime(str);
	    ope.setOpetype(2);
	    OperecordDAO dao3  = new OperecordDAOImpl();
	    dao3.addope(ope);
		
		request.setAttribute("erroinfo","删除成功！");
		request.setAttribute("type", type);
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
