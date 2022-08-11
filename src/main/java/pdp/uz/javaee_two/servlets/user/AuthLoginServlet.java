package pdp.uz.javaee_two.servlets.user;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.configs.PasswordConfigurer;
import pdp.uz.javaee_two.dao.userDao.UserDao;
import pdp.uz.javaee_two.domains.User;
import pdp.uz.javaee_two.exception.AuthenticationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;


@WebServlet("/login")
public class AuthLoginServlet extends HttpServlet {
    private final UserDao userDao = ApplicationContextHolder.getBean(UserDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userDao.findByUsername(username);


        if (Objects.isNull(user) || !PasswordConfigurer.matchPassword(password, user.getPassword())) {
            throw new AuthenticationException("Bad Credentials");
        }

        HttpSession session = req.getSession();
//        Cookie cookie = new Cookie("session_user", username);
//        cookie.setMaxAge(900);
//        resp.addCookie(cookie);
        session.setAttribute("auth_session", username);
        session.setAttribute("status", user.getStatus());
        resp.sendRedirect("/");
    }
}
