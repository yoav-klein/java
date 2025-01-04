package org.example;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */

public class Foo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");

		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/bar");
		PrintWriter out= response.getWriter();

		if(null == type) {
			out.println("Enter a parameter type=forward or type=include");
		}
		else if(type.equals("forward")) {
			out.println("This is response from Foo, now forwarding to Bar");
			rd.forward(request, response);
		}
		else if(type.equals("include")) {
			out.println("This is response from Foo, now including Bar");
			rd.include(request, response);
		} else {
			out.println("Enter a parameter type=forward or type=include");
		}
	}

}