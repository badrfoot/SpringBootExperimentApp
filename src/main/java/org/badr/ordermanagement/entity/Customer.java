/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.badr.ordermanagement.controller.deserializer.EntityIdResolver;
import org.hibernate.validator.constraints.Email;
import org.springframework.hateoas.core.Relation;
import org.springframework.util.Assert;

/**
 *
 * @author oussama
 */
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Getter @Setter @ToString
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id", 
				  scope = Customer.class, resolver = EntityIdResolver.class)
@NamedQueries({
	@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
})
public class Customer extends AbstractBaseEntity {

	@Column
	@Email
	private String email;

    @Column
	@lombok.NonNull
	@NotNull 
    private String firstName;

    @Column
	@lombok.NonNull
	@NotNull
    private String lastName;

	@JsonFormat(pattern = "yyyy-MM-dd")
    @lombok.Setter(AccessLevel.NONE)
    @Column
    private LocalDate birthDate;

    @Embedded
    private Address address;	
	
    @JsonIdentityReference(alwaysAsId=true)
	@Setter(AccessLevel.NONE)
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "BONUSCARD_ID")	
    private BonusCard bonusCard = null;
	
    @JsonIdentityReference(alwaysAsId=true)
	@Setter(AccessLevel.NONE)
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "CREDITCARD_ID")		
    private List<CreditCard> creditCards = new ArrayList<>();
	
	public String getFullName(){
		return String.format("%s %s", getFirstName(), getLastName().toUpperCase());
	}
	
    public final void setBirthDate(LocalDate birthDate) {
        Assert.notNull(birthDate, "La date de naissance ne doit pas être null!");
        Assert.isTrue(ChronoUnit.YEARS.between(birthDate, LocalDate.now()) >= 17,
                       "Le client doit avoir au moins 17 ans!");
        this.birthDate = birthDate;
    }

	public void setBonusCard(BonusCard bonusCard) {
		if (this.bonusCard != null){
			throw new UnsupportedOperationException("Le consommateur a déjà une carte de fidélité!");
		}
		this.bonusCard = bonusCard;
	}

	public Boolean addCreditCard(CreditCard creditCards) {
		if ( !(creditCards.isValid()) ){
			return false;
		}
		return this.creditCards.add(creditCards);
	}

    public Boolean doesCreditCardExist(CreditCard creditCard) {
        return this.creditCards.stream().anyMatch(ctcrd -> ctcrd.equals(creditCard));
    }

}
