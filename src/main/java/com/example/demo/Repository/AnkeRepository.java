package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.AnkeEntity;
/**
 * 入力された情報をDB上にのせるためのインターフェース
 * @author 五十嵐
 * @version 	1.0
 */
public interface AnkeRepository extends JpaRepository<AnkeEntity, Integer>{

}
