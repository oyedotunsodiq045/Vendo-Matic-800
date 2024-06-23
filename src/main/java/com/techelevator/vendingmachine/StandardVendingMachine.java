package com.techelevator.vendingmachine;

import com.techelevator.product.Product;
import com.techelevator.product.ProductType;
import com.techelevator.product.Quantity;
import com.techelevator.transaction.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StandardVendingMachine extends AbstractVendingMachine {
    Quantity q = new Quantity();
    private final Map<String, Integer> productQuantity;

    public StandardVendingMachine(String inputFile) throws FileNotFoundException {
        super(inputFile);
        this.productQuantity = new LinkedHashMap<>();
        initializeProductQuantity();
    }

    private void initializeProductQuantity() {
        for (String slot : inventory.keySet()) {
            productQuantity.put(slot, q.getDefaultMaximumQuantity());
        }
    }

    @Override
    protected Map<String, Product> readInventory(String inputFile) throws FileNotFoundException {
        Map<String, Product> inventory = new LinkedHashMap<>();
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String slot = parts[0];
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                ProductType type = ProductType.valueOf(parts[3].toUpperCase());
                inventory.put(slot, new Product(slot, name, price, type));
            }
        }
        return inventory;
    }

    @Override
    public void displayItems() {
        for (String slot : inventory.keySet()) {
            Product product = inventory.get(slot);
            String soldOut = productQuantity.get(slot) == 0 ? "SOLD OUT" : productQuantity.get(slot) + " left";
            System.out.println(product.getSlot() + " | " + product.getName() + " | $" + product.getPrice() + " | " + soldOut);
        }
    }

    @Override
    public void feedMoney(double amount) {
        balance += amount;
        Transaction.logTransaction("FEED MONEY: $" + amount + " $" + balance);
    }

    @Override
    public void selectProduct(String slot) {
        Product product = inventory.get(slot);
        int quantity = productQuantity.getOrDefault(slot, 0);

        if (product == null) {
            System.out.println("Invalid product code.");
        } else if (quantity == 0) {
            System.out.println("This item is SOLD OUT.");
        } else if (balance < product.getPrice()) {
            System.out.println("Insufficient funds.");
        } else {
            productQuantity.put(slot, quantity - 1);
            balance -= product.getPrice();
            Transaction.logTransaction(product.getName() + " " + slot + " $" + product.getPrice() + " $" + balance);
            System.out.println("Dispensing: " + product.getName() + " - $" + product.getPrice());
            System.out.println(product.getType().getMessage());
        }
    }

    @Override
    public void finishTransaction() {
        double change = balance;
        int quarters = (int) (change / 0.25);
        change -= quarters * 0.25;
        int dimes = (int) (change / 0.10);
        change -= dimes * 0.10;
        int nickels = (int) (change / 0.05);
        balance = 0;
        Transaction.logTransaction("GIVE CHANGE: $" + (quarters * 0.25 + dimes * 0.10 + nickels * 0.05) + " $0.00");
        System.out.println("Your change is " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels.");
    }

    @Override
    public Map<String, Integer> getProductQuantity() {
        return productQuantity;
    }
}
