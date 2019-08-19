package com.springer.nature.cafe.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Ajay Singh Pundir
 * This class is used to indicate the product from menu.
 */
public class Product {

    Set<Variations> variations;
    private String name;
    private Map<String, Variations> variationsMap;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Variations> getVariationsMap() {
        return variationsMap;
    }


    public Variations getVariationByCode(String variationCode) {
        return variationsMap.get(variationCode);
    }

    public void setVariationsMap() {
        for (Variations v : variations) {
            StringBuilder code = new StringBuilder();
            code.append(this.name.charAt(0)).append(v.getName().charAt(0));
            variationsMap.put(code.toString(), v);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(name, product.name);
    }

    public Set<Variations> getVariations() {
        return variations;
    }

    public void setVariations(Set<Variations> variations) {
        this.variations = variations;
        this.variationsMap = new HashMap<>();
        this.setVariationsMap();
    }
}
