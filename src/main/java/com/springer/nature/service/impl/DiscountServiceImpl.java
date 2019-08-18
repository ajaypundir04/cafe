package com.springer.nature.service.impl;

import com.springer.nature.constant.CafeConstant;
import com.springer.nature.constant.DiscountRange;
import com.springer.nature.models.Order;
import com.springer.nature.service.DiscountService;

public class DiscountServiceImpl implements DiscountService {

    private DiscountRange discountRange;

    @Override
    public double calculateDiscount(double totalPrice) {
        double discount = 0.0d;
        if (totalPrice > 100 && totalPrice <= 200) {
            discount = 0.10 * totalPrice;
            this.discountRange = DiscountRange.TEN;

        } else if (totalPrice > 200) {
            discount = 0.10 * 200;
            discount += 0.20 * (totalPrice - 200);
            this.discountRange = DiscountRange.TWENTY;
        }
        return discount;
    }

    @Override
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
