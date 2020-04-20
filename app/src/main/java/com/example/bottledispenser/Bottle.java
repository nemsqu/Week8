package com.example.bottledispenser;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double price;
    private double size;


   /*public Bottle(){
    	name = "Pepsi Max";
    	manufacturer = "Pepsi";
    	total_energy = 0.3;
    	price = 1.8;
    	size = 0.5;
    }*/

    public Bottle(String givenName, String manuf, double totE, double givenPrice){
        name = givenName;
        manufacturer = manuf;
        total_energy = totE;
        price = givenPrice;
    }

    public String getName(){
        return name;
    }

    public String getManufacturer(){
        return manufacturer;
    }

    public double getEnergy(){
        return total_energy;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return total_energy;
    }

    public String toString(){
        String s = name + ", " + total_energy + "l, " + price + "€";
        return s;
    }
}
