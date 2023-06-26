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
 * @author 	平松
 * @version 	1.0
 */
@Entity
@Table(name = "t_enquete")
@Data
public class AnkeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "anke_cd")
	private Integer ankeCd;

	@Column(name = "shop_name")
	private String shopName;

	@Column(name = "eva_cd")
	private Integer evaCd;

	@Column(name = "ceva_cd")
	private Integer cevaCd;

	@Column(name = "seva_cd")
	private Integer sevaCd;

	@Column(name = "reason")
	private String reason;
}
