package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * セール情報を保持するエンティティクラス
 * @author 道田
 * @version 1.0
 */
@Entity
@Table(name = "sale_data")
@Data
public class SaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_cd")
	private Integer discountCd;

	@Column(name = "genre_cd")
	private Integer genreCd;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "sale_rate")
	private Integer saleRate;

	@Column(name = "rec")
	private String rec;

}
