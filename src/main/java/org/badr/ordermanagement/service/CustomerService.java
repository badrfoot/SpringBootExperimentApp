/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service;

import org.badr.ordermanagement.entity.BonusCard;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author OBD
 */
@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;	
	
	public void setBonusCardToCustomer(Customer customer, BonusCard bonusCard){
		if(customerRepository.countOrderForCustomer(customer) > 0L){
			customer.setBonusCard(bonusCard);
		}
	}
	
	public Boolean sendFidelityEmailToOldestCustomer(){
		boolean emailSent = false;
		Customer customer = customerRepository.findTopByOrderByBirthDateAsc().orElse(null);
		if (customer != null){
			sendEmail(customer);
			emailSent = true;
		}		
		return emailSent;	
	}
	
	public void sendEmail (Customer customer) throws RuntimeException {
		// Sending email .....		
	}
	
}
