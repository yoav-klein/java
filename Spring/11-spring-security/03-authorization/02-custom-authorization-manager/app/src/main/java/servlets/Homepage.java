package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;


public class Homepage extends HttpServlet {
    private static ITemplateEngine buildTemplateEngine(JakartaServletWebApplication application) {
		
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);

        // HTML is the default mode, but we will set it anyway for better understanding of code
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // Set template cache TTL to 1 hour. If not set, entries would live in cache until expelled by LRU
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));

        // Cache is set to true by default. Set to false if you want templates to
        // be automatically updated when modified.
        templateResolver.setCacheable(true);

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;

    }

	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	                     throws ServletException, IOException {
		JakartaServletWebApplication application = 
			JakartaServletWebApplication.buildApplication(request.getServletContext());

        response.setContentType("text/html");
					 
		ITemplateEngine engine = buildTemplateEngine(application);

		final IWebExchange webExchange = application.buildExchange(request, response);
		final IWebRequest webRequest = webExchange.getRequest();
		final Writer writer = response.getWriter();

		final WebContext ctx = new WebContext(webExchange, webExchange.getLocale());

		engine.process("homepage", ctx, writer);
	}

}