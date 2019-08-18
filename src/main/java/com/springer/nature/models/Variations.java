package com.springer.nature.models;

/**
 * @author Ajay Pundir
 * This class will indicate all the avrieties of the products.
 */
public class Variations {

    private String name;
    private double price;
    private int quantity;

    public Variations() {

    }

    public Variations(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
