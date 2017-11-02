/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.badr.ordermanagement.controller;

import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import org.badr.ordermanagement.entity.AbstractBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author OBD
 * @param <Entity>
 */
public abstract class AbstractControllerWithUUID<Entity extends AbstractBaseEntity> extends AbstractCommonControllerCapabilities{
	
	private CrudRepository<Entity, UUID> crudRepository;
	
	@Autowired private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	private final String ID_ENTITY = "entityID";
	
	private final String ENTITY_ID_PATH = "/{entityID}";
	
	public AbstractControllerWithUUID(CrudRepository<Entity, UUID> crudRepository) {
		this.crudRepository = crudRepository;	
	}	
	
	protected void setCrudRepository(CrudRepository<Entity, UUID> crudRepository) {
		this.crudRepository = crudRepository;
	}	
	
	@GetMapping
	public ResponseEntity<?> getAllEntities(){
		return ResponseEntity.ok(crudRepository.findAll());
	}
	
	@GetMapping(path = ENTITY_ID_PATH)
	public ResponseEntity<?> getEntityById(@PathVariable(ID_ENTITY) UUID idEntity){		
		return ResponseEntity.ok(crudRepository.findOne(idEntity));
	}	
//	@GetMapping(path = ENTITY_ID_PATH)
//	public ResponseEntity<?> getEntityById(@PathVariable(ID_ENTITY) Entity entity){		
//		return ResponseEntity.ok(entity);
//	}	
	
	@GetMapping(params = ID_ENTITY)
	public ResponseEntity<?> getEntityByIdParam(@RequestParam(ID_ENTITY) Entity entity){		
		return ResponseEntity.ok(entity);
	}
	
	@PostMapping
	public ResponseEntity<?> addEntity(@Valid @RequestBody Entity transientEntity){
		
		Entity entity =  crudRepository.save(transientEntity);

		URI location = ControllerLinkBuilder.linkTo(this.getClass()).slash(entity.getId()).toUri();		
		return ResponseEntity.created(location).body(entity);
	}
	
	@DeleteMapping(path = ENTITY_ID_PATH)
	public ResponseEntity<?> deleteEntity(@PathVariable UUID entityID){		
		crudRepository.delete(entityID);
		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(path = ENTITY_ID_PATH)
	public ResponseEntity<?> patchEntity(@PathVariable(ID_ENTITY) Entity oldEntity, @RequestBody String dataToPatch){
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;		
		
		Entity newEntity = null;
		
		if (oldEntity != null){
			newEntity = applyPatch(oldEntity, dataToPatch);
			newEntity	= crudRepository.save(newEntity);
			httpStatus = HttpStatus.OK;
		}
		
		return new ResponseEntity<>(newEntity, httpStatus);
	}
	
	@PutMapping
	public ResponseEntity<?> putEntity(@RequestBody Entity entity){		
		crudRepository.save(entity);
		return  ResponseEntity.ok(entity);
	}
	
}
