package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SaleGenreEntity;
import com.example.demo.repository.GenreRepository;

import lombok.AllArgsConstructor;

/*
 * サイネージにDBのセールデータを返すコントローラークラス
 * @author 平松
 * @version 1.0
 */
@RestController
@AllArgsConstructor
public class AdvController {
	private final GenreRepository genreRepository;

	@GetMapping("/adv")
	public List<SaleGenreEntity> Adv() {
		//DBから情報を取得し、Listの中へ入れる。
		List<SaleGenreEntity> advertise = genreRepository.findAll();
		return advertise;
	}
}