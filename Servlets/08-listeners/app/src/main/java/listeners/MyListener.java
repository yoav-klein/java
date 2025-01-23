package listeners;

import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContext;


public class MyListener implements ServletContextListener {
    private String message;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();
        System.out.println("===== CONTEXT INITIALIZED: " + ctx.getInitParameter("message"));
    }
    public void contextDestoyed(ServletContextEvent event) {
        ServletContext ctx = event.getServletContext();
        System.out.println("===== CONTEXT DESTROYED: " + ctx.getInitParameter("message"));
    }
}