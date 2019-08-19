package com.springer.nature.models;

import java.util.Objects;

/**
 * @author Ajay Singh Pundir
 * It handles the order placed
 */
public class Order {

    private int quantity;
    private String code;
    private String name;

    public int getQuantity() {
        return quantity;
    }

    public Order setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Order setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return Objects.equals(code, order.code);
    }

}
