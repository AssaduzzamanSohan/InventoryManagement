package com.inventoryManagement.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data structure to define information of a Item.
 * 
 * @author Assaduzzaman Sohan
 */
@Entity
@Table(name = "T_ITEMS")
@NoArgsConstructor
@Data
public class Items {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	@Column(name = "brand_id", length = 11)
	private int brandId;

	@Column(name = "model_id", length = 11)
	private int modelId;

	// Default length is 255
	@Column(name = "name")
	private String itemName;

	@Column(name = "entry_date")
	private LocalDateTime entryDate;

	@Transient
	List<Brand> brandList;

	/* 
	* keeping brand full information to get all data of brand for this item
	* just putting anotation, data will be autometicaly fached by hibernet 
	*/
	@OneToOne
	@JoinColumn(name = "brand_id", insertable = false, updatable = false)
	private Brand brand;

	/* 
	* keeping model full information to get all data of model for this item
	* just putting anotation, data will be autometicaly fached by hibernet 
	*/
	@OneToOne
	@JoinColumn(name = "model_id", insertable = false, updatable = false)
	private Models model;

	// while inserting current datetime will be setted, no need to set from anywhere else
	@PrePersist
	public void onInsert() {
		this.entryDate = LocalDateTime.now();
	}
}
