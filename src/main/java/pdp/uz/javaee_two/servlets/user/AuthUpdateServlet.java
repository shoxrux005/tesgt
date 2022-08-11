package pdp.uz.javaee_two.servlets.user;

import pdp.uz.javaee_two.configs.ApplicationContextHolder;
import pdp.uz.javaee_two.configs.PasswordConfigurer;
import pdp.uz.javaee_two.dao.userDao.UserDao;
import pdp.uz.javaee_two.domains.User;
import pdp.uz.javaee_two.dto.auth.UserUpdateDTO;
import pdp.uz.javaee_two.exception.InvalidInputException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/update")
public class AuthUpdateServlet extends HttpServlet {
    private final UserDao userDao = ApplicationContextHolder.getBean(UserDao.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/update.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("auth_session");
        User user = userDao.findByUsername(userName);
        long id = user.getId();

        String lastName = req.getParameter("lastname");
        if (lastName == null){
            lastName=user.getLastName();
        }
        String firstName = req.getParameter("firstname");
        if (firstName == null){
            firstName=user.getFirstName();
        }
        String oldPassword = req.getParameter("old_password");

        String newPassword = req.getParameter("new_password");
        String confirmNewPassword = req.getParameter("new password confirm");
        boolean olds = oldPassword.equals(user.getPassword());
        boolean news = newPassword.equals(confirmNewPassword);

        if ((olds&&news)){
             throw new InvalidInputException("something went wrong in passwords");
        }
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(PasswordConfigurer.encode(newPassword));
        userDao.update(user);

        resp.sendRedirect("/login");
    }
}
