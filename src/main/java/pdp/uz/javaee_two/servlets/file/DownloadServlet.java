package pdp.uz.javaee_two.servlets.file;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = req.getParameter("path");
        String cover = req.getParameter("cover");
        String file = req.getParameter("file");
        System.out.println("+++++++++++++++++");
        System.out.println("file = " + file);
        System.out.println("cover = " + cover);
        System.out.println("++++++++++++++++++++");

        File down = new File(fileName);
        FileInputStream inputStream = new FileInputStream(down);
        ServletContext context = getServletContext();

        String contentType = context.getMimeType(fileName);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        resp.setContentType(contentType);
        resp.setContentLength((int) down.length());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; fileName=\"%s\"", down.getName());
        resp.setHeader(headerKey, headerValue);

        OutputStream outputStream = resp.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();
    }
}
