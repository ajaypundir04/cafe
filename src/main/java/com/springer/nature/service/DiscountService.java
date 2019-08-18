package com.springer.nature.service;

import com.springer.nature.constant.DiscountRange;

public interface DiscountService {

    double calculateDiscount(double totalPrice);

    DiscountRange getDiscountRange();
}
