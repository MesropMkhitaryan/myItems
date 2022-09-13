package servlet;
import lombok.SneakyThrows;
import manager.CategoryManager;
import manager.ItemManager;
import model.Category;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items")

public class ItemsServlet extends HttpServlet {

    ItemManager itemManager = new ItemManager();

    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        List<Item> itemByCategoryId = itemManager.getItemByCategoryId(categoryId);
            List<Item> itemList = itemManager.getAll();
            if (categoryId == 0) {
                req.setAttribute("items", itemList);
            }else {
                req.setAttribute("items",itemByCategoryId);

            }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
