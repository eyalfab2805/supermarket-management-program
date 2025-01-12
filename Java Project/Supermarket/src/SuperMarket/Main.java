//     * Authors:
//     * 2) NAME: Eyal Fabian, ID: 212067490 LECTURER: Tal Pasternak

package SuperMarket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static public String menu = "Please select your choice:\n 0 - Exit\n 1 - Add a seller"
            + "\n 2 - Add a customer\n 3 - Add a product to seller\n 4 - Add a product to shopping cart\n "
            + "5 - Pay for cart\n 6 - Show all customers\n 7 - Show all sellers\n 8" +
            " - Display Products by Category\n 9 - Load products from an older cart";
    public static Scanner scanner = new Scanner(System.in);
    static ManagingClass SystemManager = new ManagingClass();

    // Handle creating a seller considering already exist
    public static void createSeller() {
        String username;
        String password;

        while (true) {
            try {
                System.out.print("Enter seller username: ");
                username = scanner.nextLine();

                // Validate the username
                if (username.trim().isEmpty()) {
                    throw new InvalidInputException("Username cannot be empty or whitespace only.");
                }

                if (!SystemManager.sellerUsernameExists(username)) {
                    while (true) {
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();

                        // Validate the password
                        if (password.trim().isEmpty()) {
                            System.out.println("Password cannot be empty or whitespace only.");
                        } else {
                            break;
                        }
                    }
                    Seller seller = new Seller(username, password);
                    SystemManager.addSeller(seller);
                    break;
                } else {
                    System.out.println("Username already exists. Please enter a different username.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Handle creating a customer considering already exist
    public static void createCustomer() {
        String username;
        String password;
        String street;
        String city;
        String state;
        int number = 0; // Initialize number outside the loop

        while (true) {
            try {
                System.out.print("Enter customer username: ");
                username = scanner.nextLine();

                // Validate the username
                if (username.trim().isEmpty()) {
                    throw new InvalidInputException("Username cannot be empty or whitespace only.");
                }

                if (!SystemManager.customerUsernameExists(username)) {
                    while (true) {
                        System.out.print("Enter password: ");
                        password = scanner.nextLine();

                        // Validate the password
                        if (password.trim().isEmpty()) {
                            System.out.println("Password cannot be empty or whitespace only.");
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        System.out.print("Enter street: ");
                        street = scanner.nextLine();
                        if (street.trim().isEmpty()) {
                            System.out.println("Street cannot be empty or whitespace only.");
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        System.out.print("Enter city: ");
                        city = scanner.nextLine();
                        if (city.trim().isEmpty()) {
                            System.out.println("City cannot be empty or whitespace only.");
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        System.out.print("Enter state: ");
                        state = scanner.nextLine();
                        if (state.trim().isEmpty()) {
                            System.out.println("State cannot be empty or whitespace only.");
                        } else {
                            break;
                        }
                    }

                    // Ask for house number separately and validate
                    boolean validHouseNumber = false;
                    while (!validHouseNumber) {
                        try {
                            System.out.print("Enter apartment number: ");
                            number = InputValidator.validateInt(scanner.nextLine());
                            validHouseNumber = true; // If validation passes, exit the loop
                        } catch (InvalidInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    Customer customer = new Customer(username, password, street, city, state, number);
                    SystemManager.addCustomer(customer);
                    break;
                } else {
                    System.out.println("Username already exists. Please enter a different username.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // Handle adding a product to seller
    private static void addProduct() {
        if (SystemManager.sellerCount == 0) {
            System.out.println("No sellers available.");
            return;
        }

        while (true) {
            displaySellers();

            int sellerIndex = 0;
            while (true) {
                try {
                    System.out.print("Select a seller by number: ");
                    String input = scanner.nextLine();
                    sellerIndex = InputValidator.validateInt(input) - 1;
                    break;
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (sellerIndex >= 0 && sellerIndex < SystemManager.sellerCount) {
                Seller seller = SystemManager.sellers[sellerIndex];

                String productName;
                while (true) {
                    System.out.print("Enter product name: ");
                    productName = scanner.nextLine();
                    // Validate the product name
                    if (productName.trim().isEmpty()) {
                        System.out.println("Product name cannot be empty or whitespace only.");
                    } else {
                        break;
                    }
                }

                double price = 0.0;
                while (true) {
                    try {
                        System.out.print("Enter product price: ");
                        String input = scanner.nextLine();
                        price = InputValidator.validateDouble(input);
                        // Validate the price
                        if (price <= 0) {
                            System.out.println("Product price must be a positive number.");
                        } else {
                            break;
                        }
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                    }
                }

                int categoryIndex = -1;
                Category[] categories = Category.values();
                while (true) {
                    try {
                        System.out.println("Select product category:");
                        for (int i = 0; i < categories.length; i++) {
                            System.out.println((i + 1) + ". " + categories[i]);
                        }
                        String input = scanner.nextLine();
                        categoryIndex = InputValidator.validateInt(input) - 1;

                        if (categoryIndex >= 0 && categoryIndex < categories.length) {
                            break;
                        } else {
                            System.out.println("Invalid category selection. Please try again.");
                        }
                    } catch (InvalidInputException e) {
                        System.out.println(e.getMessage());
                    }
                }

                Category category = categories[categoryIndex];

                double specialPackagingCost = 0.0;
                while (true) {
                    System.out.print("Does this product need special packaging? (yes/no): ");
                    String specialPackagingInput = scanner.nextLine().trim().toLowerCase();
                    if (specialPackagingInput.equals("yes")) {
                        // Ask for special packaging cost
                        while (true) {
                            try {
                                System.out.print("Enter special packaging cost: ");
                                specialPackagingCost = InputValidator.validateDouble(scanner.nextLine());
                                // Validate the cost
                                if (specialPackagingCost > 0) {
                                    break;
                                } else {
                                    System.out.println("Special packaging cost must be a positive double.");
                                }
                            } catch (InvalidInputException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        break;
                    } else if (specialPackagingInput.equals("no")) {
                        specialPackagingCost = 0;
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    }
                }

                seller.addProduct(productName, price, category, specialPackagingCost);


                System.out.println("Product added successfully.");
                break;
            } else {
                System.out.println("Invalid seller selection. Please try again.");
            }
        }
    }

    // Handle adding an existing product to costumer's cart
    public static void addProductToCart() {
        if (SystemManager.customerCount == 0) {
            System.out.println("No customers available.");
            return;
        }

        if (SystemManager.sellerCount == 0) {
            System.out.println("No sellers available.");
            return;
        }

        boolean anySellerHasProducts = false;
        for (int i = 0; i < SystemManager.sellerCount; i++) {
            if (SystemManager.sellers[i].productCount > 0) {
                anySellerHasProducts = true;
                break;
            }
        }

        if (!anySellerHasProducts) {
            System.out.println("No sellers with products available.");
            return;
        }

        while (true) {
            displayCustomers();

            int customerIndex = 0;
            while (true) {
                try {
                    System.out.print("Select a customer by number: ");
                    String input = scanner.nextLine();
                    customerIndex = InputValidator.validateInt(input) - 1;
                    break;
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (customerIndex >= 0 && customerIndex < SystemManager.customerCount) {
                Customer customer = SystemManager.customers[customerIndex];
                while (true) {
                    displaySellers();

                    int sellerIndex = 0;
                    while (true) {
                        try {
                            System.out.print("Select a seller by number: ");
                            String input = scanner.nextLine();
                            sellerIndex = InputValidator.validateInt(input) - 1;
                            break;
                        } catch (InvalidInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (sellerIndex >= 0 && sellerIndex < SystemManager.sellerCount) {
                        Seller currentSeller = SystemManager.sellers[sellerIndex];
                        if (currentSeller.productCount == 0) {
                            System.out.println("This seller has no products available.");
                            continue; // Return to seller selection
                        }
                        while (true) {
                            System.out.print("Select the product by number: \n");
                            displayProducts(currentSeller);

                            int productIndex = 0;
                            while (true) {
                                try {
                                    System.out.print("Enter product number: ");
                                    String input = scanner.nextLine();
                                    productIndex = InputValidator.validateInt(input) - 1;

                                    if (productIndex >= 0 && productIndex < currentSeller.productCount) {
                                        customer.addProductToCart(currentSeller.getProduct(productIndex));
                                        System.out.println("Product added to cart successfully.");
                                        break;
                                    } else {
                                        System.out.println("Invalid product number. Please try again.");
                                    }
                                } catch (InvalidInputException e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                            break;
                        }
                        break;
                    } else {
                        System.out.println("Invalid seller selection. Please try again.");
                    }
                }
                break;
            } else {
                System.out.println("Invalid customer selection. Please try again.");
            }
        }
    }

    // Handle paying and checkout for current costumer's cart
    public static void PayForCart() {
        if (SystemManager.customerCount == 0) {
            System.out.println("There are no customers available.");
            return; // Exit the method if there are no customers
        }

        while (true) {
            displayCustomers();
            int customerIndex = 0;
            while (true) {
                try {
                    System.out.print("Select a customer by number: ");
                    String input = scanner.nextLine();
                    customerIndex = InputValidator.validateInt(input) - 1;
                    break;
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }

            if (customerIndex >= 0 && customerIndex < SystemManager.customerCount) {
                Customer customer = SystemManager.customers[customerIndex];
                try {
                    checkOut(customer);
                    break;
                } catch (EmptyCartException e) {
                    System.out.println(e.getMessage());
                    return;
                }
            } else {
                System.out.println("Invalid customer selection. Please try again.");
            }
        }
    }

    // Display basic sellers list
    public static void displaySellers() {
        System.out.println("Sellers:");
        for (int i = 0; i < SystemManager.sellerCount; i++) {
            System.out.println((i + 1) + ". " + SystemManager.sellers[i].getUsername());
        }
    }

    // Handle displaying sellers with additional details
    public static void displaySellersWithProducts() {
        if (SystemManager.sellerCount == 0) {
            System.out.println("No sellers available.");
            return;
        }

        System.out.println("Sellers:");

        // Create a copy of the sellers array and sort it
        Seller[] sellersCopy = Arrays.copyOf(SystemManager.sellers, SystemManager.sellerCount);
        Arrays.sort(sellersCopy, (s1, s2) -> Integer.compare(s2.productCount, s1.productCount));

        // Print the sorted copy
        for (int i = 0; i < sellersCopy.length; i++) {
            System.out.println((i + 1) + ". " + sellersCopy[i].getUsername() + " (Products: " + sellersCopy[i].productCount + ")");
            displayProducts(sellersCopy[i]);
        }
    }

    // Display basic customers list
    public static void displayCustomers() {
        System.out.println("Customers:");
        for (int i = 0; i < SystemManager.customerCount; i++) {
            System.out.println((i + 1) + ". " + SystemManager.customers[i].getUsername());
        }
    }

    // Display customers list with their current cart and carts history
    public static void displayCustomerWithOrders() {
        if (SystemManager.customerCount == 0) {
            System.out.println("No customers available.");
            return;
        }

        System.out.println("Customers:");

        // Create a copy of the customers array and Sort the copy by username in descending order
        Customer[] customersCopy = Arrays.copyOf(SystemManager.customers, SystemManager.customerCount);
        Arrays.sort(customersCopy, (c1, c2) -> c2.getUsername().compareTo(c1.getUsername()));

        // Print the sorted copy with additional details
        for (int i = 0; i < customersCopy.length; i++) {
            System.out.println((i + 1) + ". " + customersCopy[i].getUsername());
            System.out.println(customersCopy[i].customerAddress.addressToString());
            System.out.println("Current shopping cart: ");
            printShoppingCart(customersCopy[i]);
            System.out.println("Order history: ");

            // Display carts history
            if (customersCopy[i].shoppingHistoryArray.length == 0) {
                System.out.println("No history.\n");
            } else {
                for (int j = 0; j < customersCopy[i].shoppingHistoryArray.length; j++) {
                    if (customersCopy[i].shoppingHistoryArray[j] != null) {  // Null check added here
                        System.out.println(customersCopy[i].shoppingHistoryArray[j].orderToString());
                    }
                }
            }
        }
    }

    // Print current customer's shopping cart
    public static void printShoppingCart(Customer customer) {
        if (customer.shoppingCartArray == null || customer.shoppingCartArray.length == 0) {
            System.out.println("No shopping cart.");
        } else {
            for (Product product : customer.shoppingCartArray) {
                if (product != null) {
                    System.out.println(product.toString());
                }
            }
        }
    }

    // Method to display products of a seller
    public static void displayProducts(Seller seller) {
        System.out.println("Products of " + seller.username + ":");
        if (seller.productCount == 0) {
            System.out.println("Nothing to sell");
        } else {
            for (int i = 0; i < seller.productCount; i++) {
                System.out.println((i + 1) + ". " + seller.products[i].toString());
            }
            System.out.println();
        }
    }

    // Handle customer checkout with cart
    public static void checkOut(Customer customer) throws EmptyCartException {
        if (customer.shoppingCartArray.length == 0 || customer.shoppingCartArray[0] == null) {
            throw new EmptyCartException("The cart is empty. Unable to proceed with checkout.");
        }

        double totalPrice = 0;
        for (int i = 0; i < customer.shoppingCartArray.length; ++i) {
            if (customer.shoppingCartArray[i] != null) {
                Product product = customer.shoppingCartArray[i];
                double productPrice = product.getPrice();
                System.out.print((i + 1) + ". " + product.toString());
                if (product.hasSpecialPackaging()) {
                    productPrice += product.specialPackagingCost;
                }
                System.out.println();
                totalPrice += productPrice;
            } else {
                break;
            }
        }
        System.out.println("Total price is: " + totalPrice + "$");
        String formatDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        customer.addToHistoryArray(customer.shoppingCartArray, totalPrice, formatDate);
        customer.clearShoppingCart();
    }

    // Handle displaying products by selected category
    public static void displayProductsByCategory() {
        while (true) {
            System.out.println("Select product category:");
            for (Category category : Category.values()) {
                System.out.println((category.ordinal() + 1) + ". " + category);
            }

            int categoryIndex = -1;
            Category[] categories = Category.values();
            while (true) {
                try {
                    System.out.print("Enter category number: ");
                    String input = scanner.nextLine();
                    categoryIndex = InputValidator.validateInt(input) - 1;

                    if (categoryIndex >= 0 && categoryIndex < categories.length) {
                        break;
                    } else {
                        System.out.println("Invalid category selection. Please try again.");
                    }
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }

            Category selectedCategory = categories[categoryIndex];

            boolean found = false;

            for (int i = 0; i < SystemManager.sellerCount; i++) {
                Seller seller = SystemManager.sellers[i];
                for (int j = 0; j < seller.productCount; j++) {
                    Product product = seller.getProduct(j);
                    if (product.getCategory() == selectedCategory) {
                        System.out.println("Seller: " + seller.getUsername() + " - " + product);
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("No products found in the selected category.");
            }

            System.out.print("Do you want to search for another category? (yes/no): ");
            String continueSearch = scanner.nextLine().trim().toLowerCase();
            if (!continueSearch.equals("yes")) {
                break;
            }
        }
    }

    // Handle loading cart from a customer's history
    private static void handleCreateNewCartFromHistory() {
        if (SystemManager.customerCount == 0) {
            System.out.println("There are no customers available.");
            return; // Exit the method if there are no customers
        }

        while (true) {
            try {
                displayCustomers();
                System.out.print("Select customer number: ");
                String input = scanner.nextLine();
                int customerIndex = InputValidator.validateInt(input) - 1;

                if (customerIndex >= 0 && customerIndex < SystemManager.customerCount) {
                    Customer customer = SystemManager.customers[customerIndex];
                    createNewCartFromHistory(customer);
                    break;
                } else {
                    System.out.println("Invalid customer selection.");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid index. Please select a valid customer number.");
            }
        }
    }

    // Load products from cart in history of customer
    private static void createNewCartFromHistory(Customer customer) {
        if (customer.isHistoryEmpty()) {
            System.out.println("Your cart history is empty.");
            return;
        }

        System.out.println("Select a cart from history to load:");
        for (int i = 0; i < customer.getOrderHistoryCounter(); i++) {
            System.out.println((i + 1) + ". Cart from " + customer.shoppingHistoryArray[i].GetDate() + " - Total price: " + customer.shoppingHistoryArray[i].GetPrice() + "$");
        }

        int historyIndex = 0;
        while (true) {
            try {
                System.out.print("Enter the number of the history order: ");
                String input = scanner.nextLine();
                historyIndex = InputValidator.validateInt(input) - 1;
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        if (historyIndex >= 0 && historyIndex < customer.getOrderHistoryCounter()) {
            Product[] selectedOrder = customer.shoppingHistoryArray[historyIndex].GetProducts();
            customer.clearShoppingCart();
            for (Product product : selectedOrder) {
                customer.addProductToCart(product);
            }
            System.out.println("Cart has been loaded from history.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    // Main
    public static void main(String[] args) {
    String systemName = "TomEyal - E-Commerce system"; // Name of the system
        // Show menu and let user choose options to commit
        System.out.println("Welcome to " + systemName);
        int userChoice;
        do {
            System.out.println(menu);
            try {
                userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline
                switch (userChoice) {
                    case 0:
                        scanner.close();
                        System.out.println("Exiting...");
                        break;
                    case 1:
                        // Create a new seller
                        createSeller();
                        break;
                    case 2:
                        // Create a new customer
                        createCustomer();
                        break;
                    case 3:
                        // Add a product to seller
                        addProduct();
                        break;
                    case 4:
                        // Add a product to cart
                        addProductToCart();
                        break;
                    case 5:
                        // Pay for cart (all checkout processes)
                        PayForCart();
                        break;
                    case 6:
                        // Display customers
                        displayCustomerWithOrders();
                        break;
                    case 7:
                        // Display sellers
                        displaySellersWithProducts();
                        break;
                    case 8:
                        // Display Products by Category
                        displayProductsByCategory();
                        break;
                    case 9:
                        handleCreateNewCartFromHistory();
                        break;
                    default:
                        System.out.println("Invalid number. Please select a valid option from the menu.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                userChoice = -1; // Set to a non-exit value to continue the loop
            }
        } while (userChoice != 0);
    }
}
    