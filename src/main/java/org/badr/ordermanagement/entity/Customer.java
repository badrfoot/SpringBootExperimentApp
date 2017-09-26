/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Customer extends AbstractBaseEntity{

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Embedded
    private Address address;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "BONUSCARD_ID")
    private BonusCard bonusCard;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREDITCARD_ID")
    private CreditCard creditCard;

}
