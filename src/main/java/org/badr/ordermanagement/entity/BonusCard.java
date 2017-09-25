/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author oussama
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class BonusCard extends AbstractBaseEntity{

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @Column @NaturalId
    @Setter(value = AccessLevel.NONE)
    private String serialNumber;

}
