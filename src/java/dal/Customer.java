package dal;

public class Customer {

    private String customerID;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;

    public Customer(String CustomerID, String CompanyName, String ContactName, String ContactTitle, String Address) {
        this.customerID = CustomerID;
        this.companyName = CompanyName;
        this.contactName = ContactName;
        this.contactTitle = ContactTitle;
        this.address = Address;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String CustomerID) {
        this.customerID = CustomerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String CompanyName) {
        this.companyName = CompanyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String ContactName) {
        this.contactName = ContactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String ContactTitle) {
        this.contactTitle = ContactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String Address) {
        this.address = Address;
    }

    @Override
    public String toString() {
        return "Customer{" + "CustomerID=" + customerID + ", CompanyName=" + companyName + ", ContactName=" + contactName + ", ContactTitle=" + contactTitle + ", Address=" + address + '}';
    }
}
