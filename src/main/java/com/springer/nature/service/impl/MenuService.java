package com.springer.nature.service.impl;

import com.springer.nature.exception.MenuServiceException;
import com.springer.nature.models.Menu;
import com.springer.nature.models.Product;
import com.springer.nature.models.Variations;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * It deals with the Menu for the cafe
 */
public class MenuService {
    private Menu menu;

    public MenuService() {
        menu = new Menu();
    }


    /**
     * @param code On the basis of code it will return available quantity
     * @return available quantity in @int
     */
    public int getAvailableQuantitiesByProduct(String code) {
        Product product = new Product();
        product.setName(code);
        return menu.getMenuMap().getOrDefault(product, 0);

    }

    /**
     * @param code On the basis of code it will return available quantity
     * @return available quantity in @int
     */
    public int getAvailableQuantitiesByVariations(String code) {

        return menu.getMenuMap()
                .keySet()
                .stream()
                .filter(product -> product.getVariationsMap().containsKey(code))
                .findFirst()
                .get()
                .getVariationByCode(code)
                .getQuantity();

    }

    /**
     * @param code On the basis of product variations it will return it's details
     * @return @{@link Variations} object is returned from the menu
     */
    public Variations getVariationsByCode(String code) {
        return menu.getMenuMap()
                .keySet()
                .stream()
                .filter(product -> product.getVariationsMap().containsKey(code))
                .findFirst()
                .get()
                .getVariationByCode(code);
    }

    /**
     * @param code On the basis of product variations it will return it's details
     * @return @{@link Product} object is returned from the menu
     */
    public Product getProductByCode(String code) {
        return menu.getMenuMap()
                .keySet()
                .stream()
                .filter(product -> product.getVariationsMap().containsKey(code))
                .findFirst()
                .get();
    }

    /**
     * @param product @{@link Product} is passed for modifying the menu.
     */
    public void addOrUpdateMenu(Product product) {
        int quantToModify = product.getVariations()
                .stream()
                .map(Variations::getQuantity)
                .mapToInt(i -> i)
                .sum();
        if (menu.getMenuMap().containsKey(product)) {
            menu.getMenuMap().put(product, menu.getMenuMap().get(product) + quantToModify);
        } else {
            menu.getMenuMap().put(product, quantToModify);
        }
    }


    /**
     * @param variations @{@link Variations} is passed for modifying the menu.
     */
    public void addOrUpdateMenuByVariation(Variations variations) {

        Product product = menu.getMenuMap()
                .keySet()
                .stream()
                .filter(p -> p.getVariationsMap().containsKey(keyGeneration(p.getName(), variations.getName())))
                .findFirst()
                .get();
        if (Objects.nonNull(product)) {
            product.getVariationsMap().put(keyGeneration(product.getName(), variations.getName()), variations);
            menu.getMenuMap().put(product,
                    product.getVariations()
                            .stream()
                            .map(Variations::getQuantity)
                            .mapToInt(i -> i)
                            .sum());
        } else {
            throw new MenuServiceException("Product Not Available.");
        }

    }

    /**
     * @param product is passed to remove it from menu
     * @return no of items removed
     */
    public int removeProductFromMenu(Product product) {
        return menu.getMenuMap().remove(product);
    }

    /**
     * @param product    Product to be modified
     * @param variations It is used to add new variation for a particular variations
     */
    public void registerNewVariation(Product product, Variations variations) {
        product.getVariations().add(variations);
        product.getVariationsMap().put(keyGeneration(product.getName(), variations.getName()), variations);
        menu.getMenuMap().put(product,
                product.getVariations()
                        .stream()
                        .map(Variations::getQuantity)
                        .mapToInt(i -> i)
                        .sum());
    }

    private String keyGeneration(String productName, String variety) {
        StringBuilder code = new StringBuilder();
        return code.append(productName.charAt(0)).append(variety.charAt(0)).toString();
    }
}
