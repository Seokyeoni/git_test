package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cont")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String driverName = "com.mysql.jdbc.Driver";

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		System.out.println(1);
		try {
			Class.forName(driverName);
			System.out.println(2);
		} catch (ClassNotFoundException e) {
			System.out.println(3);
			e.printStackTrace();
			System.exit(1);
		}

		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "1234");

			System.out.println(3);
			String tableName = "woori1";
			
			Statement stmt = con.createStatement();
			String sql = "select * from " + tableName + " limit 10";
			System.out.println("Running: " + sql);
			ResultSet res = stmt.executeQuery(sql);
			
			ArrayList<String[]> nasdaq = new ArrayList<String[]>();
			while (res.next()) {
				String[] row = null;
				System.out.println(String.valueOf(
						res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4)
						+ "\t" + res.getString(5) + "\t" + res.getString(6) + "\t" + res.getString(7)));
				row = new String[]{String.valueOf(res.getString(1)), String.valueOf(res.getString(2))};
				nasdaq.add(row);
			}
			RequestDispatcher rd = request.getRequestDispatcher("testTable1.jsp");
			System.out.println(5);
			request.setAttribute("nasdaq", nasdaq);
			System.out.println(6);
			rd.forward(request, response);
			System.out.println(6);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

}



//public class Controller extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
//
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
//		System.out.println(1);
//		try {
//			Class.forName(driverName);
//			System.out.println(2);
//		} catch (ClassNotFoundException e) {
//			System.out.println(3);
//			e.printStackTrace();
//			System.exit(1);
//		}
//
//		Connection con;
//		try {
//			con = DriverManager.getConnection("jdbc:hive2://10.1.43.232:10000/default", "kpc", "kpc");
//
//			System.out.println(3);
//			String tableName = "nasdaq1";
//			
//			Statement stmt = con.createStatement();
//			String sql = "select * from " + tableName + " limit 10";
//			System.out.println("Running: " + sql);
//			ResultSet res = stmt.executeQuery(sql);
//			
//			
//			
//			ArrayList nasdaq = null;
//			String[] row = null;
//			while (res.next()) {
//				System.out.println(4);
//				row = new String[] {res.getString(1)};
//				System.out.println(row);
////				System.out.println(String.valueOf(
////						res.getString(1) + "\t" + res.getString(2) + "\t" + res.getString(3) + "\t" + res.getString(4)
////						+ "\t" + res.getString(5) + "\t" + res.getString(6) + "\t" + res.getString(7)));
//			}
//			System.out.println(row);
//			RequestDispatcher rd = request.getRequestDispatcher("testTable1.jsp");
//			System.out.println(5);
//			request.setAttribute("nasdaq", row);
//			System.out.println(6);
//			rd.forward(request, response);
//			System.out.println(6);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
//	
//
//}
