package com.example.bottledispenser;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Locale;

public class BottleDispenser {


    private static BottleDispenser dispenser = new BottleDispenser();

    // The array for the Bottle-objects

    ArrayList<Bottle> listBottles = new ArrayList<>();

    //private ArrayList<Bottle> listBottles;
    private float money;

    static BottleDispenser getInstance() {

        return dispenser;
    }

    private BottleDispenser() {

        money = 0;

        listBottles.add(new Bottle("Pepsi Max", "Pepsi", 0.5, 1.8));
        listBottles.add(new Bottle("Pepsi Max", "Pepsi", 1.5, 2.2));
        listBottles.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 2.0));
        listBottles.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5, 2.5));
        listBottles.add(new Bottle("Fanta Zero", "Coca-Cola", 0.5, 1.95));
    }

    String addMoney(float addition) {

        addition = addition / 100;
        money += addition;
        return String.format(Locale.getDefault(), "Klink! Added %.2f!", addition);
    }

    float getMoney(){
        return money;
    }

    String buyBottle(Bottle choiceBottle) {

        if(listBottles.size() == 0) {
            return "No bottles left!";
        }
        if(money < choiceBottle.getPrice()){
            return "Add money first!";
        }else {
            money -= choiceBottle.getPrice();
            String s = "KACHUNK! " + choiceBottle.getName() + " came out of the dispenser!";
            listBottles.remove(choiceBottle);
            return s;
        }
    }


    @SuppressLint("DefaultLocale")
    String returnMoney() {
        String s = String.format("Klink klink. Money came out! You got %,.2f€ back!", money);
        money = 0;
        return s;
    }

}
