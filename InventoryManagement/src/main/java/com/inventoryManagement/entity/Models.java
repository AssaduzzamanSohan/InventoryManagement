package com.inventoryManagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data structure to define information of a Model.
 * 
 * @author Assaduzzaman Sohan
 */
@Entity
@Table(name = "T_MODELS", indexes = {
		@Index(name = "idx_model_name_brand", columnList = "name, brand_id", unique = true) })
@NoArgsConstructor
@Data
public class Models {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	@Column(name = "brand_id", length = 11)
	private int brandId;

	@Column(name = "name", length = 100)
	private String modelName;

	@Column(name = "entry_date")
	private LocalDateTime entryDate;

	/* 
	* keeping brand full information to get all data of brand for this model
	* just putting anotation, data will be autometicaly fached by hibernet 
	*/
	@OneToOne
	@JoinColumn(name = "brand_id", insertable = false, updatable = false)
	private Brand brand;

	// keeping all brand list to show in dropdown menu
	@Transient
	List<Brand> brandList;

	// while inserting current datetime will be setted, no need to set from anywhere else
	@PrePersist
	public void onInsert() {
		this.entryDate = LocalDateTime.now();
	}
}
