package com.techelevator.vendingmachine;

import com.techelevator.product.Product;

import java.io.FileNotFoundException;
import java.util.Map;

public abstract class AbstractVendingMachine {
    protected Map<String, Product> inventory;
    protected double balance = 0;

    public AbstractVendingMachine(String inputFile) throws FileNotFoundException {
        this.inventory = readInventory(inputFile);
    }

    protected abstract Map<String, Product> readInventory(String inputFile) throws FileNotFoundException;

    public abstract void displayItems();

    public abstract void feedMoney(double amount);

    public abstract void selectProduct(String slot);

    public abstract void finishTransaction();

    public Map<String, Product> getInventory() {
        return inventory;
    }

    public double getBalance() {
        return balance;
    }

    public abstract Map<String, Integer> getProductQuantity();
}
