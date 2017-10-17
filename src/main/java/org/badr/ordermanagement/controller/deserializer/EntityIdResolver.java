/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller.deserializer;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author OBD
 */
public class EntityIdResolver implements ObjectIdResolver {

	@Autowired private EntityManager entityManager;
	

    public EntityIdResolver(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void bindItem(final ObjectIdGenerator.IdKey id, final Object pojo) {

    }

    @Override
    public Object resolveId(final ObjectIdGenerator.IdKey id) {

        return this.entityManager.find(id.scope, id.key);
    }

    @Override
    public ObjectIdResolver newForDeserialization(final Object context) {

        return this;
    }

    @Override
    public boolean canUseFor(final ObjectIdResolver resolverType) {

        return false;
    }

}
