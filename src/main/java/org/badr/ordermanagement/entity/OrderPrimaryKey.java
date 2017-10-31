/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author OBD
 */
@Embeddable
@Access(AccessType.FIELD)
@lombok.NoArgsConstructor @lombok.AllArgsConstructor @lombok.Getter @lombok.EqualsAndHashCode
public class OrderPrimaryKey implements Serializable{

	@ManyToOne(optional = false)
	@JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @javax.validation.constraints.NotNull(message = "Une commande doit être affectée à un client")
	private Customer customer;

    @Column(name = "ORDER_DATE", nullable = false)
    @javax.validation.constraints.NotNull(message = "Une commande doit avoir une date")
    private LocalDate orderDate;

}
