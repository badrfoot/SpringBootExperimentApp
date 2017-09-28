/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service.interfaces;

import java.util.List;
import java.util.Optional;
import org.badr.ordermanagement.entity.Product;

/**
 *
 * @author OBD
 */
public interface ProductService {

	Product getTopSelledProduct();

	Product getLessSelledProduct();

	Optional<Product> getNonOrderedProducts();

	List<Product> getTopCanceledProducts();

}
