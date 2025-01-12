package SuperMarket;

import java.time.LocalDate;

public class Order {
    private final LocalDate currentDate = LocalDate.now();
    private Product[] products;
    private double totalPrice;

     //Returns the array of products in the order.
    public Product[] GetProducts() {
        return products;
    }

    // Returns the total price of the order.
    public double GetPrice() {
        return totalPrice;
    }


    // Returns the date of the order.
    public LocalDate GetDate() {
        return currentDate;
    }

    // Sets the products in the order.
    public void SetProducts(Product[] products) {
        if (products == null) {
            this.products = null;
        } else {
            this.products = new Product[products.length];
            for (int i = 0; i < products.length; i++) {
                if (products[i] == null) {
                    break;
                }
                else {
                    this.products[i] = new Product(products[i]);
                }
            }
        }
    }

    //Sets the total price of the order.
    public void SetPrice(double price) {
        this.totalPrice = price;
    }

    //Converts order details to a printable string.
    public String orderToString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Date of order: ").append(this.GetDate()).append("\n");

        if (this.GetProducts() == null || this.GetProducts().length == 0 || this.GetProducts()[0] == null) {
            orderDetails.append("No Products in this order\n");
        } else {
            for (int i = 0; i < this.GetProducts().length; i++) {
                if (this.GetProducts()[i] == null) {
                    break;
                } else {
                    orderDetails.append("Product: ").append(this.GetProducts()[i].getName()).append(" - ")
                            .append(this.GetProducts()[i].getPrice()).append("$");

                    if (this.GetProducts()[i].hasSpecialPackaging()) {
                        orderDetails.append(" +");
                        orderDetails.append(GetProducts()[i].specialPackagingCost);
                        orderDetails.append("$ special packaging");
                    }

                    orderDetails.append("\n");
                }
            }
        }

        orderDetails.append("The total price is: ").append(this.GetPrice()).append("$").append("\n");
        return orderDetails.toString();
    }
}