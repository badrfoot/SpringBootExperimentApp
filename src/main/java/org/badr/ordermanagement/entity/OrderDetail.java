/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author oussama
 */
@Entity
@NoArgsConstructor
@Getter @Setter
public class OrderDetail extends AbstractBaseEntity{

    @NaturalId
    @ManyToOne(optional = false)
	@JoinColumns({
			@JoinColumn(name = "ORDER_CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID"),
			@JoinColumn(name = "ORDER_DATE", referencedColumnName = "ORDER_DATE")			
	})    
    private Order order;

    @NaturalId
    @ManyToOne(optional = false)	
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Float quantity;

}
