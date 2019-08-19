package com.springer.nature.cafe.service;

import com.springer.nature.cafe.service.DiscountService;
import com.springer.nature.cafe.models.Invoice;
import com.springer.nature.cafe.models.Order;
import com.springer.nature.cafe.service.impl.DiscountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiscountServiceTest {

    private DiscountService discountService;
    private Invoice invoice;

    @Before
    public void setUp() {
        discountService = new DiscountServiceImpl();
        Order order = new Order();
        order.setQuantity(5);
        invoice = new Invoice();
        invoice.addEntry(order, 100, "Test");
    }

    @Test
    public void calculateDiscountTest() {
        Assert.assertEquals(11.5, discountService.calculateDiscount(115.0), 1);
    }

    @Test
    public void calculateDiscountByOrderTest() {
        Assert.assertEquals(7.5, discountService.calculateDiscount(100, invoice), 1);
    }
}
