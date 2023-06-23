package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SaleEntity;

/**
 * 入力された情報をDB上にのせるためのインターフェース
 * @author 道田
 * @version 	1.0
 */

public interface SaleRepository extends JpaRepository<SaleEntity, Integer> {

}
