package com.techelevator.transaction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    public static void logTransaction(String message) {
        String timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a").format(new Date());
        try (PrintWriter writer = new PrintWriter(new FileWriter("Log.txt", true))) {
            writer.println(timestamp + " " + message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
