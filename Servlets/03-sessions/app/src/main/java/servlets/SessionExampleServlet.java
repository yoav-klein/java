package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;


public class SessionExampleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true); // Create a new session if one doesn't exist
        String username = (String) session.getAttribute("username");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (username == null) {
            session.setAttribute("username", "john_doe");
            out.println("<h1>Welcome, new user!</h1>");
            out.println("<p>Your username is set to 'john_doe'</p>");
        } else {
            out.println("<h1>Welcome back, " + username + "!</h1>");
        }
    }
}
