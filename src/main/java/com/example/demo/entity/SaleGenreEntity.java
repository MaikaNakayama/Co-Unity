package com.example.demo.entity;

import java.time.LocalDate;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * アンケート情報を保持するエンティティクラス
 * @author 道田
 * @version 1.0
 */
@Entity
@Table(name = "sale_data")
@Data
public class SaleGenreEntity implements Persistable<GenreEntity> {

	@Id
	@Column(name = "discount_cd")
	private Integer discountCd;

	@Column(name = "genre_cd")
	private Integer genreCd;

	@Column(name = "genre_name")
	private String genreName;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "sale_rate")
	private Integer saleRate;

	@Column(name = "rec")
	private String rec;

	@Override
	public GenreEntity getId() {
		return null;
	}

	@Override
	public boolean isNew() {
		return true;
	}
}
