package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAO;


@WebServlet("/cont2")
public class ControllerTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String command = request.getParameter("command");
		
		try {
			if(command.equals("search")) {
				ArrayList<String[]> woori = DAO.selectAll();
				RequestDispatcher rd = request.getRequestDispatcher("testTable1.jsp");
				System.out.println(5);
				request.setAttribute("woori", woori);
				System.out.println(6);
				rd.forward(request, response);
			}
				
		} catch (Exception e) {
			response.sendRedirect("failView.jsp");
		}
	}

}
