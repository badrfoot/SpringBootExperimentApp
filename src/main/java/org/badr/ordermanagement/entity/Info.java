/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

/**
 *
 * @author oussama
 */
@Embeddable
@RequiredArgsConstructor
@Access(javax.persistence.AccessType.FIELD) @NoArgsConstructor
@Getter @Setter
public class Info {

    @NonNull
    private String nameMe;

    @NonNull
    private Long poids;

    @OneToMany(mappedBy = "customer")
    List<City> cities = new ArrayList<>();

}
