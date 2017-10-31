/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service;

import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.badr.ordermanagement.service.impl.OrderServiceImpl;
import org.badr.ordermanagement.AbstractITest;
import org.badr.ordermanagement.entity.Customer;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author OBD
 */
public class OrderServiceTest extends AbstractITest{
	
	@Autowired	private OrderServiceImpl orderService;
	
	@PersistenceContext private EntityManager entityManager;

	@Test //@Ignore
	public void justSimpleTest(){
		
		
		System.out.println("***********get Customer whithout graph");
		entityManager.clear();
		System.out.println("");
		entityManager.createNamedQuery("Customer.findAll").getResultList().forEach(System.out::println);
		
		UUID idCustomer = entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList().stream().findFirst().get().getId();
		
		System.out.println("***********get Customer whith graph");
		entityManager.clear();
		System.out.println("");
		
		entityManager.createNamedQuery("Customer.findAll")
					.setHint("javax.persistence.fetchgraph", 
							entityManager.getEntityGraph("Customer.findAllWithAllFetch"))
				.getResultList().forEach(System.out::println);
		
		
		System.out.println("***********get Customer whith graph - session");
		entityManager.clear();
		Session session = entityManager.unwrap(Session.class);
		
		session.enableFetchProfile("WithJustString");
		System.out.println(session.get(Customer.class, idCustomer));
		
		
	}
	
	
}
