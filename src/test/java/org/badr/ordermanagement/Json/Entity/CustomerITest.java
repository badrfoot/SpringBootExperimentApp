/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.Json.Entity;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import org.badr.ordermanagement.JsonITest;
import org.badr.ordermanagement.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

/**
 *
 * @author OBD
 */

public class CustomerITest extends JsonITest{
	
	@Autowired private JacksonTester<Customer> customerJacksonTester;
	
	private Customer customer;
	
	
	@Before
	public void before(){
		customer = new Customer("Bill", "Gate");		
	}	
	
	@Test
	public void simpleTest() throws IOException{
		customer.setBirthDate(LocalDate.of(2000, Month.MARCH, 15));
		System.out.println(customerJacksonTester.write(customer).getJson());
	}
	
	
}
