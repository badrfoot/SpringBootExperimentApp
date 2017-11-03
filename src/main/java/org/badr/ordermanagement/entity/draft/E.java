/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.entity.draft;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.badr.ordermanagement.entity.AbstractBaseEntity;

/**
 *
 * @author oussama
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class E extends AbstractBaseEntity{

    @Getter @Setter
    @OneToMany(mappedBy = "e")
    private List<En> en = new ArrayList<>();

    @Column
    private String myE = this.getClass().getName();



    protected void doSomthing(){
        System.out.println("I'm doing something special !!!");
    }
}
