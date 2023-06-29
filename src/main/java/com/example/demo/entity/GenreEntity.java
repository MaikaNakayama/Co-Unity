package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * アンケート情報を保持するエンティティクラス
 * @author 道田
 * @version 1.0
 */
@Embeddable
@Table(name = "t_genre")
@Data
public class GenreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genre_cd")
	private Integer genreCd;

	@Column(name = "genre_name")
	private String genreName;
}
