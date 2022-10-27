package dal;

public class Account {

    private int accountID;
    private String email;
    private String password;
    private String customerID;
    private int employeeID;
    private int role;

    public Account(int AccountID, String Email, String Password, String CustomerID, int EmployeeID, int Role) {
        this.accountID = AccountID;
        this.email = Email;
        this.password = Password;
        this.customerID = CustomerID;
        this.employeeID = EmployeeID;
        this.role = Role;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int AccountID) {
        this.accountID = AccountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        this.password = Password;
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

    public int getRole() {
        return role;
    }

    public void setRole(int Role) {
        this.role = Role;
    }

    @Override
    public String toString() {
        return "Account{" + "AccountID=" + accountID + ", Email=" + email + ", Password=" + password + ", CustomerID=" + customerID + ", EmployeeID=" + employeeID + ", Role=" + role + '}';
    }
}
