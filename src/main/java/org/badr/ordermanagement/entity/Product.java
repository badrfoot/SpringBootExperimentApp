/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
public class Product extends AbstractBaseEntity{

	// JPA
    @Column
	// Validation
	@NotNull(message = "Le produit doit avoir un nom")
    private String name;

    @Column
    private float unitPrice;

	// JPA
    @Column
	// Validation
	@Min(value = 1, message = "La quantité initial doit être plus ou égale à 1")
    private Float storedQuantity;
	
	// Validation
	@NotNull(message = "Le produit doit avoir une catégorie")
	// JPA
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
}
