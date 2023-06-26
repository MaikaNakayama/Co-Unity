package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleRepository, Integer> {

	List<SaleEntity> inputSale(@Param("discountCd") Integer discountCd);
	
}