package com.springer.nature.cafe.service;

import com.springer.nature.cafe.utils.TestUtils;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;
import com.springer.nature.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ProductServiceTest {

    Order order;
    Product product;
    @Before
    public void setUp() throws IOException
    {
        order=  TestUtils.readJsonFile("test-orders.json", Order[].class)[0];
        product= TestUtils.readJsonFile("test-product.json", Product[].class)[0];
        product.getVariations().forEach(variations -> variations.setQuantity(10));
    }
    @Test
    public void calculatePriceTest()
    {
        ProductServiceImpl productService=new ProductServiceImpl();
        Assert.assertEquals(45,productService.calculatePrice(order,product),0);
    }
}
