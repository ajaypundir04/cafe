package com.springer.nature.service;

import com.springer.nature.models.Order;
import com.springer.nature.models.Product;

public interface ProductService {
    double calculatePrice(Order order, Product product);
}
