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
import javax.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

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

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date activatedDate = null;

	@Length(min = 10)// Validation
    @Column @NaturalId // JPA
    @Setter(value = AccessLevel.NONE) //Lombook
    private String serialNumber;

	@Column
	@Setter(AccessLevel.NONE)
	private boolean enabled = false;

    @Column
    @Min(value = 0, message = "Le bonus ne doit pas être négatif")
    @Setter(AccessLevel.NONE)
    private int points = 120;


	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
		if(enabled){
			activatedDate = new Date();
		}
	}

    public void addBonusPoints(int points){
        Assert.isTrue(points >= 0, "Les points à ajouter ne doit pas être negative");
        this.points =+ points;
    }

    public void substractBonusPoints(int points){
        Assert.isTrue(points >= 0, "Les points à soustraire ne doit pas être negative");

        this.points =- points;

        // Remettre à zéro si le bonus devient négatif
        if (this.points < 0){
            this.points = 0;
        }
    }

}
