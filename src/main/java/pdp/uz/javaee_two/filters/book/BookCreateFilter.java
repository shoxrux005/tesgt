package pdp.uz.javaee_two.filters.book;

import javax.servlet.*;
import java.io.IOException;

public class BookCreateFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String name = servletRequest.getParameter("name");
        String pageCount = servletRequest.getParameter("pageCount");
    }
}
