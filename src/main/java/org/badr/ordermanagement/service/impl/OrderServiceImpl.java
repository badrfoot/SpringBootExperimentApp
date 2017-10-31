/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.badr.ordermanagement.entity.Order;
import org.badr.ordermanagement.service.interfaces.OrderService;
import org.badr.ordermanagement.respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author OBD
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository ;

	@Override
	public Stream<Order> getAllOrders() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Optional<Order> getOrderThatHasMaxTotalPrice() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Optional<Order> getOrderThatHasMinTotalPrice() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Long getCountOfCanceledOrder() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public List<Order> getAllOrdersBetweenDates(Date startDate, Date endDate) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}
