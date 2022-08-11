package pdp.uz.javaee_two.servlets;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.dao.bookDao.BookDao;
import pdp.uz.javaee_two.dao.userDao.UserDao;
import pdp.uz.javaee_two.domains.Book;
import pdp.uz.javaee_two.domains.User;
import pdp.uz.javaee_two.enums.BookStatus;
import pdp.uz.javaee_two.enums.Language;
import pdp.uz.javaee_two.enums.UserStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private final BookDao bookDao = ApplicationContextHolder.getBean(BookDao.class);
    private final UserDao userDao  = ApplicationContextHolder.getBean(UserDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String authSession = (String) session.getAttribute("auth_session");
        String search = req.getParameter("search");
        if (Objects.isNull(search)) {
            search = "";
        }
        List<Book> books = bookDao.findAll(search);

        if (Objects.nonNull(authSession)) {
            req.setAttribute("search", search);
            req.setAttribute("username", req.getSession().getAttribute("auth_session"));
            req.setAttribute("genres", Book.Genre.values());
            req.setAttribute("languages", Language.values());
            User user = userDao.findByUsername(authSession);

            int page = 1;
            int recordsPerPage = 6;
            if (req.getParameter("page") != null)
                page = Integer.parseInt(req.getParameter("page"));

            List<Book> list = bookDao.viewAllBooks((page - 1) * recordsPerPage, recordsPerPage, search);
            int noOfRecords = books.size();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            req.setAttribute("books", list);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);

            RequestDispatcher dispatcher;
            if (user.getStatus().equals(UserStatus.USER)) {
                dispatcher = req.getRequestDispatcher("views/main.jsp");
            } else {
                dispatcher = req.getRequestDispatcher("views/admin.jsp");
            }
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}


