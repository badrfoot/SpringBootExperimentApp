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
import org.hibernate.validator.constraints.Length;

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
	private Boolean enabled = false;
	

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
		if(enabled){
			activatedDate = new Date();
		}
	}
	
	
	

}
