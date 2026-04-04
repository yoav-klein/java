package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Management extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	                     throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Management!!</h1>");
		out.println("</body></html>");
	}

}
