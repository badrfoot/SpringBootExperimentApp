/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.Json.Entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.diff.JsonDiff;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import org.badr.ordermanagement.JsonITest;
import org.badr.ordermanagement.entity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 *
 * @author OBD
 */

public class CustomerITest extends JsonITest{
	
	@Autowired private JacksonTester<Customer> customerJacksonTester;
	@Autowired private ObjectMapper objectMapper;	
	
	private Customer customer;
	
	
	@Before
	public void before(){
		customer = new Customer("Bill", "Gate");
	}	
	
	@Test
	public void simpleTest() throws IOException{
		
		
		
		customer.setBirthDate(LocalDate.of(2000, Month.MARCH, 15));		
		
		System.out.println(customerJacksonTester.write(customer).getJson());
		JsonNode jsonNodeSituationFamilleOrigin = objectMapper.convertValue(customer, JsonNode.class);		
		
		customer.setFirstName("Steve");
		JsonNode jsonNodeSituationFamilleAfterUpdate = objectMapper.convertValue(customer, JsonNode.class);
		String jsonPatch = JsonDiff.asJson(jsonNodeSituationFamilleOrigin, jsonNodeSituationFamilleAfterUpdate).toString();
		
		System.out.println("*******" + jsonPatch + "********");
		
		
	}
	
	
}
