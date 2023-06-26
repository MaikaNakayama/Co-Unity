package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.GenreEntity;
import com.example.demo.entity.SaleGenreEntity;

/**
 * 入力された情報をDB上にのせるためのインターフェース
 * @author 道田
 * @version 	1.0
 */

public interface GenreRepository extends JpaRepository<SaleGenreEntity, GenreEntity> {
	
	@Query(value="SELECT date, sale_rate,genre_name FROM sale_data"
			+ " JOIN t_genre using(genre_cd) WHERE genre_cd=:genre_cd", nativeQuery = true)
	List<SaleGenreEntity> findAllById(@Param("genre_cd") Integer genre_cd);

}

//SaleEntityのところには新しく作ったEntityクラス名を入れる
//新しいEntityクラスにはdate, sale_rate, genre_nameを入れる
//WHERE genre_cd=:genre_cd で表を表示させるために何を入力させたいか

//select date, sale_rate, genre_name from sale_data join t_genre using(genre_cd) where genre_cd=:genre_cd", nativeQuery = true

//SELECT date, sale_rate,genre_name FROM sale_data JOIN t_genre using(genre_cd);