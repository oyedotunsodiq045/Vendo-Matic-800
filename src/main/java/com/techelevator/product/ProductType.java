package com.techelevator.product;

public enum ProductType {
    CHIP("Crunch Crunch, Yum!"),
    CANDY("Munch Munch, Yum!"),
    DRINK("Glug Glug, Yum!"),
    GUM("Chew Chew, Yum!");

    private final String message;

    ProductType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
