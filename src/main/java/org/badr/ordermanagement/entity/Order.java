/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author oussama
 */

@Entity
@NoArgsConstructor
@Getter @Setter
public class Order extends AbstractBaseEntity{

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    // TODO // Should not have getter and setter
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @Transient
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE)
    private Double totalPrice;

    /**
     * Calculate the total order price base on {@code orderDetails}
     * @return Total order price
     */
    public Double getTotalPrice() {
        return orderDetails.stream()//
                            .mapToDouble(od -> od.getProduct().getUnitPrice())//
                            .sum();
    }


private enum PaymentType{
    CreditCard, Cash
}

}


