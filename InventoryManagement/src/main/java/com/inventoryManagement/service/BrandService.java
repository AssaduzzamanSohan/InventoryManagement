package com.inventoryManagement.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inventoryManagement.constants.ActionType;
import com.inventoryManagement.entity.Brand;
import com.inventoryManagement.repository.BrandRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BrandService {
	@Autowired
	private BrandRepository brandRepository;

	/**
	 * Developer will just call this method with information in brand object and
	 * what to do in actionType
	 * 
	 * @param brand
	 * @param actionType
	 * @return List<Brand>
	 * @throws Exception
	 */
	public List<Brand> handleSingleRequest(Brand brand, String actionType) throws Exception {
		if (actionType.matches(ActionType.SELECT.toString())) {
			return select(brand);
		} else if (actionType.matches(ActionType.SELECT_ALL.toString())) {
			return selectAll();
		} else if (actionType.matches(ActionType.NEW.toString()) || actionType.matches(ActionType.SAVE.toString())) {
			return save(brand);
		} else if (actionType.matches(ActionType.UPDATE.toString())) {
			return update(brand);
		} else if (actionType.matches(ActionType.DELETE.toString())) {
			return delete(brand);
		} else {
			throw new Exception("Unknown action " + actionType);
		}
	}

	/**
	 * Select's data by brand id
	 * 
	 * @param brand
	 * @return List<Brand>
	 * @throws Exception
	 */
	private List<Brand> select(Brand brand) throws Exception {
		try {
			return brandRepository.findById(brand.getId());
		} catch (Exception e) {
			log.error("Exception selecting Brand [{}]", brand.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * Select all data from brand table with entryDate desc
	 * 
	 * @return List<Brand>
	 * @throws Exception
	 */
	private List<Brand> selectAll() throws Exception {
		try {
			return brandRepository.findAll(new Sort(Sort.Direction.DESC, "entryDate"));
		} catch (Exception e) {
			log.error("Exception selecting all Brand [{}]", e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * Save brand info, and update if brand id found in object
	 * 
	 * @param brand
	 * @return List<Brand>
	 * @throws Exception
	 */
	private List<Brand> save(Brand brand) throws Exception {
		try {
			return Arrays.asList(brandRepository.save(brand));
		} catch (Exception e) {
			log.error("Exception saving Brand [{}]", brand.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * update brand, call it when you are sure
	 * 
	 * @param brand
	 * @return List<Brand>
	 * @throws Exception
	 */
	private List<Brand> update(Brand brand) throws Exception {
		try {
			if (brand.getId() == 0) {
				throw new Exception("Brand id not found in Object, First Find then try to update");
			}

			return Arrays.asList(brandRepository.save(getUpdateableBrand(brand)));
		} catch (Exception e) {
			log.error("Exception updating Brand [{}]", brand.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}

	/**
	 * Merge DB and local data, stays DB data if local data not found
	 * 
	 * @param brand
	 * @return
	 * @throws Exception
	 */
	private Brand getUpdateableBrand(Brand brand) throws Exception {
		List<Brand> brandDbList = select(brand);

		Brand brandDb = brandDbList.get(0);
		if (brand.getBrandName() != null && !brand.getBrandName().isEmpty()) {
			brandDb.setBrandName(brand.getBrandName());
		}
		return brandDb;
	}

	/**
	 * Delete brand
	 * 
	 * @param brand
	 * @return brand by which this method was called as list
	 * @throws Exception
	 */
	private List<Brand> delete(Brand brand) throws Exception {
		try {
			brandRepository.delete(brand);
			return Arrays.asList(brand);
		} catch (Exception e) {
			log.error("Exception deleting Brand [{}]", brand.toString(), e);
			throw new Exception(e.getLocalizedMessage());
		}
	}
}
