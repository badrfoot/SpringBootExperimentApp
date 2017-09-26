/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service;

import org.badr.ordermanagement.AbstractITest;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author OBD
 */
public class OrderServiceTest extends AbstractITest{
	
	@Autowired
	private OrderService orderService;

	
	@Test
	public void testJustTest() {
		assertEquals("Should be 0", (Long)0L, orderService.justTest()); 
	}
	
}
