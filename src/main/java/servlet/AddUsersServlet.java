package servlet;

import lombok.SneakyThrows;

import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;

import model.Category;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/users/add")
public class AddUsersServlet extends HttpServlet {

    UserManager userManager = new UserManager();


    ItemManager itemManager = new ItemManager();
    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/addUsers.jsp").forward(req,resp);
    }
        @SneakyThrows
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        if (userManager.getUserByEmail(email) != null){
            req.setAttribute("msg", "User already exists");
            req.getRequestDispatcher("/WEB-INF/addUsers.jsp").forward(req,resp);
        }
        else { String name = req.getParameter("name");
            String surname = req.getParameter("surname");

            String password = req.getParameter("password");




            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .build();
            userManager.addUser(user);
            resp.sendRedirect("/login");}

    }
}