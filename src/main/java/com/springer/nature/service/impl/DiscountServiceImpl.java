package com.springer.nature.service.impl;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.constant.DiscountRange;
import com.springer.nature.models.Order;
import com.springer.nature.service.DiscountService;

public class DiscountServiceImpl implements DiscountService {

    private DiscountRange discountRange;

    @Override
    public double calculateDiscount(double totalPrice) {
        double remaining = totalPrice;
        double discount = 0.0d;
        DiscountRange[] ranges = DiscountRange.values();
        for (int i = 1; i < CafeConstant.UPPER_LIMIT.length && remaining > 0; i++) {
            double df = Math.min(CafeConstant.UPPER_LIMIT[i] - CafeConstant.UPPER_LIMIT[i - 1], remaining);
            discount += df * ((ranges[i].getDiscountRange()) / 100);
            this.discountRange = ranges[i];
            remaining -= df;
        }
        return discount;
    }

    public double calculateDiscount(double totalPrice, Order order) {
        double discount = 0.0d;
        if (order.getQuantity() >= CafeConstant.DISCOUNT_LIMIT) {
            discount = 0.25 * totalPrice;
            this.discountRange = DiscountRange.TWENTY_FIVE;
        }
        return discount;
    }

    @Override
    public DiscountRange getDiscountRange() {
        return discountRange;
    }

}
