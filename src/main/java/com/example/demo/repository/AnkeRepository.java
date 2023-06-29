package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.AnkeEntity;

/**
 * 入力された情報をDB上にのせるためのインターフェース
 * @author 五十嵐
 * @version 1.0
 */
public interface AnkeRepository extends JpaRepository<AnkeEntity, Integer>{

}
