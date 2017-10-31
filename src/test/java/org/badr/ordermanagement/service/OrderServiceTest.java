/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service;

import org.badr.ordermanagement.service.impl.OrderServiceImpl;
import org.badr.ordermanagement.AbstractITest;
import org.junit.Test;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author OBD
 */
public class OrderServiceTest extends AbstractITest{
	
	@Autowired
	private OrderServiceImpl orderService;

	@Test @Ignore
	public void justSimpleTest(){
	
	}
	
	
}
