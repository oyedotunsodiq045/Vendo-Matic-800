package com.techelevator;

import com.techelevator.report.SalesReport;
import com.techelevator.vendingmachine.StandardVendingMachine;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		StandardVendingMachine vendingMachine = null;
		try {
			vendingMachine = new StandardVendingMachine("vendingmachine.csv");
		} catch (FileNotFoundException e) {
			System.out.println("Inventory file not found.");
			System.exit(1);
		}

		Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine();

			switch (choice) {
				case "1":
					vendingMachine.displayItems();
					System.out.println(" ");
					break;
				case "2":
					purchasingProcessMenu(vendingMachine, scanner);
					break;
				case "3":
					System.out.println("Exiting...");
					break label;
				case "4":
					SalesReport.generateReport(vendingMachine.getInventory(), vendingMachine.getProductQuantity());
					System.out.println("Sales report generated.");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
        }
    }

	private static void displayMainMenu() {
		System.out.println("(1) Display Vending Machine Items");
		System.out.println("(2) Purchase");
		System.out.println("(3) Exit");
		// System.out.println("(4) Generate Sales Report ...(Hidden)");
		System.out.println(" ");
		System.out.print("Please choose an option: ");
	}

	private static void purchasingProcessMenu(StandardVendingMachine vendingMachine, Scanner scanner) {
        label:
        while (true) {
            displayPurchasingProcessMenu(vendingMachine);
            String choice = scanner.nextLine();

			switch (choice) {
				case "1":
					System.out.print("Enter amount to feed (whole dollar amount): ");
					double amount = Double.parseDouble(scanner.nextLine());
					vendingMachine.feedMoney(amount);
					break;
				case "2":
					System.out.print("Enter product code: ");
					String code = scanner.nextLine();
					vendingMachine.selectProduct(code);
					break;
				case "3":
					vendingMachine.finishTransaction();
					break label;
				default:
					System.out.println("Invalid choice. Please try again.");
					break;
			}
        }
    }

	private static void displayPurchasingProcessMenu(StandardVendingMachine vendingMachine) {
		System.out.println("Current Money Provided: $" + vendingMachine.getBalance());
		System.out.println(" ");
		System.out.println("(1) Feed Money");
		System.out.println("(2) Select Product");
		System.out.println("(3) Finish Transaction");
		System.out.println(" ");
		System.out.print("Please choose an option: ");
	}
}
