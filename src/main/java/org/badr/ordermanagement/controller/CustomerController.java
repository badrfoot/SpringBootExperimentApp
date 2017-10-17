/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import java.io.IOException;
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.respository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author OBD
 */
@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
	
//	@Autowired private EntityManager entityManager;
	
	@Autowired private CustomerRepository customerRepository;
	
	@Autowired private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	final String CUSTOMER_ID_PATH = "/{idCustomer}";
	
	final String ID_CUSTOMER = "idCustomer";
	
	
	@GetMapping
	public ResponseEntity<?> getAllCustomer(){
		return ResponseEntity.ok(customerRepository.findAll());
	}
	
	@GetMapping(path = CUSTOMER_ID_PATH)
	public ResponseEntity<?> getCustomerById(@PathVariable(ID_CUSTOMER) Customer customer){		
		return ResponseEntity.ok(customer);
	}
	
	
	
	@GetMapping(params = ID_CUSTOMER)
	public ResponseEntity<?> getCustomerByIdParam(@RequestParam(ID_CUSTOMER) Customer customer){		
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer transientCustomer){
		
		Customer customer =  customerRepository.save(transientCustomer);

		URI location = ControllerLinkBuilder.linkTo(this.getClass()).slash(customer.getId()).toUri();		
		return ResponseEntity.created(location).body(customer);
	}
	
	@DeleteMapping(path = CUSTOMER_ID_PATH)
	public ResponseEntity<?> deleteCustomer(@PathVariable UUID idCustomer){
		
		customerRepository.delete(idCustomer);
		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(path = CUSTOMER_ID_PATH)
	public ResponseEntity<?> patchCustomer(@PathVariable(ID_CUSTOMER) Customer oldCustomer, @RequestBody String dataToPatch){
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;		
		
		Customer newCustomer = null;
		
		if (oldCustomer != null){
			newCustomer = applyPatch(oldCustomer, dataToPatch);
			newCustomer	= customerRepository.save(newCustomer);
			httpStatus = HttpStatus.OK;
		}
		
		return new ResponseEntity<>(newCustomer, httpStatus);
	}
	
	@PutMapping
	public ResponseEntity<?> putCustomer(@RequestBody Customer customer){		
		customerRepository.save(customer);
		return  ResponseEntity.ok(customer);
	}	
	
	/**
	 * Appliquer le patch {@code jsonPatchContent} sur {@code originBean}. 
	 * @param originBean L'objet (l'entité) origine et l
	 * @param jsonPatchContent L'opération patch demandée, doit respecter les spécifications Patch <a href=https://tools.ietf.org/html/rfc6902>RFC6902</a> 
	 * @param beanClassType Le type du bean (l'entité)
	 * @return L'objet bean après le patch.
	 * @throws RuntimeException
	 */
	private Customer applyPatch(Customer oldCustomer, String jsonPatchContent) {
		
		ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();
		
		try {
			JsonPatch jsonPatch = objectMapper.readValue(jsonPatchContent, JsonPatch.class);
			JsonNode jsonNodeOriginBean = objectMapper.convertValue(oldCustomer, JsonNode.class);
			JsonNode patchedBean = jsonPatch.apply(jsonNodeOriginBean);
			
			// Récupérer le nouveau objet après le patch 
			return objectMapper.treeToValue(patchedBean, Customer.class);
			
		} catch (IOException | JsonPatchException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
}
