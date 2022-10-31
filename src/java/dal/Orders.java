/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Orders {

    private int orderID;
    private String customerID;
    private int employeeID;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private double freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    private String shipPostalCode;
    private String shipCountry;

    public Orders() {
    }

    public Orders(int OrderID, String CustomerID, int EmployeeID, Date OrderDate, Date RequiredDate, Date ShippedDate) {
        this.orderID = OrderID;
        this.customerID = CustomerID;
        this.employeeID = EmployeeID;
        this.orderDate = OrderDate;
        this.requiredDate = RequiredDate;
        this.shippedDate = ShippedDate;
    }

    public Orders(int OrderID, String CustomerID, int EmployeeID, Date OrderDate, Date RequiredDate, Date ShippedDate, double Freight, String ShipName, String ShipAddress, String ShipCity, String ShipRegion, String ShipPostalCode, String ShipCountry) {
        this.orderID = OrderID;
        this.customerID = CustomerID;
        this.employeeID = EmployeeID;
        this.orderDate = OrderDate;
        this.requiredDate = RequiredDate;
        this.shippedDate = ShippedDate;
        this.freight = Freight;
        this.shipName = ShipName;
        this.shipAddress = ShipAddress;
        this.shipCity = ShipCity;
        this.shipRegion = ShipRegion;
        this.shipPostalCode = ShipPostalCode;
        this.shipCountry = ShipCountry;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int OrderID) {
        this.orderID = OrderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String CustomerID) {
        this.customerID = CustomerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int EmployeeID) {
        this.employeeID = EmployeeID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.orderDate = OrderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Date RequiredDate) {
        this.requiredDate = RequiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Date ShippedDate) {
        this.shippedDate = ShippedDate;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double Freight) {
        this.freight = Freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String ShipName) {
        this.shipName = ShipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String ShipAddress) {
        this.shipAddress = ShipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String ShipCity) {
        this.shipCity = ShipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String ShipRegion) {
        this.shipRegion = ShipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String ShipPostalCode) {
        this.shipPostalCode = ShipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String ShipCountry) {
        this.shipCountry = ShipCountry;
    }

    public String getStatus() {
        String status = "";
        if (this.getRequiredDate() != null && this.getShippedDate() == null) {
            status = "pending";
        } else if (this.getRequiredDate() != null && this.getShippedDate() != null) {
            status = "completed";
        } else {
            status = "cancel";
        }
        return status;
    }

    @Override
    public String toString() {
        return "Orders{" + "OrderID=" + orderID + ", CustomerID=" + customerID + ", EmployeeID=" + employeeID + ", OrderDate=" + orderDate + ", RequiredDate=" + requiredDate + ", ShippedDate=" + shippedDate + ", Freight=" + freight + ", ShipName=" + shipName + ", ShipAddress=" + shipAddress + ", ShipCity=" + shipCity + ", ShipRegion=" + shipRegion + ", ShipPostalCode=" + shipPostalCode + ", ShipCountry=" + shipCountry + '}';
    }
}
