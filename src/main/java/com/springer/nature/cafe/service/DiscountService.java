package com.springer.nature.cafe.service;

import com.springer.nature.cafe.constant.DiscountRange;
import com.springer.nature.cafe.models.Invoice;

/**
 * @author Ajay Singh Pundir
 * Interface for discount calculation
 */
public interface DiscountService {

    double calculateDiscount(double totalPrice);

    double calculateDiscount(double totalPrice, Invoice invoice);

    DiscountRange getDiscountRange();
}
