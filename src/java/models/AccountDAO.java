package models;

import dal.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO extends DBContext {

    public Account getAccount(String email, String pass) {
        Account account = null;
        try {
            String sql = "select * from Accounts where Email = ? and Password = ?";
            //B2: Tạo đối tượng PreparedStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //Set cac gia tri cho cac tham so cua sql
            ps.setString(1, email);
            ps.setString(2, pass);
            //B3: Thực thi truy vấn
            ResultSet rs = ps.executeQuery();
            //B4: Xu ly ket qua tra ve
            while (rs.next()) {
                //Lay du lieu tu 'rs' gan cho cac bien cuc bo
                int AccountID = rs.getInt("AccountID");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                String CustomerID = rs.getString("CustomerID");
                int EmployeeID = rs.getInt("EmployeeID");
                int Role = rs.getInt("Role");

                account = new Account(AccountID, Email, Password, CustomerID, EmployeeID, Role);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return account;
    }

    public Account createAccount(String email, String pass) {
        Account account = null;
        try {
            String sql = "insert into Accounts(Email, Password) values (?, ?)";
            //B2: Tạo đối tượng PreparedStatement
            PreparedStatement ps = connection.prepareStatement(sql);
            //Set cac gia tri cho cac tham so cua sql
            ps.setString(1, email);
            ps.setString(2, pass);
            //B3: Thực thi truy vấn
            ps.executeUpdate();
            //B4: Xu ly ket qua tra ve

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("Sign up successful!");
        return account;
    }

    public int AddAccount(Account acc, Customer cus) {
        int result1 = 0, result2 = 0;
        try {
            //sql
            String sql1 = "insert into Customers"
                    + "(CustomerID, CompanyName, ContactName, ContactTitle, Address) "
                    + "values(?,?,?,?,?)";
            String sql2 = "insert into Accounts"
                    + "(Email, Password, CustomerID, Role) "
                    + "values(?,?,?,?)";

            //PreparedStatement
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setString(1, cus.getCustomerID());
            ps1.setString(2, cus.getCompanyName());
            ps1.setString(3, cus.getContactName());
            ps1.setString(4, cus.getContactTitle());
            ps1.setString(5, cus.getAddress());

            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setString(1, acc.getEmail());
            ps2.setString(2, acc.getPassword());
            ps2.setString(3, cus.getCustomerID());
            ps2.setInt(4, acc.getRole());

            //executeUpdate
            result1 = ps1.executeUpdate();
            result2 = ps2.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

        if (result1 != 0 && result2 != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(new AccountDAO().getAccount("cust1@gmail.com", "123"));

//        new AccountDAO().createAccount("maiptn@gmail.com", "123");
        System.out.println(new AccountDAO().getAccount("maiptn@gmail.com", "123"));

//        Account acc = new Account(0, "mai@gmail.com", "123", RandomSQL.RandomID(), 0, 0);
//        Customer cus = new Customer(RandomSQL.RandomID(), "FPTU", "Mai", "Ms.", "HN");
//        System.out.println(new AccountDAO().AddAccount(acc, cus));
        System.out.println(new AccountDAO().getAccount("mai@gmail.com", "123"));
    }
}
