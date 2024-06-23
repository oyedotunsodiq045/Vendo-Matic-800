package com.techelevator.product;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    private Product product;

    @Before
    public void setUp() {
        product = new Product("A1", "Chips", 1.50, ProductType.CHIP);
    }

    @Test
    public void testGetPrice() {
        assertEquals(1.50, product.getPrice(), 0.0);
    }

    @Test
    public void testGetName() {
        assertEquals("Chips", product.getName());
    }

    @Test
    public void testGetSlot() {
        assertEquals("A1", product.getSlot());
    }

    @Test
    public void testGetType() {
        assertEquals(ProductType.CHIP, product.getType());
    }
}
