/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import java.io.IOException;
import org.badr.ordermanagement.entity.InterfaceBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 *
 * @author OBD
 */
public abstract class AbstractCommonControllerCapabilities {
	
	@Autowired private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;	
	
	
	/**
	 * Appliquer le patch {@code jsonPatchContent} sur {@code originBean}. 
	 * @param <E>
	 * @param oldEntity L'objet (l'entité) origine
	 * @param jsonPatchContent L'opération patch demandée, doit respecter les spécifications Patch <a href=https://tools.ietf.org/html/rfc6902>RFC6902</a> 	 
	 * @return L'objet bean après le patch.
	 * @throws RuntimeException
	 */
	protected <E extends InterfaceBaseEntity> E applyPatch(E oldEntity, String jsonPatchContent) {
		
		ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();
		
		try {
			JsonPatch jsonPatch = objectMapper.readValue(jsonPatchContent, JsonPatch.class);
			JsonNode jsonNodeOriginBean = objectMapper.convertValue(oldEntity, JsonNode.class);
			JsonNode patchedBean = jsonPatch.apply(jsonNodeOriginBean);
			
			// Récupérer le nouveau objet après le patch 
			return (E)objectMapper.treeToValue(patchedBean, oldEntity.getClass());
			
		} catch (IOException | JsonPatchException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
}
