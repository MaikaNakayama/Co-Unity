package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.SaleGenreEntity;

/**
 * 入力された情報をDB上にのせるためのインターフェース
 * @author 平松
 * @version 1.0
 */

public interface AdvRepository extends JpaRepository<SaleGenreEntity, Integer> {
	@Query(value = "SELECT discount_cd, genre_cd, genre_name, date, sale_rate,rec FROM sale_data JOIN t_genre using(genre_cd) WHERE date>current_date ORDER BY Date;", nativeQuery = true)
	List<SaleGenreEntity> findAll();
}
