/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class City extends AbstractBaseEntity{

    @Column
    @NaturalId
    private String name;

    @ManyToOne
    private Customer customer;

}
