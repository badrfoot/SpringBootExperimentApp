/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author oussama
 */
@Embeddable
@NoArgsConstructor
@Getter @Setter
public class Address{

    @Column
    private String localAddress;

    @Column
    private String City;

    @Column
    private String Country;

    @Column
    private String Planet;

}
