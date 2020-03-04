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
import com.dao.impl.DepartmentDAOImpl;
import com.dao.impl.OperecordDAOImpl;
import com.domain.Department;
import com.domain.Operecord;

public class RecordQueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RecordQueryServlet() {
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
		
		String usernum = request.getParameter("usernum");
		
		String type  = request.getParameter("opetype");
		String content = request.getParameter("content");
		int num=-1;
		int opetype =-1;
		String sql = null;
		
		
		try {
			if(!usernum.equals(""))
			 num = Integer.parseInt(usernum);
			//System.out.println("num%%%"+num);
			opetype = Integer.parseInt(type);
			 sql="select * from operecord where 1=1 ";
				
			 if(num!=-1)
			 sql+=" and opeman = '"+num+"' ";
			 if(opetype!=0)
			 sql+=" and opetype = '"+opetype+"' ";
			 
			 
			 if(content!=null && !"".equals(content)){
					sql += " and opecontent like '%"+content+"%' ";
				}
					
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			OperecordDAO  dao = new OperecordDAOImpl();
			ArrayList<Operecord> listall = new ArrayList<Operecord>();
			listall = dao.getAllope(sql);
			
		
			int i;
			
			//System.out.println("this 输出 listall.size"+listall.size());
			
			ArrayList<String> liststr = new ArrayList<String>();
			ArrayList<Integer> liststr2 = new ArrayList<Integer>();
			DepartmentDAO dao2 = new DepartmentDAOImpl();
			for(i=0;i<listall.size();i++)
			{
				
				Operecord record = listall.get(i);
				//System.out.println("this print id"+dao2.getdep(record.getOpeman()).getDepname());
				liststr.add(dao2.getdep(record.getOpeman()).getDepname());
				//sSystem.out.println("xsxsxssxxsxsxsxs");
			}
			//System.out.println("xsxsxssxxsxsxsxs"+liststr.get(0));
			for(i=0;i<listall.size();i++)
			{
				
				Operecord record = listall.get(i);
				liststr2.add(dao2.getdep(record.getOpeman()).getDeprole());
			}
			
			
			int pageall = 1;
			int page = 1;
			List listx = null;
			List liststrx =null;
			List liststrx2 = null;
			if (listall != null && listall.size()>=5) {
				// 分页了

				pageall = (int) Math.ceil((double) listall.size() / 5); // 用于分页显示
				System.out.println(pageall);
				listx = listall.subList((page - 1) * 5, page * 5);
				liststrx = liststr.subList((page - 1) * 5, page * 5);
				liststrx2 = liststr2.subList((page - 1) * 5, page * 5);
			}
			else if(listall.size() >= 1){
				listx = listall.subList((page - 1) * 5, listall.size());
				liststrx = liststr.subList((page - 1) * 5, listall.size());
				liststrx2 = liststr2.subList((page - 1) * 5, listall.size());
			}
			
			request.getSession().setAttribute("listx", listx);
			request.getSession().setAttribute("liststrx", liststrx);
			request.getSession().setAttribute("liststrx2", liststrx2);
			request.getSession().setAttribute("pageall", pageall);
			request.getSession().setAttribute("curpage", page);
			//request.getSession().setAttribute("listall", listall);
			request.getSession().setAttribute("listall", listall);
			request.getSession().setAttribute("liststr", liststr );
			request.getSession().setAttribute("liststr2", liststr2);
			request.getRequestDispatcher("/syspage/opedaily.jsp").forward(request, response);
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
