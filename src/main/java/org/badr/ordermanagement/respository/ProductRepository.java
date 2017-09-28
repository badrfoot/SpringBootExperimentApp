/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.respository;

import java.util.Optional;
import java.util.UUID;
import org.badr.ordermanagement.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author OBD
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, UUID>{

	@Query(value = "SELECT od.product, MAX(SUM(od.quantity)) FROM OrderDetail od GROUP BY od.product")
	Product findTopOrderedProducts();

	@Query(value = "SELECT od.product, MIN(SUM(od.quantity)) FROM OrderDetail od GROUP BY od.product")
	Product findLessOrderedProducts();


    @Query(value = "SELECT p FROM  OrderDetail odp RIGHT JOIN odp.product p")
    Optional<Product> findNonOrderedProducts();

//    @Query(value = "SELECT od.product, MAX(COUNT(od)) FROM OrderDetail od WHERE od.")
//    Optional<Product> findTopCanceledProduct();

}
