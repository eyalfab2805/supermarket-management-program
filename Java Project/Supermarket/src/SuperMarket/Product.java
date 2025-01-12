package SuperMarket;

public class Product {
    private static int nextSerialNumber = 1; // Static variable to keep track of the next serial number
    private final int serialNumber; // Instance variable to store the serial number of the product
    private String name;
    private double price;
    private Category category; // Instance variable to store the category of the product
    public double specialPackagingCost = 0;

    // Constructor
    public Product(String name, double price, Category category, double specialPackagingCost) {
        this.serialNumber = nextSerialNumber++; // Assign the current serial number and increment the static variable
        this.name = name;
        this.price = price;
        this.category = category;
        this.specialPackagingCost = specialPackagingCost;
    }

    // Copy constructor
    public Product(Product other) {
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null product.");
        }
        this.serialNumber = other.serialNumber; // Assign the same serial number
        this.name = other.name;
        this.price = other.price;
        this.category = other.category;
        this.specialPackagingCost = other.specialPackagingCost;
    }

    // Get product Name
    public String getName() {
        return name;
    }

    // Get product price
    public double getPrice() {
        return price;
    }

    // Get product category
    public Category getCategory() {
        return category;
    }

    // Set product name
    public void setName(String name) {
        this.name = name;
    }

    // Set product price
    public void setPrice(double price) {
        this.price = price;
    }

    // Set product category
    public void setCategory(Category category) {
        this.category = category;
    }
    // Set special Packaging cost
    public void setSpecialPackagingCost(double specialPackaging_Cost) {
        this.specialPackagingCost = specialPackaging_Cost;
    }

    // Get special packaging flag
    public boolean hasSpecialPackaging() {
        return specialPackagingCost != 0.0;
    }

    // Get product serial number
    public int getSerialNumber() {
        return serialNumber;
    }

    // Convert to a printable string
    public String toString() {
        return "Serial Number: " + serialNumber + ", Name: " + name + ", Price: " + price + "$, Category: " + category +
                (specialPackagingCost != 0.0 ? ", +" : "") + (specialPackagingCost != 0.0 ? specialPackagingCost : "") + (specialPackagingCost != 0.0 ? "$ special packaging" : "");
    }
}
