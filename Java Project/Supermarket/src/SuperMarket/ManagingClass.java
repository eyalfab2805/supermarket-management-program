package SuperMarket;

public class ManagingClass {
    public Seller[] sellers = new Seller[0];
    public Customer[] customers = new Customer[0];
    public int sellerCount = 0; // Logical size for sellers
    public int customerCount = 0; // Logical size for customers

    // Method to resize the seller array
    public void resizeSellerArray() {
        int new_size = 0;
        new_size = sellers.length == 0 ? 1 : sellers.length * 2;
        Seller[] newArray = new Seller[new_size];
        System.arraycopy(sellers, 0, newArray, 0, sellers.length);
        sellers = newArray;
    }

    // Method to resize the customer array
    public void resizeCustomerArray() {
        int new_size = 0;
        new_size = customers.length == 0 ? 1 : customers.length * 2;
        Customer[] newArray = new Customer[new_size];
        System.arraycopy(customers, 0, newArray, 0, customers.length);
        customers = newArray;
    }

    // Method to add a seller
    public void addSeller(Seller seller) {
        if (sellerCount == sellers.length) {
            resizeSellerArray();
        }
        sellers[sellerCount++] = seller;
    }

    // Method to add a customer
    public void addCustomer(Customer customer) {
        if (customerCount == customers.length) {
            resizeCustomerArray();
        }
        customers[customerCount++] = customer;
    }

    // Method to check if seller username exists
    public boolean sellerUsernameExists(String username) {
        for (int i = 0; i < sellerCount; i++) {
            if (sellers[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Method to check if customer username exists
    public boolean customerUsernameExists(String username) {
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}







