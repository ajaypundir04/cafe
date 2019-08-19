package com.springer.nature.cafe.service.impl;

import com.springer.nature.cafe.exception.CafeServiceException;
import com.springer.nature.cafe.constant.CafeConstant;
import com.springer.nature.cafe.models.Invoice;
import com.springer.nature.cafe.models.Order;
import com.springer.nature.cafe.models.Product;
import com.springer.nature.cafe.service.CafeService;
import com.springer.nature.cafe.service.DiscountService;
import com.springer.nature.cafe.service.ProductService;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * It proceess the order placed
 */
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

    /**
     * @param orders List of @{@link Order} orders passed for processing
     * @return @{@link Invoice}
     */
    @Override
    public Invoice processOrder(List<Order> orders) {
        Invoice invoice = new Invoice();
        for (Order order : orders) {
            int availableQuantity = menuService.getAvailableQuantitiesByVariations(order.getCode());
            if (order.getQuantity() <= availableQuantity) {
                Product product = menuService.getProductByCode(order.getCode());
                double price = productService.calculatePrice(order, product);
                invoice.addEntry(
                        order,
                        price,
                        orderName(product, order.getCode())
                );
                invoice.setTotalAmount(price);
            } else {
                throw new CafeServiceException(CafeConstant.UNAVAILABLE_QUANTITY);
            }
        }
        invoice.setDiscount(discountService.calculateDiscount(invoice.getTotalAmount()));
        invoice.setDiscountRange(discountService.getDiscountRange());
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
