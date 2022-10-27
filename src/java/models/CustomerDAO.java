package models;

import dal.DBContext;
import dal.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO extends DBContext {

    public ArrayList<Customer> getCustomer() {
        ArrayList<Customer> cus = new ArrayList<>();
        try {
            String sql = "select * from Customers";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String CustomerID = rs.getString(1);
                String CompanyName = rs.getString(2);
                String ContactName = rs.getString(3);
                String ContactTitle = rs.getString(4);
                String Address = rs.getString(5);

                Customer c = new Customer(CustomerID, CompanyName, ContactName, ContactTitle, Address);
                cus.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return cus;
    }

    public Customer getCustomerById(String CustomerID) {
        try {
            String sql = "select * from Customers where CustomerID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, CustomerID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String CustomerID2 = rs.getString(1);
                String CompanyName = rs.getString(2);
                String ContactName = rs.getString(3);
                String ContactTitle = rs.getString(4);
                String Address = rs.getString(5);

                Customer c = new Customer(CustomerID2, CompanyName, ContactName, ContactTitle, Address);
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int createProfile(Customer c) {
        int result = 0;
        try {
            String sql = "insert into Customers values (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, c.getCustomerID());
            ps.setString(2, c.getCompanyName());
            ps.setString(3, c.getContactName());
            ps.setString(4, c.getContactTitle());
            ps.setString(5, c.getAddress());

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

    public int editProfile(Customer c) {
        int result = 0;
        try {
            String sql = "update Customers set CompanyName=?, ContactName=?, \n"
                    + "ContactTitle=?, Address=? where CustomerID=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, c.getCompanyName());
            ps.setString(2, c.getContactName());
            ps.setString(3, c.getContactTitle());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getCustomerID());

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

    public static void main(String[] args) {
        String CustomerID = "BFJQO";
        System.out.println(new CustomerDAO().getCustomerById(CustomerID));
        System.out.println("=============================================================================");
        ArrayList<Customer> list = new CustomerDAO().getCustomer();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        System.out.println("====================================");
        Customer c = new CustomerDAO().getCustomerById(CustomerID);
        c.setAddress("Hà Nam");
        int i = new CustomerDAO().editProfile(c);
        System.out.println("i = " + i);
        System.out.println(c);
        System.out.println("======================================");
        Customer c2 = new Customer("ABCDE", "fpt", "new name", "unknown", "no way home");
        System.out.println(new CustomerDAO().createProfile(c2));
    }
}
