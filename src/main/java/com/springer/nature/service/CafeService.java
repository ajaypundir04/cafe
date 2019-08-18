package com.springer.nature.service;

import com.springer.nature.models.Invoice;
import com.springer.nature.models.Order;

import java.util.List;

public interface CafeService {
    Invoice processOrder(List<Order> orders);
}
