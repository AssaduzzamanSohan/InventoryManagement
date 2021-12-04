package com.inventoryManagement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventoryManagement.constants.ActionType;
import com.inventoryManagement.entity.Models;
import com.inventoryManagement.repository.ModelsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ModelService {

	@Autowired
	private ModelsRepository modelRepository;

	/**
	 * Developer will just call this method with information in model object and
	 * what to do in actionType
	 * 
	 * @param model
	 * @param actionType
	 * @return List<Models>
	 * @throws Exception
	 */
	public List<Models> handleSingleRequest(Models model, String actionType) throws Exception {
		if (actionType.matches(ActionType.SELECT.toString())) {
			return select(model);
		} else if (actionType.matches(ActionType.SELECT_ALL.toString())) {
			return selectAll();
		} else if (actionType.matches(ActionType.NEW.toString()) || actionType.matches(ActionType.SAVE.toString())) {
			return save(model);
		} else if (actionType.matches(ActionType.UPDATE.toString())) {
			return update(model);
		} else if (actionType.matches(ActionType.DELETE.toString())) {
			return delete(model);
		} else if (actionType.matches(ActionType.SELECT_BY_BRAND_ID.toString())) {
			return selectAllByBrandId(model);
		} else {
			throw new Exception("Unknown action " + actionType);
		}
	}

	/**
	 * Select's data by model id
	 * 
	 * @param model
	 * @return List<Models>
	 * @throws Exception
	 */
	private List<Models> select(Models model) throws Exception {
		try {
			return modelRepository.findById(model.getId());
		} catch (Exception e) {
			log.error("Exception selecting Models [{}]", model.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * Select all data from brand table with entryDate asc
	 * 
	 * @return List<Models>
	 * @throws Exception
	 */
	private List<Models> selectAll() throws Exception {
		try {
			return modelRepository.findAll(new Sort(Sort.Direction.ASC, "entryDate"));
		} catch (Exception e) {
			log.error("Exception selecting all Models [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * select all model of a brand
	 * 
	 * @param model
	 * @return List<Models>
	 * @throws Exception
	 */
	private List<Models> selectAllByBrandId(Models model) throws Exception {
		try {
			return modelRepository.findByBrandId(model.getBrandId(), new Sort(Sort.Direction.ASC, "name"));
		} catch (Exception e) {
			log.error("Exception selecting all Models [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * Save brand info, and update if brand id found in object
	 * 
	 * @param brand
	 * @return List<Models>
	 * @throws Exception
	 */
	private List<Models> save(Models model) throws Exception {
		try {
			return Arrays.asList(modelRepository.save(model));
		} catch (Exception e) {
			log.error("Exception saving Models [{}]", model.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * update model, call it when you are sure
	 * 
	 * @param model
	 * @return List<Models>
	 * @throws Exception
	 */
	private List<Models> update(Models model) throws Exception {
		try {
			if (model.getId() == 0) {
				throw new Exception("Models id not found in Object, First Find then try to update");
			}

			return Arrays.asList(modelRepository.save(model));
		} catch (Exception e) {
			log.error("Exception updating Models [{}]", model.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * Delete model
	 * 
	 * @param model
	 * @return model by which this method was called as list
	 * @throws Exception
	 */
	private List<Models> delete(Models model) throws Exception {
		try {
			modelRepository.delete(model);
			return Arrays.asList(model);
		} catch (Exception e) {
			log.error("Exception deleting Models [{}]", model.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}
}
