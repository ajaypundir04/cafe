package com.springer.nature.cafe.service;

import com.springer.nature.cafe.models.Invoice;
import com.springer.nature.cafe.models.Order;

import java.util.List;

/**
 * @author Ajay Singh Pundir
 * Interface for order processing
 */
public interface CafeService {

    Invoice processOrder(List<Order> orders);
}
