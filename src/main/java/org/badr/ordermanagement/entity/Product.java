/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.text.DecimalFormat;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.springframework.util.Assert;

/**
 *
 * @author oussama
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product extends AbstractBaseEntity {

    /**
     * Round {@link #storedQuantity} to 2 decimal places before persisting to DB
     */
    @PrePersist
    public void onPrePersist() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        storedQuantity = Float.parseFloat(decimalFormat.format(storedQuantity));
    }

    // JPA
    @Column
    @NaturalId
    // Validation
    @NotNull(message = "Le produit doit avoir un nom")
    private String name;

    // Validation
    @NotNull(message = "Le produit doit avoir une catégorie")
    // JPA
    @NaturalId
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Column
    private float unitPrice;

    // JPA
    @Column
    // Validation
    @javax.validation.constraints.Min(value = 0, message = "La quantité initial doit être un nombre positive")
    @lombok.Setter(AccessLevel.NONE)
    private float storedQuantity;

    /**
     *
     * @param quantityToSubstract
     */
    public void substractStoredQuantity(float quantityToSubstract) {
        Assert.isTrue(quantityToSubstract > 0,
                      "La quantité à soustraire ne doit pas être négative");
        Assert.isTrue(doesQuantityExist(quantityToSubstract),
                      "La quantité à soustraire ne doit pas dépasser la quantité en stock");

        storedQuantity =- quantityToSubstract;
    }

    /**
     *
     * @param quantity
     * @return
     */
    public Boolean doesQuantityExist(float quantity){
        Assert.isTrue(quantity > 0, "Le paramètre [quantity] ne doit pas être négative");
        return this.storedQuantity >= quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.category)//
                + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Product other = (Product) obj;

        return (((super.equals(other)) || (other.getId() == null) || (getId() == null))
                && (getCategory().equals(other.getCategory()) && getName().equals(other.getName())));
    }

}
