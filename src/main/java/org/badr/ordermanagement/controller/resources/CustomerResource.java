/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller.resources;

import java.util.UUID;
import lombok.Getter;
import org.badr.ordermanagement.controller.CustomerController;
import org.badr.ordermanagement.entity.BonusCard;
import org.badr.ordermanagement.entity.Customer;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author OBD
 */
@Getter
public class CustomerResource extends ResourceSupport{
	
	private final Customer customer;

	public CustomerResource(Customer customer) {
		this.customer = customer;
		
//		add(linkTo(CustomerController.class).withRel("customers"));
//		add(linkTo(CustomerController.class).slash(customer).withRel("put"));
		
		add(linkTo(methodOn(CustomerController.class).deleteEntity(customer.getId())).withRel("delete"));
		add(linkTo(methodOn(CustomerController.class).putEntity(customer)).withRel("put"));
		add(linkTo(methodOn(CustomerController.class).getEntityById(customer.getId())).withSelfRel());
		add(linkTo(methodOn(CustomerController.class).getBonusCard(customer.getId())).withRel("bonusCards"));
		add(linkTo(methodOn(CustomerController.class).getCreditCards(customer.getId())).withRel("creditCards"));
		
	}
	
	
}
