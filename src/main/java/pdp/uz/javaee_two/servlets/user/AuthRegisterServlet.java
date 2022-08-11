package pdp.uz.javaee_two.servlets.user;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.configs.PasswordConfigurer;
import pdp.uz.javaee_two.dao.userDao.UserDao;
import pdp.uz.javaee_two.domains.User;
import pdp.uz.javaee_two.exception.InvalidInputException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@WebServlet("/register")
public class AuthRegisterServlet extends HttpServlet {
    private final UserDao userDao = ApplicationContextHolder.getBean(UserDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String lastName = req.getParameter("lastname");
        String firstName = req.getParameter("firstname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        User user = userDao.findByUsername(username);
        if (Objects.nonNull(user))
            throw new InvalidInputException("'%s' Username already taken".formatted(username));
        user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(PasswordConfigurer.encode(password))
                .build();
        userDao.save(user);

        resp.sendRedirect("/login");
    }
}
