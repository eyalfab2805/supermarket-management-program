package TomBitran_Eyal_Fabian;

public class Seller extends User implements Comparable<Seller>{
    public Product[] products = new Product[0];
    public int productCount = 0; // Logical size for products

    // Constructor
    public Seller(String username, String password) {
        super(username, password);
    }

    // Method to resize the product array
    private void resizeProductArray() {
        int new_size = 0;
        new_size = products.length == 0 ? 1 : products.length * 2;
        Product[] newArray = new Product[new_size];
        System.arraycopy(products, 0, newArray, 0, products.length);
        products = newArray;
    }

    // Method to add a product with category
    public void addProduct(String name, double price, Category category, double specialPackagingCost) {
        if (productCount == products.length) {
            resizeProductArray();
        }
        products[productCount++] = new Product(name, price, category, specialPackagingCost);
    }

    // Get product with index
    public Product getProduct(int index) {
        return new Product(products[index]);
    }

    // Implementation of compareTo
    @Override
    public int compareTo(Seller other) {
        return Integer.compare(other.productCount, this.productCount);
    }
}
