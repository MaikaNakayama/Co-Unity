package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LoginEntity;

/**
 * 従業員用ログイン画面に入力された情報をDBと照合するためのインターフェース
 * @author 中山
 * @version 	1.0
 */


public interface LoginRepository extends JpaRepository<LoginRepository, Integer> {

	List<LoginEntity> findByEmpId(@Param("empId") Integer empId);
}
