package com.techelevator.transaction;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TransactionTest {

    @Test
    public void testLogTransaction() {
        Transaction.logTransaction("TEST TRANSACTION");
        try (BufferedReader reader = new BufferedReader(new FileReader("Log.txt"))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("TEST TRANSACTION")) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
