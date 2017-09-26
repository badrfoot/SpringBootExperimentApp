/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller;

import org.badr.ordermanagement.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author OBD
 */
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	
	@Autowired private CustomerRepository customerRepository;
	
	@GetMapping
	public ResponseEntity<?> getAllCustomer(){
		return ResponseEntity.ok(customerRepository.findAll());
	}
	
}
