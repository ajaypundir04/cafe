package com.springer.nature.service.impl;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.exception.CafeServiceException;
import com.springer.nature.models.Invoice;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;
import com.springer.nature.service.CafeService;
import com.springer.nature.service.DiscountService;
import com.springer.nature.service.ProductService;
import com.springer.nature.util.InvoicePrinterFactory;

import java.util.List;

public class CafeServiceImpl implements CafeService {

    private static CafeServiceImpl cafeService = new CafeServiceImpl();
    private MenuService menuService;
    private ProductService productService;
    private DiscountService discountService;

    private CafeServiceImpl() {
    }

    public static CafeServiceImpl getInstance() {
        return cafeService;
    }

    @Override
    public Invoice processOrder(List<Order> orders) {
        Invoice invoice = new Invoice();
        for (Order order : orders) {
            int availableQuantity = menuService.getAvailableQuantitiesByVariations(order.getCode());
            if (order.getQuantity() <= availableQuantity) {
                Product product = menuService.getProductByCode(order.getCode());
                double price=productService.calculatePrice(order, product);
                invoice.addEntry(
                        order,
                        price,
                        orderName(product, order.getCode())
                );
               // invoice.setDiscount(discountService.calculateDiscount(invoice.getTotalAmount()));
                invoice.setTotalAmount(price);
                //invoice.setDiscountRange(productService.getDiscountRange());

            } else {
                throw new CafeServiceException(CafeConstant.UNAVAILABLE_QUANTITY);
            }
        }
        invoice.setDiscount(discountService.calculateDiscount(invoice.getTotalAmount()));
        invoice.setDiscountRange(discountService.getDiscountRange());
        InvoicePrinterFactory.getInvoicePrinter(CafeConstant.PRINT_TEXT).prettyPrint(invoice);
        return invoice;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public MenuService getMenuService() {
        return menuService;
    }

    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private String orderName(Product product, String variationCode) {
        return String.join("",
                product.getName(), "-", product.getVariationByCode(variationCode).getName());
    }
}
