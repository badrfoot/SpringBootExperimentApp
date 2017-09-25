/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Product extends AbstractBaseEntity{

    @Column
    private String name;

    @Column
    private float unitPrice;

    @Column
    private Float storedQuantity;

}
