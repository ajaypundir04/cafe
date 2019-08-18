package com.springer.nature.cafe.service;

import com.springer.nature.cafe.utils.TestUtils;
import com.springer.nature.models.Invoice;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;
import com.springer.nature.service.DiscountService;
import com.springer.nature.service.ProductService;
import com.springer.nature.service.impl.CafeServiceImpl;
import com.springer.nature.service.impl.DiscountServiceImpl;
import com.springer.nature.service.impl.MenuService;
import com.springer.nature.service.impl.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


public class CafeServiceTest {

    private CafeServiceImpl cafeService = CafeServiceImpl.getInstance();

    @Before
    public void setup() throws Exception {
        MenuService menuService = new MenuService();
        Product[] products = TestUtils.readJsonFile("test-product.json", Product[].class);
        Arrays.stream(products).forEach(p -> {
            menuService.addOrUpdateMenu(p);
            p.getVariations().forEach(v -> v.setQuantity(30 / p.getVariations().size()));
        });

        ProductService productService = new ProductServiceImpl();
        DiscountService discountService = new DiscountServiceImpl();
        cafeService.setMenuService(menuService);
        cafeService.setProductService(productService);
        cafeService.setDiscountService(discountService);

    }

    @Test
    public void processOrder() throws Exception {
        Order[] orders = TestUtils.readJsonFile("test-orders.json", Order[].class);
        Invoice invoice = cafeService.processOrder(Arrays.asList(orders));
        Assert.assertNotNull(invoice);
        Assert.assertNotNull(invoice.allOrders());
        Assert.assertEquals(115, invoice.getTotalAmount(), 0);
        Assert.assertEquals(11.5, invoice.getDiscount(), 0);
        Assert.assertEquals(103.5, invoice.getFinalAmount(), 0);

    }

}