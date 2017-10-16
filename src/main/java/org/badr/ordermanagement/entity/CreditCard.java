/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.badr.ordermanagement.entity.enums.TypeCreditCard;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author oussama
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE) 
@Getter
public class CreditCard extends AbstractBaseEntity{

	@JsonFormat(pattern = "yyyy-MM-dd")
    @Column @Temporal(TemporalType.DATE)
    private Date expirationDate;

    @Enumerated(EnumType.STRING)
    private TypeCreditCard type;
	

	public CreditCard(Date expirationDate, TypeCreditCard type) {
		this.expirationDate = expirationDate;
		this.type = type;
	}
	
	public Boolean isValid(){
		Date today = new Date();
		
		return today.before(expirationDate);
	}



}