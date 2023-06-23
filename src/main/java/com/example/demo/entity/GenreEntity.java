package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "t_genre")
@Data
public class GenreEntity {

	@Id
	@Column(name = "genre_cd")
	private Integer genreCd;
	
	@Column(name = "genre_name")
	private String genreName;
}
