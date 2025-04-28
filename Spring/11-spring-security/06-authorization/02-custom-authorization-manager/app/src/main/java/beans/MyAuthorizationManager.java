package beans;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.security.authorization.AuthorizationDecision;
import java.util.function.Supplier;
import jakarta.servlet.http.HttpServletRequest;



@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        HttpServletRequest request = context.getRequest();
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request URL: " + request.getRequestURL());
        return new AuthorizationDecision(false); // Allow access for demonstration purposes
    }
}