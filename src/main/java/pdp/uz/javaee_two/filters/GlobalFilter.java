package pdp.uz.javaee_two.filters;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.dao.userDao.UserDao;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class GlobalFilter implements Filter {
    private final UserDao userDao=ApplicationContextHolder.getBean(UserDao.class);

    private final List<String> WHITE_LIST = new ArrayList<>(Arrays.asList(
            "/login",
            "/register"
    ));
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (WHITE_LIST.contains(req.getRequestURI())) {
            chain.doFilter(req, response);
            return;
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("session_user")) {
                    chain.doFilter(req, response);
                    return;
                }
            }
        }

        ((HttpServletResponse) response).sendRedirect("/login");
    }
}
