/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service;

import org.badr.ordermanagement.respository.CustomerRepository;
import org.badr.ordermanagement.respository.OrderRepository;
import org.badr.ordermanagement.respository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author OBD
 */
@Service
public class OrderService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository ;
	
	@Autowired
	private CustomerRepository customerRepository ;
	
	
	public Long justTest(){
		return customerRepository.count();
	}
	
}
