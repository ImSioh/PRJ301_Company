/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import dal.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class EmployeeDAO extends DBContext {

    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> emp = new ArrayList<>();
        try {
            String sql = "select * from Employees";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                int departmentID = rs.getInt("DepartmentID");
                String title = rs.getString("Title");
                String titleOfCourtesy = rs.getString("TitleOfCourtesy");
                Date birthDate = rs.getDate("BirthDate");
                Date hireDate = rs.getDate("HireDate");
                String address = rs.getString("Address");

                Employee e = new Employee(employeeID, lastName, firstName, departmentID, title, titleOfCourtesy, birthDate, hireDate, address);
                emp.add(e);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return emp;
    }

    public Employee getEmployeeById(int EmployeeID) {
        try {
            String sql = "select * from Employees where EmployeeID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, EmployeeID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeID2 = rs.getInt(1);
                String lastName = rs.getString(2);
                String firstName = rs.getString(3);
                int departmentID = rs.getInt(4);
                String title = rs.getString(5);
                String titleOfCourtesy = rs.getString(6);
                Date birthDate = rs.getDate(7);
                Date hireDate = rs.getDate(8);
                String address = rs.getString(9);

                Employee e = new Employee(employeeID2, lastName, firstName, departmentID, title, titleOfCourtesy, birthDate, hireDate, address);
                return e;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Employee> emp = new EmployeeDAO().getAllEmployee();
        for (Employee employee : emp) {
            System.out.println(employee);
        }
        System.out.println("=====================");
        System.out.println(new EmployeeDAO().getEmployeeById(9));
    }
}
