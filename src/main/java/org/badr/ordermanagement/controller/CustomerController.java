/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller;

import java.util.UUID;
import javax.annotation.PostConstruct;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author OBD
 */
@RestController
@RequestMapping(path = "/customer")
public class CustomerController extends AbstractControllerWithUUID<Customer>{

	@Autowired
	public CustomerController(CrudRepository<Customer, UUID> crudRepository) {
		super(crudRepository);
	}
		
}
