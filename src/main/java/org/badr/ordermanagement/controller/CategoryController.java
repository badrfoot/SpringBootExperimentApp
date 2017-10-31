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
import java.net.URI;
import java.util.UUID;
import javax.validation.Valid;
import org.badr.ordermanagement.entity.Category;
import org.badr.ordermanagement.respository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author OBD
 */
@RestController
@RequestMapping(path = "/category")
public class CategoryController {
	
	@Autowired private CategoryRepository categoryRepository;
	
	@Autowired private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	final String ID_CATEGORY = "idCategory";
	
	final String CATEGORY_ID_PATH = "/{idCategory}";	
	
	
	@GetMapping
	public ResponseEntity<?> getAllCategory(){
		return ResponseEntity.ok(categoryRepository.findAll());
	}
	
	@GetMapping(path = CATEGORY_ID_PATH)
	public ResponseEntity<?> getCategoryById(@PathVariable(ID_CATEGORY) Category category){		
		return ResponseEntity.ok(category);
	}	
	
	@GetMapping(params = ID_CATEGORY)
	public ResponseEntity<?> getCategoryByIdParam(@RequestParam(ID_CATEGORY) Category category){		
		return ResponseEntity.ok(category);
	}
	
	@PostMapping
	public ResponseEntity<?> addCategory(@Valid @RequestBody Category transientCategory){
		
		Category category =  categoryRepository.save(transientCategory);

		URI location = ControllerLinkBuilder.linkTo(this.getClass()).slash(category.getId()).toUri();		
		return ResponseEntity.created(location).body(category);
	}
	
	@DeleteMapping(path = CATEGORY_ID_PATH)
	public ResponseEntity<?> deleteCategory(@PathVariable UUID idCategory){		
		categoryRepository.delete(idCategory);
		
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(path = CATEGORY_ID_PATH)
	public ResponseEntity<?> patchCategory(@PathVariable(ID_CATEGORY) Category oldCategory, @RequestBody String dataToPatch){
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;		
		
		Category newCategory = null;
		
		if (oldCategory != null){
			newCategory = applyPatch(oldCategory, dataToPatch);
			newCategory	= categoryRepository.save(newCategory);
			httpStatus = HttpStatus.OK;
		}
		
		return new ResponseEntity<>(newCategory, httpStatus);
	}
	
	@PutMapping
	public ResponseEntity<?> putCategory(@RequestBody Category category){		
		categoryRepository.save(category);
		return  ResponseEntity.ok(category);
	}	
	
	/**
	 * Appliquer le patch {@code jsonPatchContent} sur {@code originBean}. 
	 * @param originBean L'objet (l'entité) origine et l
	 * @param jsonPatchContent L'opération patch demandée, doit respecter les spécifications Patch <a href=https://tools.ietf.org/html/rfc6902>RFC6902</a> 
	 * @param beanClassType Le type du bean (l'entité)
	 * @return L'objet bean après le patch.
	 * @throws RuntimeException
	 */
	private Category applyPatch(Category oldCategory, String jsonPatchContent) {
		
		ObjectMapper objectMapper = mappingJackson2HttpMessageConverter.getObjectMapper();
		
		try {
			JsonPatch jsonPatch = objectMapper.readValue(jsonPatchContent, JsonPatch.class);
			JsonNode jsonNodeOriginBean = objectMapper.convertValue(oldCategory, JsonNode.class);
			JsonNode patchedBean = jsonPatch.apply(jsonNodeOriginBean);
			
			// Récupérer le nouveau objet après le patch 
			return objectMapper.treeToValue(patchedBean, Category.class);
			
		} catch (IOException | JsonPatchException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
}
