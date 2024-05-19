package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class MyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	                     throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Got GET</h1>");
		out.println("</body></html>");
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) 
	                     throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Got POST</h1>");
		out.println("</body></html>");
	}
}
