package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SaleGenreEntity;
import com.example.demo.repository.GenreRepository;

/*
 * サイネージにDBのセールデータを返すコントローラークラス
 * @author 平松
 * @version 1.0
 */
@RestController
public class AdvController {
		SaleGenreEntity saleGenreEntity;
		GenreRepository genreRepository;
		
	@GetMapping("")
	public List<SaleGenreEntity> Adv() {
		
		List<SaleGenreEntity> advertise=genreRepository.findAll();
		/*advertise.setGenreName("genreName", saleGenreEntity);
		advertise.setDate("date", saleGenreEntity);
		advertise.setSaleRate("saleRate",saleGenreEntity);
		advertise.setRec("rec",saleGenreEntity);*/
		return advertise;
		
	}
	
}
