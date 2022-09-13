package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CategoryManager {
    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();


    public void add(Category category) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("Insert into category(name) Values(?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, category.getName());

        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();

        if (resultSet.next()){
            int id = resultSet.getInt(1);
            category.setId(id);


        }
    }
    private Category getCategoriesFromResultSet(ResultSet resultSet) throws SQLException, ParseException {

        Category category = new Category();
        category.setId(resultSet.getInt(1));
        category.setName(resultSet.getString(2));


        return category;
    }

    public List<Category> getAll() throws SQLException {
        String sql = "select * from category";
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                categories.add(getCategoriesFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return categories;

    }
    public Category getById(int id) {
        String sql = "select * from category where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                return getCategoriesFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}