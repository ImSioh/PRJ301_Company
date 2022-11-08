package models;

import dal.DBContext;
import dal.Product;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO extends DBContext {

    public ArrayList<Product> parseResultSetToArray(ResultSet rs) {
        ArrayList<Product> pro = new ArrayList<>();
        try {
            while (rs.next()) {
                int ProductID = rs.getInt("ProductID");
                String ProductName = rs.getString("ProductName");
                int CategoryID = rs.getInt("CategoryID");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                double UnitPrice = rs.getDouble("UnitPrice");
                int UnitsInStock = rs.getInt("UnitsInStock");
                int UnitsOnOrder = rs.getInt("UnitsOnOrder");
                int ReorderLevel = rs.getInt("ReorderLevel");
                boolean Discontinued = rs.getBoolean("Discontinued");

                Product p = new Product(ProductID, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);

                pro.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    public ArrayList<Product> getProduct() {
        ArrayList<Product> pro = new ArrayList<>();
        try {
            String sql = "select * from Products";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            pro = parseResultSetToArray(rs);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return pro;
    }

    public Product getProductById(int ProductID) {

        try {
            String sql = "select * from Products where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ProductID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int ProductID2 = rs.getInt(1);
                String ProductName = rs.getString(2);
                int CategoryID = rs.getInt(3);
                String QuantityPerUnit = rs.getString(4);
                double UnitPrice = rs.getDouble(5);
                int UnitsInStock = rs.getInt(6);
                int UnitsOnOrder = rs.getInt(7);
                int ReorderLevel = rs.getInt(8);
                boolean Discontinued = rs.getBoolean(9);

                Product p = new Product(ProductID2, ProductName, CategoryID, QuantityPerUnit, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued);
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Product> getProductByKeyword(String keyword, HashMap<String, String> filter) {
        ArrayList<Product> pro = new ArrayList<>();
        int sql_param_counter = 1;
        try {
            String sql = "select * from Products\n"
                    + "where ProductName like ?";

            String categoryID = filter.get("CategoryID");
            if (categoryID != null) {
                if (!categoryID.trim().equals("")){
                sql += " and CategoryID = ?";
                }
            }

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(sql_param_counter, "%" + keyword + "%");
            sql_param_counter += 1;

            if (categoryID != null) {
                if (!categoryID.trim().equals("")){
                ps.setString(sql_param_counter, categoryID);
                sql_param_counter += 1;
                }
            }

            ResultSet rs = ps.executeQuery();
            pro = parseResultSetToArray(rs);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return pro;
    }
    
        public ArrayList<Product> getProductByKeywordPaging(String keyword, HashMap<String, String> filter, int page, int elements) {
        ArrayList<Product> pro = new ArrayList<>();
        int sql_param_counter = 1;
        int start = page * elements - elements;
        try {
            String sql = "select * from Products\n"
                    + "where ProductName like ?";

            String categoryID = filter.get("CategoryID");
            if (categoryID != null) {
                if (!categoryID.trim().equals("")){
                sql += " and CategoryID = ?";
                }
            }
            
            sql += "\n";
            
            sql += "order by ProductID\n" 
                    + "offset ? rows\n"
                    + "fetch next ? rows only";
            
            System.out.println(keyword);
            System.out.println(categoryID);
            System.out.println(start);
            System.out.println(elements);
            System.out.println();
            
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(sql_param_counter, "%" + keyword + "%");
            sql_param_counter += 1;

            if (categoryID != null) {
                if (!categoryID.trim().equals("")){
                ps.setString(sql_param_counter, categoryID);
                sql_param_counter += 1;
                }
            }
            
            ps.setInt(sql_param_counter, start);
            sql_param_counter += 1;
            ps.setInt(sql_param_counter, elements);
            sql_param_counter += 1;
            
            ResultSet rs = ps.executeQuery();
            pro = parseResultSetToArray(rs);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return pro;
    }

    public int createProduct(Product p) {
        int result = 0;
        try {
            String sql = "insert into Products(ProductName, CategoryID, QuantityPerUnit, UnitPrice, \n"
                    + "	UnitsInStock, ReorderLevel, Discontinued)\n"
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
//            ps.setInt(5, p.getUnitsOnOrder());
            ps.setInt(6, p.getReorderLevel());
            ps.setBoolean(7, p.isDiscontinued());

            result = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        if (result != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int editProduct(Product p) {
        int result = 0;
        try {
            String sql = "update Products set ProductName=?, CategoryID=?, QuantityPerUnit=?, UnitPrice=?, \n"
                    + "UnitsInStock=?, UnitsOnOrder=?, ReorderLevel=?, Discontinued=? where ProductID=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCategoryID());
            ps.setString(3, p.getQuantityPerUnit());
            ps.setDouble(4, p.getUnitPrice());
            ps.setInt(5, p.getUnitsInStock());
            ps.setInt(6, p.getUnitsOnOrder());
            ps.setInt(7, p.getReorderLevel());
            ps.setBoolean(8, p.isDiscontinued());
            ps.setInt(9, p.getProductID());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (result != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void editProductWithoutCategoryID(Product p) {
        try {
            String sql = "update Products set ProductName=?, QuantityPerUnit=?, UnitPrice=?, \n"
                    + "UnitsInStock=?, UnitsOnOrder=?, ReorderLevel=?, Discontinued=? where ProductID=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setString(2, p.getQuantityPerUnit());
            ps.setDouble(3, p.getUnitPrice());
            ps.setInt(4, p.getUnitsInStock());
            ps.setInt(5, p.getUnitsOnOrder());
            ps.setInt(6, p.getReorderLevel());
            ps.setBoolean(7, p.isDiscontinued());
            ps.setInt(8, p.getProductID());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteProductById(int ProductID) {
        try {
            String sql1 = "delete from \"Order Details\" where ProductID=?";
            String sql2 = "delete from Products where ProductID = ?";

            PreparedStatement ps1 = connection.prepareStatement(sql1);
            PreparedStatement ps2 = connection.prepareStatement(sql2);

            ps1.setInt(1, ProductID);
            ps1.executeUpdate();

            ps2.setInt(1, ProductID);
            ps2.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Product> getProductHot() {
        ArrayList<Product> proHot = new ArrayList<>();
        try {
            String sql = "select p.*, od.Discount\n"
                    + "from Products p, [Order Details] od\n"
                    + "where p.ProductID = od.ProductID and p.Discontinued = 1\n"
                    + "group by p.ProductID, p.ProductName, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, \n"
                    + "	p.UnitsInStock, p.UnitsOnOrder, p.ReorderLevel, p.Discontinued, od.Discount\n"
                    + "order by od.Discount desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            proHot = parseResultSetToArray(rs);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return proHot;
    }

    public ArrayList<Product> getProductBestSale() {
        ArrayList<Product> proBest = new ArrayList<>();
        try {
            String sql = "select p.*, COUNT(*) as Total\n"
                    + "from Products p, [Order Details] od\n"
                    + "where p.ProductID = od.ProductID and p.Discontinued = 1\n"
                    + "group by p.ProductID, p.ProductName, p.CategoryID, p.QuantityPerUnit, p.UnitPrice, \n"
                    + "	p.UnitsInStock, p.UnitsOnOrder, p.ReorderLevel, p.Discontinued\n"
                    + "order by Total desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            proBest = parseResultSetToArray(rs);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return proBest;
    }

    public ArrayList<Product> getProductNew() {
        ArrayList<Product> proNew = new ArrayList<>();
        try {
            String sql = " select * from Products order by ProductID desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            proNew = parseResultSetToArray(rs);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return proNew;
    }

    public ArrayList<Product> getProductByCategoryId(int CategoryID) {
        ArrayList<Product> proList = new ArrayList<>();
        try {
            String sql = "select * from Products where CategoryID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, CategoryID);
            ResultSet rs = ps.executeQuery();

            proList = parseResultSetToArray(rs);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return proList;
    }

    public ArrayList<Product> getProductsByPage(int page, int elements) {
        ArrayList<Product> product = new ArrayList<>();
        int start = page * elements - elements;
        try {
            String sql = "select * from Products\n"
                    + "order by ProductID\n"
                    + "offset ? rows\n"
                    + "fetch next ? rows only";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, elements);
            ResultSet rs = ps.executeQuery();
            product = parseResultSetToArray(rs);
        } catch (Exception e) {
        }
        return product;
    }

    public static void main(String[] args) {
        int count = 0;

        HashMap<String, String> filters = new HashMap<>();
//        filters.put("CategoryID", "1");

//        ArrayList<Product> list = new ProductDAO().getProductByKeyword("ch", filters);
        ArrayList<Product> list = new ProductDAO().getProductByKeywordPaging("d", filters, 1, 2);
        
        for (Product product : list) {
            System.out.println(product);
        }
        System.out.println("================================");
//        ArrayList<Product> list2 = new ProductDAO().getProductsByPage(1, 10);
//        for (Product product : list2) {
//            System.out.println(product);
//        }
    }
}
