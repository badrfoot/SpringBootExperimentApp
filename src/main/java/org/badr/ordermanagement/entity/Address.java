/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.badr.ordermanagement.entity.enums.PlanetEnum;

/**
 *
 * @author oussama
 */
@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor @Getter @Setter
public class Address{

    @Column
    private String localAddress;

    @Column
    private String city;

    @Column
	@Setter(AccessLevel.NONE)
    private String country;

    @Column
	@Enumerated(EnumType.STRING)
    private PlanetEnum planet;

	
	public void  setCountry(String country) {
		if ((planet == PlanetEnum.Earth) && (!"France".equals(country.trim()))){
			throw new IllegalArgumentException("Le seul pays de résidence accepté pour la planete [Earth] est la France");
		}
		
		this.country = country;
	}
	
}
