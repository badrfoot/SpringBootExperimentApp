/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller;

import com.google.common.collect.Lists;
import java.util.UUID;
import java.util.stream.Collectors;
import org.badr.ordermanagement.controller.resources.CustomerResource;
import org.badr.ordermanagement.entity.BonusCard;
import org.badr.ordermanagement.entity.CreditCard;
import org.badr.ordermanagement.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 *
 * @author OBD
 */
@RestController
@RequestMapping(path = "/customer")
@ExposesResourceFor(Customer.class)
public class CustomerController extends AbstractControllerWithUUID<Customer>{

	private CrudRepository<Customer, UUID> crudRepository;
	
	private final EntityLinks entityLinks;
	
	private final String ID_ENTITY = "entityID";
	
	private final String ENTITY_ID_PATH = "/{entityID}";
	
	@Autowired
	public CustomerController(CrudRepository<Customer, UUID> crudRepository, EntityLinks entityLinks) {		
		super(crudRepository);		
		this.crudRepository = crudRepository;
		
		this.entityLinks = entityLinks;
	}
	
	@GetMapping(path = "/h" ,produces =  "application/hal+json")
	public ResponseEntity<Resources<CustomerResource>> getHeteoas(){
		
		final Resources<CustomerResource> resources = new Resources<>(Lists.newArrayList(crudRepository.findAll()).stream().map(CustomerResource::new).collect(Collectors.toList()));
//		final String uriString = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
		
		resources.add(linkTo(methodOn(this.getClass()).getHeteoas()).withSelfRel());
		
		return ResponseEntity.ok(resources);
		
	}
	
	@GetMapping(path = "/hv2" ,produces =  "application/hal+json")
	public ResponseEntity<Resources<Customer>> getHeteoasV2(){		
		
		final Resources<Customer> resources = new Resources<>(Lists.newArrayList(crudRepository.findAll()).stream().collect(Collectors.toList()));
		
		resources.add(this.entityLinks.linkToCollectionResource(Customer.class));
		
		return ResponseEntity.ok(resources);
		
	}
	
	
	@GetMapping(path = ENTITY_ID_PATH + "/bonusCard")
	public ResponseEntity<Resource<BonusCard>> getBonusCard(@PathVariable(ID_ENTITY) UUID idCustomer){		
		
		
		Customer customer = crudRepository.findOne(idCustomer);
		
		if (customer == null){
			return ResponseEntity.badRequest().build();
		}else if (customer.getBonusCard() == null){
			return ResponseEntity.noContent().build();
		}		
		
		final Resource<BonusCard> resources = new Resource(customer.getBonusCard());
		
		return ResponseEntity.ok(resources);
	}

	@GetMapping(path = ENTITY_ID_PATH + "/creditCards")
	public ResponseEntity<Resources<CreditCard>> getCreditCards(@PathVariable(ID_ENTITY) UUID idCustomer){		
		
		
		Customer customer = crudRepository.findOne(idCustomer);
		
		if (customer == null){
			return ResponseEntity.badRequest().build();
		}else if (customer.getCreditCards().isEmpty()){
			return ResponseEntity.noContent().build();
		}		
		
		final Resources<CreditCard> resources = new Resources<>(customer.getCreditCards());
		resources.add(linkTo(methodOn(this.getClass()).getCreditCards(idCustomer)).withSelfRel());
		
		return ResponseEntity.ok(resources);
	}	
	
	
	
		
}
