package com.springer.nature.launcher;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.models.Invoice;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;
import com.springer.nature.service.DiscountService;
import com.springer.nature.service.InvoicePrinter;
import com.springer.nature.service.ProductService;
import com.springer.nature.service.impl.*;
import com.springer.nature.util.FileReader;
import com.springer.nature.util.InvoicePrinterFactory;

import java.io.IOException;
import java.util.List;

public class ApplicationLauncher {

    private static CafeServiceImpl cafeService;

    public static void main(String[] args) throws IOException {
        cafeService = initialize();
        List<Order> orders = FileReader.loadOrder("orders.json", CafeConstant.JSON_EXTENSION);
        Invoice invoice = cafeService.processOrder(orders);
        InvoicePrinterFactory.getInvoicePrinter(CafeConstant.PRINT_TEXT).prettyPrint(invoice);
    }

    private static CafeServiceImpl initialize() throws IOException {
        Product[] products = FileReader.loadProduct("products.json", CafeConstant.JSON_EXTENSION);
        MenuService menuService = new MenuService();
        for (Product p : products) {
            int varietyCount = p.getVariations().size();
            p.getVariations().forEach(variations -> {
                variations.setQuantity(CafeConstant.DEFAULT_QUANTITY / varietyCount);
            });
            menuService.addOrUpdateMenu(p, CafeConstant.DEFAULT_QUANTITY);
        }
        ProductService productService = new ProductServiceImpl();
        DiscountService discountService=new DiscountServiceImpl();
        CafeServiceImpl cafeService = CafeServiceImpl.getInstance();
        cafeService.setMenuService(menuService);
        cafeService.setDiscountService(discountService);
        cafeService.setProductService(productService);
        return cafeService;
    }
}
