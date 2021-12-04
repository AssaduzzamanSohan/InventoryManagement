package com.inventoryManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.inventoryManagement.entity.Brand;
import com.inventoryManagement.entity.Items;
import com.inventoryManagement.entity.Models;

/**
 * Service class bean configuration class
 * 
 * @author Assaduzzaman Sohan
 *
 */
@Configuration
public class AppConfig {

	@Bean
	@Scope("prototype") // As we want to create several beans with different args
	Brand newBrand(int id) {
		Brand brand = new Brand();
		brand.setId(id);
		return brand;
	}

	@Bean
	@Scope("prototype") // As we want to create several beans with different args
	Models newModels(int id) {
		Models models = new Models();
		models.setId(id);
		return models;
	}

	@Bean
	@Scope("prototype") // As we want to create several beans with different args
	Items newItems(int id) {
		Items items = new Items();
		items.setId(id);
		return items;
	}

}
