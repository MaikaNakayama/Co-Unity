package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleEntity, Integer> {

	
}