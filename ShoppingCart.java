import java.util.Stack;
import java.util.Scanner;

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - P" + price;
    }
}

public class ShoppingCart {
    private Stack<Item> cart = new Stack<>();
    private Scanner scanner = new Scanner(System.in);

    public void addItem(String name, double price) {
        Item item = new Item(name, price);
        cart.push(item);
        System.out.println(item.getName() + " added to the cart.");
    }

    public void updateItem(String oldName, String newName, double price) {
        boolean found = false;
        for (Item item : cart) {
            if (item.getName().equals(oldName)) {
                cart.remove(item); // Remove the old item with the misspelled name
                Item updatedItem = new Item(newName, price); // Create a new item with the corrected name
                cart.push(updatedItem); // Add the updated item to the cart
                found = true;
                System.out.println(oldName + " has been updated to " + newName + " in the cart.");
                break;
            }
        }
        if (!found) {
            System.out.println(oldName + " is not in the cart.");
        }
    }
    
    public void removeItem(String name) {
        boolean removed = cart.removeIf(item -> item.getName().equals(name));

        if (removed) {
            System.out.println(name + " has been removed from the cart.");
        } else {
            System.out.println(name + " is not in the cart.");
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in the cart:");
            cart.forEach(item -> System.out.println(item));
        }
    }

    public void searchCart(String keyword) {
        boolean found = false;
        for (Item item : cart) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("Found: " + item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching items found in the cart.");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : cart) {
            total += item.getPrice();
        }
        return total;
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Cart has been cleared.");
    }

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add to cart");
            System.out.println("2. Update cart");
            System.out.println("3. Delete cart");
            System.out.println("4. View cart");
            System.out.println("5. Search cart");
            System.out.println("6. Calculate Total Price");
            System.out.println("7. Clear Cart");
            System.out.println("8. Exit\n");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter item price: P");
                    double price = scanner.nextDouble();
                    cart.addItem(name, price);
                    break;
                case 2:
                    System.out.print("Enter item name to update: ");
                    String oldName = scanner.nextLine();
                    System.out.print("Enter new item name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new item price: P");
                    double newPrice = scanner.nextDouble();
                    cart.updateItem(oldName, newName, newPrice);
                    break;
                case 3:
                    System.out.print("Enter item name to delete: ");
                    name = scanner.nextLine();
                    cart.removeItem(name);
                    break;
                case 4:
                    cart.viewCart();
                    break;
                case 5:
                    System.out.print("Enter a keyword to search: ");
                    String keyword = scanner.nextLine();
                    cart.searchCart(keyword);
                    break;
                case 6:
                    System.out.println("Total Price: P" + cart.calculateTotal());
                    break;
                case 7:
                    cart.clearCart();
                    break;
                case 8:
                    System.out.println("Thank you for shopping!");
                    scanner.close();  // Close the scanner before exiting
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
