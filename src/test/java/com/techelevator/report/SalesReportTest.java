package com.techelevator.report;

import com.techelevator.product.Product;
import com.techelevator.product.ProductType;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class SalesReportTest {

    private Map<String, Product> inventory;
    private Map<String, Integer> productQuantity;

    @Before
    public void setUp() {
        inventory = new HashMap<>();
        inventory.put("A1", new Product("A1", "Chips", 1.50, ProductType.CHIP));
        inventory.put("B1", new Product("B1", "Cola", 2.00, ProductType.DRINK));

        productQuantity = new HashMap<>();
        productQuantity.put("A1", 5); // Assuming 5 units of A1 were sold
        productQuantity.put("B1", 3); // Assuming 3 units of B1 were sold
    }

    @Test
    public void testGenerateReport() {
        SalesReport.generateReport(inventory, productQuantity);
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_a").format(new Date());
        String fileName = "SalesReport_" + timestamp + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("**TOTAL SALES**")) {
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
