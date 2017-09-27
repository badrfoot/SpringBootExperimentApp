/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.respository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;
import org.badr.ordermanagement.entity.Customer;
import org.badr.ordermanagement.entity.enums.PlanetEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author OBD
 */
public interface CustomerRepository extends CrudRepository<Customer, UUID>{

	@Query(value = "SELECT COUNT(o) FROM Order o INNER JOIN o.orderPrimaryKey.customer c")	
	Long countOrderForCustomer(Customer customer);
	
	@Query(value = "SELECT o.orderPrimaryKey.customer FROM Order o")
	List<Customer> findCustomerThatHasAtLeastOneOrder();	
	
	Optional<Customer> findTopByOrderByBirthDateAsc();
	
	Stream<Customer> findByAddressPlanetIn(Collection<PlanetEnum> planetEnum);
	
	Stream<Customer> findByAddressPlanetNotIn(Collection<PlanetEnum> planetEnum);
	
}
