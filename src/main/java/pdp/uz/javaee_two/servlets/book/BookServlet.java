package pdp.uz.javaee_two.servlets.book;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.dto.book.BookCreateDTO;
import pdp.uz.javaee_two.services.book.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet("/uploads/add")
@MultipartConfig
public class BookServlet extends HttpServlet {
    private final BookService bookService = ApplicationContextHolder.getBean(BookService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/main.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String author = req.getParameter("author");
            String genre = req.getParameter("genre");
            String language = req.getParameter("language");
            String pageCount = req.getParameter("pageCount");

            Part cover = req.getPart("cover");
            Part file = req.getPart("file");


            System.out.println("++++++++++++++++++++++");
            System.out.println("name = " + name);
            System.out.println("description = " + description);
            System.out.println("author = " + author);
            System.out.println("genre = " + genre);
            System.out.println("language = " + language);
            System.out.println("pageCount = " + pageCount);
            System.out.println("++++++++++++++++++++++++");

            BookCreateDTO bookCreateDTO = BookCreateDTO.builder()
                    .name(name)
                    .description(description)
                    .author(author)
                    .genre(genre)
                    .cover(file)
                    .file(file)
                    .language(language)
                    .pageCount(Integer.valueOf(pageCount))
                    .build();

            bookService.create(bookCreateDTO);
            resp.sendRedirect("/");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
