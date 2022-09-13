package servlet;

import lombok.SneakyThrows;
import manager.CategoryManager;
import manager.ItemManager;
import manager.UserManager;
import model.Category;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/items/add")
@MultipartConfig(
        fileSizeThreshold = 1024*1024*1,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*100
)
public class AddItemsServlet extends HttpServlet {
    ItemManager itemManager =new ItemManager();
    private static final String IMAGE_PATH = "C:\\Users\\user\\IdeaProjects\\myItemsNew\\projectImages\\";
    CategoryManager categoryManager = new CategoryManager();
    UserManager userManager = new UserManager();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryManager.getAll();
        req.setAttribute("category", categoryList);
        req.getRequestDispatcher("/WEB-INF/addItems.jsp").forward(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String title = req.getParameter("title");
        double price = Double.parseDouble(req.getParameter("price"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part itemPicPart = req.getPart("itemPic");
        String fileName = null;
        if (itemPicPart.getSize() != 0 ){
            long nanoTime = System.nanoTime();
            fileName = nanoTime + "_" + itemPicPart.getSubmittedFileName();

            itemPicPart.write(IMAGE_PATH + fileName);
        }



        Item item = Item.builder()
                .title(title)
                .price(price)
                .category(categoryManager.getById(categoryId))
                .picUrl(fileName)
                .user(userManager.getById(userId))
                .build();
        itemManager.additem(
                item);
        resp.sendRedirect("/items");
    }
}
