package com.techelevator.vendingmachine;

import com.techelevator.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Map;

import static org.junit.Assert.*;

public class StandardVendingMachineTest {

    private StandardVendingMachine vendingMachine;

    @Before
    public void setUp() throws FileNotFoundException {
        vendingMachine = new StandardVendingMachine("src/test/resources/vendingmachine_test.csv");
    }

    @Test
    public void testReadInventory() {
        Map<String, Product> inventory = vendingMachine.getInventory();
        assertFalse(inventory.isEmpty());
        assertEquals("Cola", inventory.get("C1").getName());
    }

    @Test
    public void testFeedMoney() {
        vendingMachine.feedMoney(5.00);
        assertEquals(5.00, vendingMachine.getBalance(), 0.0);
    }

    @Test
    public void testSelectProduct() {
        // Test case where product quantity goes to zero
        vendingMachine.feedMoney(5.00);
        vendingMachine.selectProduct("A1"); // Assuming A1 has quantity 5 initially
        assertEquals(1.95, vendingMachine.getBalance(), 0.001);
        assertEquals(4, vendingMachine.getProductQuantity().get("A1").intValue());

//        // Exhaust the quantity of A1
//        for (int i = 0; i < StandardVendingMachine.DEFAULT_MAXIMUM_QUANTITY; i++) {
//            vendingMachine.selectProduct("A1");
//        }
//
//        // After selecting A1 DEFAULT_MAXIMUM_QUANTITY times, the balance should remain the same
//        assertEquals(1.95, vendingMachine.getBalance(), 0.001);
//        // Quantity of A1 should now be zero
//        assertEquals(0, vendingMachine.getProductQuantity().get("A1").intValue());
//
//        // Attempting to buy A1 again should fail
//        vendingMachine.selectProduct("A1");
//        assertEquals(1.95, vendingMachine.getBalance(), 0.001); // Balance remains the same
//        assertEquals(0, vendingMachine.getProductQuantity().get("A1").intValue()); // Quantity remains zero
    }

    @Test
    public void testInitialQuantity() {
        assertEquals(5, StandardVendingMachine.DEFAULT_MAXIMUM_QUANTITY);
    }



    @Test
    public void testFinishTransaction() {
        vendingMachine.feedMoney(1.00);
        vendingMachine.finishTransaction();
        assertEquals(0.0, vendingMachine.getBalance(), 0.0);
    }
}
