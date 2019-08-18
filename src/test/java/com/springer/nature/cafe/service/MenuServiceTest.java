package com.springer.nature.cafe.service;

import com.springer.nature.cafe.utils.TestUtils;
import com.springer.nature.models.Product;
import com.springer.nature.models.Variations;
import com.springer.nature.service.impl.MenuService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class MenuServiceTest {
    Product[] products;
    private MenuService menuService = new MenuService();

    @Before
    public void setUp() throws IOException {
        products = TestUtils.readJsonFile("test-product.json", Product[].class);
        Arrays.stream(products).forEach(p -> {
            p.getVariations()
                    .forEach(v -> v.setQuantity(30 / p.getVariations().size()));
            menuService.addOrUpdateMenu(p);

        });
    }

    @Test
    public void getAvailableQuantitiesByProductTest() {
        Assert.assertEquals(30, menuService.getAvailableQuantitiesByProduct("Tea"));
    }

    @Test
    public void getAvailableQuantitiesByVariationsTest() {
        Assert.assertEquals(10, menuService.getAvailableQuantitiesByVariations("TL"));
    }

    @Test
    public void getProductByCodeTest() {
        Product product = products[0];
        Assert.assertThat(product,
                Matchers.samePropertyValuesAs(menuService.getProductByCode("TL"))
        );
    }

    @Test
    public void getVariationsByCodeTest() {
        Product product = products[0];
        Assert.assertThat(product.getVariationByCode("TL"),
                Matchers.samePropertyValuesAs(menuService.getVariationsByCode("TL"))
        );
    }

    @Test
    public void addOrUpdateMenuTest() {
        Product product = products[0];
        product.getVariations().forEach(
                variations -> variations.setQuantity(20)
        );
        menuService.addOrUpdateMenu(product);
        Assert.assertEquals(20,menuService.getVariationsByCode("TL").getQuantity());
    }

}
