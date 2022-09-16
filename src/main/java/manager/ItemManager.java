package manager;

import db.DBConnectionProvider;
import model.Category;
import model.Item;
import model.User;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    CategoryManager categoryManager = new CategoryManager();
    UserManager userManager = new UserManager();

    private Connection connection = DBConnectionProvider.getINSTANCE().getConnection();
    public void additem(Item item) throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("Insert into item(title,price,category_id,pic_url,user_id) Values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, item.getTitle());
        preparedStatement.setDouble(2,item.getPrice());
        preparedStatement.setInt(3, item.getCategory().getId());
        preparedStatement.setString(4, item.getPicUrl());
        preparedStatement.setInt(5, item.getUser().getId());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()){
            int id = resultSet.getInt(1);
            item.setId(id);


        }
    }
    public List<Item> getItemByCategoryId(int catId){
        String sql = "select * from item where category_id = ?" ;
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, catId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                items.add(getItemsFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;


    }
    public List<Item> getItemByUser(int userId){
        String sql = "select * from item where user_id = ?" ;
        List<Item> items = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                items.add(getItemsFromResultSet(resultSet));
            }
            return items;
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;


    }
    public List<Item> getAll() throws SQLException {
        String sql = "select * from item order by id desc limit 20";
        List<Item> items = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                items.add(getItemsFromResultSet(resultSet));
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return items;

    }
    private Item getItemsFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        Item item = new Item();
        item.setId(resultSet.getInt(1));
        item.setTitle(resultSet.getString(2));
        item.setPrice(resultSet.getDouble(3));
        int categoryId = resultSet.getInt(4);
        Category category = categoryManager.getById(categoryId);
        item.setCategory(category);
        item.setPicUrl(resultSet.getString(5));
        int userId = resultSet.getInt(6);
        User user = userManager.getById(userId);
        item.setUser(user);



        return item;


    }
    public void removeItemById(int id) {
        String sql = "DELETE FROM item WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




