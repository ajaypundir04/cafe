package com.springer.nature.service;

import com.springer.nature.constant.DiscountRange;
import com.springer.nature.models.Invoice;

public interface DiscountService {

    double calculateDiscount(double totalPrice);

    double calculateDiscount(double totalPrice, Invoice invoice);

    DiscountRange getDiscountRange();
}
