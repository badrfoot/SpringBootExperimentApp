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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class CreditCard extends AbstractBaseEntity{

    @Column
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Enumerated(EnumType.STRING)
    private TypeCreditCard type;

private enum TypeCreditCard{
    Visa, MasterCard, PayBal
}

}
