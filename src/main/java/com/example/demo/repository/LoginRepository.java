package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 従業員用ログイン画面に入力された情報をDBと照合するためのインターフェース
 * @author 中山
 * @version 	1.0
 */


public interface LoginRepository extends JpaRepository<LoginRepository, Integer> {

}
