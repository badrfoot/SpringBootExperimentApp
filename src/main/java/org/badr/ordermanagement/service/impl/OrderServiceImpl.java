/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service.impl;

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
	public Long justTest(){		
		return orderRepository.count();		
	}	
	
}
