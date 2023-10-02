package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FundsTest {

    private Funds funds;

    @Before
    public void setup() {
        this.funds = new Funds();
    }

    @Test
    public void dispenseChange_test() {

        String actualResult = funds.dispenseChange(1.40);
        String expectedResult = "Dispensing 5 quarter(s) 1 dime(s) and 1 nickle(s).";

        assertEquals(expectedResult, actualResult);
    }



}
