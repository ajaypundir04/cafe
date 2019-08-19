package com.springer.nature.service.impl;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;
import com.springer.nature.models.Variations;
import com.springer.nature.service.ProductService;

/**
 * @author Ajay Singh Pundir
 * This class used to perform product level operation like price calculation, etc.
 */
public class ProductServiceImpl implements ProductService {
    /**
     * @param order
     * @param product
     * @return @{@link Double} calculate the price of the ordered product
     */
    @Override
    public double calculatePrice(Order order, Product product) {
        Variations variations = product.getVariationByCode(order.getCode());
        if (variations.getQuantity() >= order.getQuantity()) {
            return order.getQuantity() * variations.getPrice();
        } else {
            throw new RuntimeException(CafeConstant.UNAVAILABLE_QUANTITY);
        }
    }
}
