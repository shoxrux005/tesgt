package pdp.uz.javaee_two.filters.auth;

import pdp.uz.javaee_two.exception.InvalidInputException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;


//@WebFilter("/register")

public class AuthRegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String confirmPassword = req.getParameter("confirmPassword");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("confirmPassword = " + confirmPassword);
        String method = ((HttpServletRequest) req).getMethod();
        System.out.println("method = " + method);
        if ("post".equalsIgnoreCase(method)) {
            if (Objects.isNull(username))
                throw new InvalidInputException("Username can not be null");
            if (Objects.isNull(password))
                throw new InvalidInputException("Password can not be null");
            if (!Objects.equals(password, confirmPassword))
                throw new InvalidInputException("Password did not match");
        }
        chain.doFilter(req, res);
    }
}
