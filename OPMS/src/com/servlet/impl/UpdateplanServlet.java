package com.servlet.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ResproDAO;
import com.dao.impl.ResproDAOImpl;
import com.domain.Respro;

public class UpdateplanServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UpdateplanServlet() {
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
		System.out.println(id);
		int proid = -1;
		try{
		proid =	Integer.parseInt(id);
		}catch (Exception e)
		{}
		ResproDAO dao = new ResproDAOImpl();
		Respro pro = new Respro();
		pro= dao.getpro(proid);
		pro.setPlanCon(3);
		dao.updatepro(pro);
		request.setAttribute("erroinfo", "2");
		request.setAttribute("proid", proid);
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

		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("proid");
		//System.out.println(id);
		String text = request.getParameter("content");
		//System.out.println(text);
		int proid = -1;
		try{
		proid =	Integer.parseInt(id);
		}catch (Exception e)
		{}
		System.out.println(proid);
		ResproDAO dao = new ResproDAOImpl();
		Respro pro = new Respro();
		pro=dao.getpro(proid);
		
		pro.setProstate(text);
		pro.setPlanCon(2);
		dao.updatepro(pro);
		request.setAttribute("erroinfo", "1");
		request.setAttribute("proid", proid);
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
