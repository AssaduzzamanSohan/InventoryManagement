package com.inventoryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventoryManagement.entity.Brand;

/**
 * Spring data repository for {@link Brand} model.
 * 
 * @author Assaduzzaman Sohan
 */
@Repository("brandRepository")
public interface BrandRepository extends JpaRepository<Brand, Integer> {

	List<Brand> findById(int id);

	List<Brand> findByBrandName(String name);

}
