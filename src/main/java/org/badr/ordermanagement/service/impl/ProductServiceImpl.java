/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.service.impl;

import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.badr.ordermanagement.entity.OrderDetail;
import org.badr.ordermanagement.entity.Product;
import org.badr.ordermanagement.entity.QOrderDetail;
import org.badr.ordermanagement.entity.QProduct;
import org.badr.ordermanagement.respository.ProductRepository;
import org.badr.ordermanagement.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author OBD
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository ;

    @PersistenceContext
    EntityManager entityManager;


	@Override
	public Product getTopSelledProduct() {
		return productRepository.findTopOrderedProducts();
	}

	@Override
	public Product getLessSelledProduct() {
		return productRepository.findLessOrderedProducts();
	}

	@Override
	public List<Product> getNonOrderedProducts() {
		return productRepository.findNonOrderedProducts();
	}

	@Override
	public Optional<Product> getTopCanceledProducts() {

        QOrderDetail qOrderDetail = QOrderDetail.orderDetail;

        JPAQuery<OrderDetail> queryOrderDetail = new JPAQuery<>(entityManager);

        return Optional.ofNullable(//
                            queryOrderDetail.select(qOrderDetail.product)//
                                            .from(qOrderDetail)//
                                            .groupBy(qOrderDetail.product)//
                                            .orderBy(qOrderDetail.canceled.count().max().desc())//
                                            .fetchFirst());
	}



}
