package com.springer.nature.service;

import com.springer.nature.constant.DiscountRange;
import com.springer.nature.models.Invoice;

/**
 * @author Ajay Singh Pundir
 * Interface for discount calculation
 */
public interface DiscountService {

    double calculateDiscount(double totalPrice);

    double calculateDiscount(double totalPrice, Invoice invoice);

    DiscountRange getDiscountRange();
}
