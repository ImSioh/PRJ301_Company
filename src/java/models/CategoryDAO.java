package models;

import dal.Category;
import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO extends DBContext {

    public ArrayList<Category> getCategory() {
        ArrayList<Category> category = new ArrayList<>();
        try {
            String sql = "select * from Categories";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int CategoryID = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");

                Category c = new Category(CategoryID, CategoryName, Description);

                category.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return category;
    }

    public Category getCategoryById(int CategoryID) {
        try {
            String sql = "select * from Categories where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int CategoryID2 = rs.getInt("CategoryID");
                String CategoryName = rs.getString("CategoryName");
                String Description = rs.getString("Description");

                Category c = new Category(CategoryID2, CategoryName, Description);

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    
    public static void main(String[] args) {
        ArrayList<Category> list = new CategoryDAO().getCategory();
        for (Category category : list) {
            System.out.println(category);
        }
    }
}
