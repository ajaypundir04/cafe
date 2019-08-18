package com.springer.nature.service;

import com.springer.nature.constant.DiscountRange;
import com.springer.nature.models.Order;

public interface DiscountService {

    double calculateDiscount(double totalPrice);

    double calculateDiscount(double totalPrice, Order order);

    DiscountRange getDiscountRange();
}
