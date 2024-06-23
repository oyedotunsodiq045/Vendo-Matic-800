package com.techelevator.product;

public class Product {
    private final String slot;
    private final String name;
    private final double price;
    private final ProductType type;

    public Product(String slot, String name, double price, ProductType type) {
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }
}
