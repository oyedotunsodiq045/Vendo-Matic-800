package com.techelevator.report;

import com.techelevator.product.Product;
import com.techelevator.product.Quantity;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class SalesReport {
    static Quantity q = new Quantity();

    public static void generateReport(Map<String, Product> inventory, Map<String, Integer> productQuantity) {
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss_a").format(new Date());
        String fileName = "SalesReport_" + timestamp + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            double totalSales = 0.0;
            for (String slot : inventory.keySet()) {
                Product product = inventory.get(slot);
                int quantitySold = q.getDefaultMaximumQuantity() - productQuantity.getOrDefault(slot, 0);
                writer.write(product.getName() + "|" + quantitySold + "\n");
                totalSales += quantitySold * product.getPrice();
            }
            writer.write("\n**TOTAL SALES** $" + totalSales + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
