package pdp.uz.javaee_two.servlets.file;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



@WebServlet("/display")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String img = req.getParameter("img");
        Path path= Paths.get(img);
        ServletOutputStream outputStream= resp.getOutputStream();
        Files.copy(path,outputStream);
    }

}
