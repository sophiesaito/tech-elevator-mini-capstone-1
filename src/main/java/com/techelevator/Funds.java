package com.techelevator;

import java.math.BigDecimal;

public class Funds {

    public String dispenseChange(double change) {
        double moneyRemaining = change;
        int quarters = (int) ((moneyRemaining * 100) / 25);
        moneyRemaining *= 100;
        moneyRemaining -= quarters * 25;
        int dimes = (int) (moneyRemaining / 10);
        moneyRemaining -= dimes * 10;
        int nickles = (int) (moneyRemaining / 5);
        moneyRemaining -= nickles * 5;
        // Andy wants to see tests for this method
        System.out.println("Dispensing " + quarters + " quarter(s) " + dimes + " dime(s) and " + nickles + " nickle(s).");
        return "Dispensing " + quarters + " quarter(s) " + dimes + " dime(s) and " + nickles + " nickle(s).";
    }
}
