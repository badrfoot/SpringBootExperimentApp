/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.badr.ordermanagement.entity.enums.TypeCreditCard;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.badr.ordermanagement.controller.deserializer.EntityIdResolver;

/**
 *
 * @author oussama
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE) 
@Getter
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", 
				  scope = CreditCard.class, resolver = EntityIdResolver.class)
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