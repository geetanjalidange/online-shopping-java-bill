import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define Item class to represent items available for purchase
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
}

// Define Order class to represent an order with items and quantities
class Order {
    private List<Item> items;
    private List<Integer> quantities;

    public Order() {
        items = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    public void addItem(Item item, int quantity) {
        items.add(item);
        quantities.add(quantity);
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }

    public double getTotal() {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getPrice() * quantities.get(i);
        }
        return total;
    }
}

public class OnlineShopping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define available items
        Item item1 = new Item("Laptop", 1000);
        Item item2 = new Item("Headphones", 50);
        Item item3 = new Item("Smartphone", 500);

        // Create an order
        Order order = new Order();

        // Display available items and take order from the customer
        System.out.println("Welcome to Online Shopping!");
        System.out.println("Available Items:");
        System.out.println("1. Laptop - $10000");
        System.out.println("2. Headphones - $500");
        System.out.println("3. Smartphone - $5000");
        System.out.println("Enter the item number and quantity (e.g., 1 2 for 2 laptops), type 'done' to finish:");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter item number and quantity.");
                continue;
            }
            int itemNumber;
            int quantity;
            try {
                itemNumber = Integer.parseInt(parts[0]);
                quantity = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid numbers.");
                continue;
            }
            if (itemNumber < 1 || itemNumber > 3 || quantity <= 0) {
                System.out.println("Invalid item number or quantity.");
                continue;
            }
            Item item = null;
            switch (itemNumber) {
                case 1:
                    item = item1;
                    break;
                case 2:
                    item = item2;
                    break;
                case 3:
                    item = item3;
                    break;
            }
            order.addItem(item, quantity);
        }

        // Generate bill
        System.out.println("\n----- Bill -----");
        System.out.println("Item\t\tQuantity\tPrice");
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.printf("%s\t\t%d\t\t$%.2f\n", order.getItems().get(i).getName(),
                    order.getQuantities().get(i), order.getItems().get(i).getPrice() * order.getQuantities().get(i));
        }
        System.out.printf("\nTotal: $%.2f\n", order.getTotal());

        scanner.close();
    }
}
