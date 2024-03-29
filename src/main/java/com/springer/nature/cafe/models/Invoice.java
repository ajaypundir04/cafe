package com.springer.nature.cafe.models;

import com.springer.nature.cafe.constant.DiscountRange;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ajay Singh Pundir
 * This class is the itemized bill generated
 */
public class Invoice {

    private Map<Order, Double> items;
    private double finalAmount;
    private double discount;
    private double totalAmount = 0.0;
    private DiscountRange discountRange;

    public Invoice() {
        items = new HashMap<>();
    }

    public void addEntry(Order order, double price, String name) {
        order.setName(name);
        if (!items.containsKey(order)) {
            items.put(order, price);
        } else {
            items.put(order, price);
        }
    }

    public Collection<Order> allOrders() {
        return items.keySet();
    }


    public double getFinalAmount() {
        return this.finalAmount = totalAmount - discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = this.totalAmount + totalAmount;
    }

    public DiscountRange getDiscountRange() {
        return discountRange;
    }

    public void setDiscountRange(DiscountRange discountRange) {
        this.discountRange = discountRange;
    }

    public Double getPrice(Order order) {
        return items.getOrDefault(order, 0.0);
    }
}
