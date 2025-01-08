package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class UsersOnly extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	                     throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>Only authenticated users can see this page!!</h1>");
		out.println("</body></html>");
	}

}
