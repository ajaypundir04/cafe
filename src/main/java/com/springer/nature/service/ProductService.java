package com.springer.nature.service;

import com.springer.nature.models.Order;
import com.springer.nature.models.Product;

/**
 * @author Ajay Singh Pundir
 * Interface for product level operations
 */
public interface ProductService {

    double calculatePrice(Order order, Product product);
}
