/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import org.badr.ordermanagement.entity.enums.PaymentType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author oussama
 */

@Entity
@Table(name = "[order]")
@NoArgsConstructor
@Getter @Setter
public class Order {	

	@EmbeddedId
	private OrderPrimaryKey orderPrimaryKey;
	
	@Column
	private Boolean isCanceled;
	
	@Transient
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Double totalPrice;
	
	@NotNull(message = "La date de livraison ne doit pas être null")
    @Column
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;	
	
	// Validation
	@NotNull(message = "Les lignes de commandes doit être renseignées")	
	@Size(min = 1, message = "Les lignes de commandes doit être renseignées")
    // TODO // Should not have getter and setter
	// JPA
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "PAYMENT_CREDITCARD_ID")
	private CreditCard creditCard;
	
	@ManyToOne
	@JoinColumn(name = "BONUSCARD_ID_USED")
	private BonusCard bonusCard;

    /**
     * Calculate the total order price base on {@code orderDetails}
     * @return Total order price
     */
    public Double getTotalPrice() {
		totalPrice = orderDetails.stream()//
                            .mapToDouble(od -> od.getProduct().getUnitPrice())//
                            .sum();
        return totalPrice;
    }

}