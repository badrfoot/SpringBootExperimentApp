/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.badr.ordermanagement.entity.Order;

/**
 *
 * @author OBD
 */
public interface OrderService {

	Stream<Order> getAllOrders();
	
	Optional<Order> getOrderThatHasMaxTotalPrice();
	
	Optional<Order> getOrderThatHasMinTotalPrice();
	
	Long getCountOfCanceledOrder();
	
	List<Order> getAllOrdersBetweenDates(Date startDate, Date endDate);
}
