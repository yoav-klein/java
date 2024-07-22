package org.example;


import java.util.ArrayList;
import java.util.List;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemDAOJDBCImpl implements ItemDAO {

    
    private String dbUrl;
    private String user;
    private String password;

    private static final String GET_ALL_ITEMS = "SELECT * FROM items";
    private static final String ADD_ITEM = "INSERT INTO items (name, category_id, quantity, quantity_type_id) VALUES(?, ?, ?, ?)";
    private static final String DELETE_ITEM = "DELETE FROM items WHERE name = ? AND category_id = ? AND quantity_type_id = ?";
    private static final String GET_ITEM = "SELECT * FROM users WHERE name = ?";
    private static final String GET_CATEGORY_BY_ID = "SELECT name FROM Categories WHERE id = ?";
    private static final String GET_QUANTITY_TYPE_BY_ID = "SELECT name FROM QuantityTypes WHERE id = ?";
    private static final String GET_QUANTITY_TYPE_ID_BY_NAME = "SELECT id FROM QuantityTypes WHERE name = ?";
    private static final String GET_CATEGORY_ID_BY_NAME = "SELECT id FROM Categories WHERE name = ?";


    public ItemDAOJDBCImpl(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    
    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection with DB");
            System.out.println(e);
        }
        return connection;
    }
    
    private ItemCategory getCategoryFromId(int id) throws SQLException {
        ItemCategory c = null;
        try(Connection conn = getConnection(); PreparedStatement prst = conn.prepareStatement(GET_CATEGORY_BY_ID)) {
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                c = ItemCategory.valueOf(rs.getString("name"));
            }

        } catch(SQLException e) {
            System.out.println("couldn't get category from ID");
            System.out.println(e);
            throw(e);
        }

        return c;
    }

    private int getCategoryIdByName(ItemCategory category) throws SQLException {
        int id = -1;
        try(Connection conn = getConnection(); PreparedStatement prst = conn.prepareStatement(GET_CATEGORY_ID_BY_NAME)) {
            
            prst.setString(1, category.toString());
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                id = rs.getInt("id");
            }
        } catch(SQLException e) {
            System.out.println("couldn't get category id from name");
            System.out.println(e);
            throw(e);
        }
        return id;
    }   

    private int getQuantityTypeIdByName(QuantityType quantityType) throws SQLException {
        int id = -1;
        try(Connection conn = getConnection(); PreparedStatement prst = conn.prepareStatement(GET_QUANTITY_TYPE_ID_BY_NAME)) {
            
            prst.setString(1, quantityType.toString());
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                id = rs.getInt("id");
            }
        } catch(SQLException e) {
            System.out.println("couldn't get category id from name");
            System.out.println(e);
            throw(e);
        }
        return id;
    }  

    private QuantityType getQuantityTypeFromId(int id) throws SQLException {
        QuantityType q = null;
        try(Connection conn = getConnection(); PreparedStatement prst = conn.prepareStatement(GET_QUANTITY_TYPE_BY_ID)) {
            prst.setInt(1, id);
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                q = QuantityType.valueOf(rs.getString("name"));
            }

        } catch(SQLException e) {
            System.out.println("couldn't get quantity type from ID");
            System.out.println(e);
            throw(e);
        }

        return q;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();

        try(Connection conn = getConnection(); PreparedStatement prst = conn.prepareStatement(GET_ALL_ITEMS)) {
            ResultSet rs = prst.executeQuery();
            while(rs.next()) {
                Item item = new Item();

                String name = rs.getString("name");
                int categoryId = rs.getInt("category_id");
                int quantity = rs.getInt("quantity");
                int quantityTypeId = rs.getInt("quantity_type_id");

                item.setName(name);
                item.setCategory(getCategoryFromId(categoryId));
                item.setQuantity(quantity);
                item.setQuantityType(getQuantityTypeFromId(quantityTypeId));

                items.add(item);
            }
        } catch(SQLException e) {
            System.out.println("Error getting all items");
            System.out.println(e);
        }
        return items;
    }

    @Override
    public void addItem(Item item) {
        try(Connection conn = getConnection(); PreparedStatement prst = conn.prepareStatement(ADD_ITEM)) {
            prst.setString(1, item.getName());
            prst.setInt(2, getCategoryIdByName(item.getCategory()));
            prst.setInt(3, item.getQuantity());
            prst.setInt(4, getQuantityTypeIdByName(item.getQuantityType()));

            prst.execute();

        } catch(SQLException e) {
            System.out.println("Error adding item");
            System.out.println(e);
        }
    }

    @Override
    public void deleteItem(Item item) {
        try(Connection conn = getConnection(); PreparedStatement prst = conn.prepareStatement(DELETE_ITEM)) {
            prst.setString(1, item.getName());
            prst.setInt(2, getCategoryIdByName(item.getCategory()));
            prst.setInt(3, getQuantityTypeIdByName(item.getQuantityType()));
            prst.execute();
        } catch(SQLException e) {
            System.out.println("Error deleting item");
            System.out.println(e);
        }
        
    }
/* 

    @Override
    public Item getItemByName(String name) {
        // TODO Auto-generated method stub
        return null;
    } */
    
    
}
