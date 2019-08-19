package com.springer.nature.service;

import com.springer.nature.models.Invoice;
import com.springer.nature.models.Order;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * Interface for order processing
 */
public interface CafeService {

    Invoice processOrder(List<Order> orders);
}
