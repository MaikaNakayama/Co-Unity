package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.AnkeEntity;
import com.example.demo.form.AnkeForm;
import com.example.demo.repository.AnkeRepository;
import com.example.demo.service.AnkeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class AceController {
	
	private final AnkeRepository ankeRepository;
	private final AnkeService ankeService;
	
	//ace.htmlからanke.htmlに遷移させる
	
	@PostMapping("/anke")
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
			mv.setViewName("ankeComplete");
			return mv;
		}else {
			mv.setViewName("anke");
			return mv;
		}
		
	}
}