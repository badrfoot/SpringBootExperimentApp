/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.respository;

import org.badr.ordermanagement.entity.Category;
import org.badr.ordermanagement.entity.OrderPrimaryKey;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author OBD
 */
public interface CategoryRepository extends CrudRepository<Category, OrderPrimaryKey>{
	
}
