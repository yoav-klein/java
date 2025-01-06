package beans;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig config) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        chain.doFilter(request, response);

        PrintWriter out = response.getWriter();
		out.println("<p>Added by Second filter</p>");
		out.println("</body></html>");

    }

    @Override
    public void destroy() {

    }
}
