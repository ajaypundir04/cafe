package com.springer.nature.cafe.models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ajay Singh Pundir
 * This class will have the entries of all the products available.
 */
public class Menu {
    private Map<Product, Integer> menuMap;

    public Menu() {
        menuMap = new HashMap<>();
    }

    public Map<Product, Integer> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<Product, Integer> menuMap) {
        this.menuMap = menuMap;
    }
}
