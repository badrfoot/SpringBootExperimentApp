/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.badr.ordermanagement.service.interfaces.CustomerService;
import org.badr.ordermanagement.entity.BonusCard;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.entity.enums.PlanetEnum;
import org.badr.ordermanagement.respository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author OBD
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;


	@Override
	public String getGiftForCustomer(Customer customer) {
		String gift = "Rien pour le moment";

		if(customerRepository.countOrderForCustomer(customer) > 10L){
			gift = "50% pour le prochain achat";
		}

		return gift;
	}

	@Override
	public Stream<Customer> findAlienCustomers() {
		return customerRepository.findByAddressPlanetNotIn(Arrays.asList(PlanetEnum.Earth));
	}
	
}