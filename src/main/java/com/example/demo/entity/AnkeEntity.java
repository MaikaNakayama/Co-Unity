package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * アンケート情報を保持するエンティティクラス
 * @author 	平松優希
 * @version 	1.0
 */
@Entity
@Table(name = "t_enquete")
@Data
public class AnkeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ans_cd")
	private Integer ansCd;

	@Column(name = "shop_name")
	private String shopName;

	@Column(name = "eva_cd")
	private Integer evaCd;

	@Column(name = "reason")
	private String reason;
}
