package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.AnkeEntity;
import com.example.demo.form.AnkeForm;
import com.example.demo.repository.AnkeRepository;
import com.example.demo.service.AnkeService;

import lombok.AllArgsConstructor;

/**
 * 顧客側の画面遷移を決定するコントローラークラス
 * @author isa
 * @version 1.0
 */
@AllArgsConstructor
@Controller
public class AceController {
	
	private final AnkeRepository ankeRepository;
	private final AnkeService ankeService;
	
	//ace.htmlからanke.htmlに遷移させる
	
	@GetMapping("/anke")
	public ModelAndView sample(@ModelAttribute ModelAndView mv) {
		mv.setViewName("anke");
		return mv;
	}
	

	/**
	 * anke.htmlからankeComplete.htmlに遷移させる。
	 * @param ankeForm
	 * @param result
	 * @return 
	 * @return
	 */
	
	//エラーがある場合はanke.htmlにエラーを返す。ない場合はankeComplete.htmlに遷移。
	@PostMapping("/ankeComplete")
	public ModelAndView ankeInput(@ModelAttribute @Validated AnkeForm ankeForm, BindingResult result, ModelAndView mv) {
		ankeService.validName(ankeForm, result);
		ankeService.validReason(ankeForm, result);
		if(!result.hasErrors()) {
			AnkeEntity ankeEntity = new AnkeEntity();
			ankeEntity.setShopName(ankeForm.getShopName());
			ankeEntity.setEvaCd(ankeForm.getEvaCd());
			ankeEntity.setReason(ankeForm.getReason());
			ankeRepository.saveAndFlush(ankeEntity);
			mv.setViewName("ankecomplete");
			return mv;
		}else {
			mv.setViewName("anke");
			return mv;
		}
		
	}
}
