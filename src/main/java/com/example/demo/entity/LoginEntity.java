package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * ログイン情報を保持するためのエンティティクラス
 * @author 中山
 * @version 1.0
 */

@Entity
@Table (name = "t_employee")
@Data
public class LoginEntity {
	
	@Column (name = "emp_name")
	private String empName;
	
	@Id
	@Column (name = "emp_id")
	private Integer empId;
	
	@Column (name = "question")
	private String question;
}
