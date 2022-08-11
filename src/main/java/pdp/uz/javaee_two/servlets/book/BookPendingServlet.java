package pdp.uz.javaee_two.servlets.book;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.dao.bookDao.BookDao;
import pdp.uz.javaee_two.domains.Book;
import pdp.uz.javaee_two.domains.User;
import pdp.uz.javaee_two.enums.BookStatus;
import pdp.uz.javaee_two.enums.Language;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/pending")
public class BookPendingServlet extends HttpServlet {
    private final BookDao bookDao= ApplicationContextHolder.getBean(BookDao.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search = req.getParameter("search");
        if (Objects.isNull(search)) {
            search = "";
        }
        req.setAttribute("search", search);
        int page = 1;
        int recordsPerPage = 6;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        List<Book> allPenning = bookDao.findAllPenning(search);

        List<Book> list = bookDao.viewAllPenningBooks((page - 1) * recordsPerPage, recordsPerPage, search);
        int noOfRecords = allPenning.size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        req.setAttribute("books", list);
        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);
        req.setAttribute("books", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/pending.jsp");
        dispatcher.forward(req, resp);
    }

}
