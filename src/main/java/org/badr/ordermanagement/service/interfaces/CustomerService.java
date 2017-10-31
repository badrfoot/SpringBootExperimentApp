/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service.interfaces;

import java.util.stream.Stream;
import org.badr.ordermanagement.entity.Customer;

/**
 *
 * @author OBD
 */
public interface CustomerService {

	String getGiftForCustomer(Customer customer);
	
	Stream<Customer> findAlienCustomers();
	
}
