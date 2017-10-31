/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author oussama
 */
@MappedSuperclass
@Getter @NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, 
				getterVisibility = JsonAutoDetect.Visibility.NONE, 
				isGetterVisibility = JsonAutoDetect.Visibility.NONE, 
				setterVisibility = JsonAutoDetect.Visibility.NONE)
public abstract class AbstractBaseEntity implements InterfaceBaseEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
	

    @Override
	// Id should not be used for hasCode as its value changed from null to UUID -- Ok it's just a test
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        final AbstractBaseEntity other = (AbstractBaseEntity) obj;


        if ((other.getId() == null) || (!Objects.equals(this.getId(), other.getId()))) return false;

        return true;
    }



}
