/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.respository;

import java.util.List;
import java.util.UUID;
import org.badr.ordermanagement.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author OBD
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, UUID>{
	
	@Query(value = "SELECT p FROM Product p WHERE p.id IN (SELECT MAX(quantity) FROM OrderDetail od WHERE od.product = p GROUP PY od.product)")
	List<Product> findTopOrderedProducts();
	
	@Query(value = "SELECT p FROM Product p WHERE p.id IN (SELECT MIN(quantity) FROM OrderDetail od WHERE od.product = p GROUP PY od.product)")
	List<Product> findLessOrderedProducts();
	
	
	
}
