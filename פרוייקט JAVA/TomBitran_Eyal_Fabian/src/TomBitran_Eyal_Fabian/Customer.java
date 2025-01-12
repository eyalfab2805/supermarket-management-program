package TomBitran_Eyal_Fabian;

public class Customer extends User implements Comparable<Customer>{
    // Constructor
    public Product[] shoppingCartArray = new Product[0];
    public Order[] shoppingHistoryArray = new Order[0];
    private int orderHistoryCounter = 0;
    public int productCartCount = 0;
    public Address customerAddress;

    // Constructor
    public Customer(String username, String password, String street, String City, String State, int Number) {
        super(username, password);
        customerAddress = new Address(street, City, State, Number);

    }

    // Resize product cart array if necessary
    private void resizeProductCartArray() {
        int new_size = 0;
        new_size = shoppingCartArray.length == 0 ? 1 : shoppingCartArray.length * 2;
        Product[] newArray = new Product[new_size];
        System.arraycopy(shoppingCartArray, 0, newArray, 0, shoppingCartArray.length);
        shoppingCartArray = newArray;
    }

    // Checks if no orders in history array
    public boolean isHistoryEmpty(){
        return shoppingHistoryArray.length == 0;
    }

    // Returns number of orders in history
    public int getOrderHistoryCounter(){
        return orderHistoryCounter;
    }
    // Create a shopping cart
    public void addProductToCart(Product product) {
            if (productCartCount == shoppingCartArray.length)
            {
                resizeProductCartArray();
            }
            shoppingCartArray[productCartCount++] = product;
    }

    // Resize product history array if necessary
    private void resizeHistoryArray() {
        int new_size = 0;
        new_size = shoppingHistoryArray.length == 0 ? 1 : shoppingHistoryArray.length * 2;
        Order[] newArray = new Order[new_size];
        System.arraycopy(shoppingHistoryArray, 0, newArray, 0, shoppingHistoryArray.length);
        shoppingHistoryArray = newArray;
    }

    // Add cart to history
    public void addToHistoryArray(Product[] product,double price,String date) {
        if (orderHistoryCounter == shoppingHistoryArray.length)
        {
            resizeHistoryArray();
        }
        shoppingHistoryArray[orderHistoryCounter] = new Order();
        shoppingHistoryArray[orderHistoryCounter].SetPrice(price);
        shoppingHistoryArray[orderHistoryCounter].SetProducts(product);
        orderHistoryCounter++;
    }

    // Clean shopping cart
    public void clearShoppingCart() {
        this.shoppingCartArray = new Product[0];
        productCartCount = 0;
    }

    // Implementation of compareTo
    @Override
    public int compareTo(Customer other) {
        return this.getUsername().compareTo(other.getUsername());
    }
}
