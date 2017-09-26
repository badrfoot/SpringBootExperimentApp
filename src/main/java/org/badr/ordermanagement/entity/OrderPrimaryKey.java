/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author OBD
 */
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor @Getter @Setter @EqualsAndHashCode
public class OrderPrimaryKey implements Serializable{
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "CUSTOMER_ID")
	private Customer customer;
			
    @Column(name = "ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)	
    private Date orderDate;
	
}
