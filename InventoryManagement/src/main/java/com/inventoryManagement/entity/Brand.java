package com.inventoryManagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data structure to define information of a Brand.
 * 
 * @author Assaduzzaman Sohan
 */
@Entity
@Table(name = "T_BRAND", indexes = { @Index(name = "idx_brand_name", columnList = "name", unique = true) })
@NoArgsConstructor
@Data
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
	private int id;

	@Column(name = "name", length = 50)
	private String brandName;

	@Column(name = "entry_date")
	private LocalDateTime entryDate;

	// while inserting current datetime will be setted, no need to set from anywhere else
	@PrePersist
	public void onInsert() {
		this.entryDate = LocalDateTime.now();
	}
}
