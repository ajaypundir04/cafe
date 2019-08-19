package com.springer.nature.cafe.service;

import com.springer.nature.cafe.models.Order;
import com.springer.nature.cafe.models.Product;

/**
 * @author Ajay Singh Pundir
 * Interface for product level operations
 */
public interface ProductService {

    double calculatePrice(Order order, Product product);
}
