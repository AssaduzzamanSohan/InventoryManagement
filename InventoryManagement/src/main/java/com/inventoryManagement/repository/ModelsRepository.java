package com.inventoryManagement.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventoryManagement.entity.Brand;
import com.inventoryManagement.entity.Models;

/**
 * Spring data repository for {@link Brand} model.
 * 
 * @author Assaduzzaman Sohan
 */
@Repository("modelsRepository")
public interface ModelsRepository extends JpaRepository<Models, Long> {
	List<Models> findById(int id);

	List<Models> findByModelName(String name);

	List<Models> findByBrandId(int brandId, Sort sort);
}
