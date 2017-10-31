/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.badr.ordermanagement.controller.deserializer.EntityIdResolver;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author oussama
 */
@Entity
@NoArgsConstructor
@Getter @Setter
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", 
				  scope = Category.class, resolver = EntityIdResolver.class)
public class Category extends AbstractBaseEntity{

    @Column
    @NaturalId
    private String name;

}
