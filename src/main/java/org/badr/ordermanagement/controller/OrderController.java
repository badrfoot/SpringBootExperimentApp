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
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.Valid;
import org.badr.ordermanagement.entity.AbstractBaseEntity;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.entity.Order;
import org.badr.ordermanagement.entity.OrderPrimaryKey;
import org.badr.ordermanagement.respository.CustomerRepository;
import org.badr.ordermanagement.respository.OrderRepository;
import org.badr.ordermanagement.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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
@RequestMapping(path = "/order")
public class OrderController extends AbstractCommonControllerCapabilities{

	@Autowired private OrderRepository orderRepository;

	@Autowired private OrderService orderService;


	private final String ID_ORDER = "orderID";

	private final String ORDER_ID_PATH = "/{customerID}/{orderDate}";

	private final String ID_CUSTOMER = "/{customerID}/{orderDate}";

	private final String ORDER_DATE = "/{customerID}/{orderDate}";


	@GetMapping
	public ResponseEntity<?> getAllOrders(){
		return ResponseEntity.ok(orderRepository.findAll());
	}

	@GetMapping(path = ORDER_ID_PATH)
	public ResponseEntity<?> getOrderById(@PathVariable(ORDER_DATE) LocalDate orderDate, @PathVariable(ID_CUSTOMER) Customer customer){
		return ResponseEntity.ok(orderRepository.findOne(new OrderPrimaryKey(customer , orderDate)));
	}

	@GetMapping(params = {ID_CUSTOMER, ID_ORDER})
	public ResponseEntity<?> getOrderByIdParam(@RequestParam(ID_CUSTOMER) Customer customer, @PathVariable(ORDER_DATE) LocalDate orderDate){
		return ResponseEntity.ok(orderRepository.findOne(new OrderPrimaryKey(customer , orderDate)));
	}

	@PostMapping
	public ResponseEntity<?> addOrder(@Valid @RequestBody Order transientOrder){

		Order order =  orderRepository.save(transientOrder);

		URI location = ControllerLinkBuilder.linkTo(this.getClass()).slash(order.getOrderPrimaryKey().getCustomer().getId())//
																	.slash(order.getOrderPrimaryKey().getOrderDate())//
																	.toUri();
		return ResponseEntity.created(location).body(order);
	}

	@DeleteMapping(path = ORDER_ID_PATH)
	public ResponseEntity<?> deleteOrder(@PathVariable(ORDER_DATE) LocalDate orderDate, @PathVariable(ID_CUSTOMER) Customer customer){

		orderRepository.delete(new OrderPrimaryKey(customer , orderDate));
		return ResponseEntity.noContent().build();

	}

	@PatchMapping(path = ORDER_ID_PATH)
	public ResponseEntity<?> patchOrder(@PathVariable(ORDER_DATE) LocalDate orderDate, @PathVariable(ID_CUSTOMER) Customer customer, @RequestBody String dataToPatch){

		HttpStatus httpStatus = HttpStatus.NOT_FOUND;

		Order oldOrder = orderRepository.findOne(new OrderPrimaryKey(customer , orderDate));
		Order newOrder = null;

		if (oldOrder != null){
			newOrder = applyPatch(oldOrder, dataToPatch);
			newOrder	= orderRepository.save(newOrder);
			httpStatus = HttpStatus.OK;
		}

		return new ResponseEntity<>(newOrder, httpStatus);
	}

	@PutMapping
	public ResponseEntity<?> putOrder(@Valid @RequestBody Order order){
		orderRepository.save(order);
		return  ResponseEntity.ok(order);
	}

}
