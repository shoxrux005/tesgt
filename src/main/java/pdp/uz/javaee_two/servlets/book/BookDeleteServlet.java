package pdp.uz.javaee_two.servlets.book;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.services.book.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author "Tojaliyev Asliddin"
 * @since 17/07/22 19:10 (Sunday)
 * JavaEE_two/IntelliJ IDEA
 */
@WebServlet("/cancelBook")
public class BookDeleteServlet extends HttpServlet {
    private final BookService bookService = ApplicationContextHolder.getBean(BookService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        System.out.println("bookId = " + bookId);
        bookService.delete(Integer.valueOf(bookId));
        resp.sendRedirect("/admin");
    }
}
