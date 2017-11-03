/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement;

import java.util.Date;
import org.badr.ordermanagement.entity.BonusCard;
import org.badr.ordermanagement.entity.CreditCard;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.entity.enums.TypeCreditCard;
import org.badr.ordermanagement.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author OBD
 */
@Component
public class InitData implements ApplicationRunner {

	@Autowired private CustomerRepository customerRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void run(ApplicationArguments applicationArguments) throws Exception {
		
		Customer customer = new Customer("Bill", "Gates");
		
		BonusCard bonusCard = new BonusCard("EED54SC-88DF");
		bonusCard.setEnabled(true);		
		customer.setBonusCard(bonusCard);
		
		Date expirationDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse("2099-12-01");		
		CreditCard creditCard = new CreditCard(expirationDate, TypeCreditCard.Visa);		
		customer.addCreditCard(creditCard);
		
		System.out.println("***** Customer UUID is ==> " + customerRepository.save(customer).getId() + "***********");		
	}
	
	
}
