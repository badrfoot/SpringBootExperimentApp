/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.respository;

import java.util.UUID;
import org.badr.ordermanagement.entity.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author OBD
 */
public interface CustomerRepository extends CrudRepository<Customer, UUID>{
	
}
