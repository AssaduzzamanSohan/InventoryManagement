package com.inventoryManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventoryManagement.entity.Brand;
import com.inventoryManagement.entity.Items;

/**
 * Spring data repository for {@link Brand} model.
 * 
 * @author Assaduzzaman Sohan
 */
@Repository("itemsRepository")
public interface ItemsRepository extends JpaRepository<Items, Long> {
	List<Items> findById(int id);

	List<Items> findByItemName(String name);
}
