package com.springer.nature.service.impl;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.models.Order;
import com.springer.nature.models.Product;
import com.springer.nature.models.Variations;
import com.springer.nature.service.ProductService;

public class ProductServiceImpl implements ProductService {

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
