package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public abstract class Item {

    private static boolean bogodo;



    public double selectProduct(String customerChoice, double currentMoney, Map inventory, File inventoryFile) {
        try {
            Scanner fileScanner = new Scanner(inventoryFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] lineArr = line.split("\\,");
                int checker = (int) inventory.get(lineArr[1]);
                if (customerChoice.equals(lineArr[0]) && checker !=0) {
                    System.out.println("Dispensing " + lineArr[1] + " $" + lineArr[2]);
                    if (currentMoney >= Double.parseDouble(lineArr[2])) {
                        currentMoney -= Double.parseDouble(lineArr[2]);
                        if(this.bogodo && Double.parseDouble(lineArr[2]) >= 1){
                            currentMoney += 1;
                            System.out.println("You received a BOGODO deal");
                             this.bogodo = !this.bogodo;
                        } else if(!this.bogodo){
                            this.bogodo = !this.bogodo;
                        } else{
                            System.out.println("Item not eligible for discount");
                            this.bogodo = !this.bogodo;
                        }
                        inventory.put(lineArr[1], (int)inventory.get(lineArr[1]) - 1);
                        break;
                    } else {
                        System.out.println("Not enough money for item");
                    }
                } else if (!customerChoice.equals(lineArr[0])){

                } else {
                    System.out.println("Item is sold out!");
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("File not Found!");
        }

        return currentMoney;
    }

    public void getPhrase() {

    }
}
