package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";

	private File inventoryFile = new File("main.csv");
	private Map<String, Integer> inventory = new HashMap<>();
	private double currentMoney = 0.00;
	private Funds funds = new Funds();

	private Logger logger = new Logger("log.txt");


	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	public void run() {


		logger.write("Machine has been turned on.");



		Scanner scanner = new Scanner(System.in);
		boolean stay = true;

		try {
		Scanner fileScanner = new Scanner(inventoryFile);


		while(fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String[] lineArr = line.split("\\,");

			inventory.put(lineArr[1],5);
		}
		} catch (FileNotFoundException e) {
				System.out.println("Problem with file");
				logger.write("Problem with file");
			}



		while (stay) {
			System.out.println("(1) " + MAIN_MENU_OPTION_DISPLAY_ITEMS);
			System.out.println("(2) " + MAIN_MENU_OPTION_PURCHASE);
			System.out.println("(3) Exit");

			String choice = scanner.nextLine();

			if(choice.equals("1")){
				display();
			} else if (choice.equals("2")) {
				purchase();

			} else if (choice.equals("3")) {
				stay = false;
			}
		}
	}

	public void display(){
		try {
			Scanner fileScanner = new Scanner(inventoryFile);
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] lineArr = line.split("\\,");

				if (inventory.get(lineArr[1]) == 0) {
					System.out.println(lineArr[0] + ", " + lineArr[1] + ", $" + lineArr[2] + " Inventory Remaining: (Sold Out)");
				} else {
					System.out.println(lineArr[0] + ", " + lineArr[1] + ", $" + lineArr[2] + " Inventory Remaining: (" + inventory.get(lineArr[1]) + ")");
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Problem with file");
		}
	}

	public void purchase(){
		boolean stay = true;

		while (stay) {

			System.out.println("Current Money Provided: " + "$" + this.currentMoney);
			System.out.println("(1) Feed Money");
			System.out.println("(2) Select Product");
			System.out.println("(3) Finish Transaction");

			Scanner scanner = new Scanner(System.in);
			String choice = scanner.nextLine();

			boolean stay1 = true;
			if (choice.equals("1")) {
				while (stay1) {
					System.out.println("Enter a dollar? (Y/N)");

					String dollar = scanner.nextLine();
					if (dollar.toLowerCase().equals("y")) {
						this.currentMoney++;
						logger.write("FEED MONEY:$1.00 $" + currentMoney);
						System.out.println("Current Balance: " + "$" + this.currentMoney);
					}
					else if (dollar.toLowerCase().equals("n")) {
						stay1 = false;
						logger.write("FEED MONEY:$0.00 $" + this.currentMoney);
					}else{
						System.out.println("Invalid input!");
						logger.write("Invalid input");
					}
				}
			}

			if (choice.equals("2")) {
				display();
				System.out.println("Please select a product: ex. A1, B2, C3");
				String customerChoice = scanner.nextLine();

				try {
					Scanner fileScanner = new Scanner(inventoryFile);
					while(fileScanner.hasNextLine()) {
						String line = fileScanner.nextLine();
						String[] lineArr = line.split("\\,");

						if (lineArr[0].equals(customerChoice) && lineArr[3].equals("Munchy")) {
							Munchy munchy = new Munchy();
							double beforePurchaseMoney = currentMoney;
							this.currentMoney = munchy.selectProduct(customerChoice, this.currentMoney, inventory, inventoryFile);
							if (currentMoney != beforePurchaseMoney) {
								munchy.getPhrase();
							}
							logger.write(customerChoice + " " + lineArr[1] + " " + lineArr[0] + " " + lineArr[2] + " " + this.currentMoney);
						}
						if (lineArr[0].equals(customerChoice) && lineArr[3].equals("Candy")) {
							Candy candy = new Candy();
							double beforePurchaseMoney = currentMoney;
							this.currentMoney = candy.selectProduct(customerChoice, this.currentMoney, inventory, inventoryFile);
							if (currentMoney != beforePurchaseMoney) {
								candy.getPhrase();
							}
							logger.write(customerChoice + " " + lineArr[1] + " " + lineArr[0] + " " + lineArr[2] + " " + this.currentMoney  );
						}
						if (lineArr[0].equals(customerChoice) && lineArr[3].equals("Drink")) {
							Drink drink = new Drink();
							double beforePurchaseMoney = currentMoney;
							this.currentMoney = drink.selectProduct(customerChoice, this.currentMoney, inventory, inventoryFile);
							if (currentMoney != beforePurchaseMoney) {
								drink.getPhrase();
							}
							logger.write(customerChoice + " " + lineArr[1] + " " + lineArr[0] + " " + lineArr[2] + " " + this.currentMoney  );
						}
						if (lineArr[0].equals(customerChoice) && lineArr[3].equals("Gum")) {
							Gum gum = new Gum();
							double beforePurchaseMoney = currentMoney;
							this.currentMoney = gum.selectProduct(customerChoice, this.currentMoney, inventory, inventoryFile);
							if (currentMoney != beforePurchaseMoney) {
								gum.getPhrase();
							}
							logger.write(customerChoice + " " + lineArr[1] + " " + lineArr[0] + " " + lineArr[2] + " " + this.currentMoney  );
						}
					}
				} catch (FileNotFoundException e) {
					System.out.println("Problem with file");
					logger.write("Problem with file");
				}
			}

			if (choice.equals("3")) {
				double change = this.currentMoney;
				funds.dispenseChange(currentMoney);
				this.currentMoney = 0;
				logger.write("GIVE CHANGE: " + change + " $" + currentMoney);
				stay = false;
			}
		}

	}


}
