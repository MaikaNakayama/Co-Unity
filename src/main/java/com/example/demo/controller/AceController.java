package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.AnkeEntity;
import com.example.demo.entity.SaleGenreEntity;
import com.example.demo.form.AnkeForm;
import com.example.demo.form.LoginForm;
import com.example.demo.repository.AnkeRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.AnkeService;
import com.example.demo.service.LoginService;

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
	private final GenreRepository genreRepository;
	
	//ace.htmlからanke.htmlに遷移させる
	
	@GetMapping("/anke")
	public ModelAndView sample(AnkeForm ankeForm, ModelAndView mv) {
		mv.addObject("ankeForm", new AnkeForm());
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
		ankeForm.setShopName(ankeForm.getShopName().replace(",",""));
		
		
		ankeService.validName(ankeForm, result);
		ankeService.validReason(ankeForm, result);
		ankeService.validEmp(ankeForm, result);
		if(!result.hasErrors()) {
			AnkeEntity ankeEntity = new AnkeEntity();
			ankeEntity.setShopName(ankeForm.getShopName());
			ankeEntity.setEvaCd(ankeForm.getEvaCd());
			ankeEntity.setCevaCd(ankeForm.getCevaCd());
			ankeEntity.setSevaCd(ankeForm.getSevaCd());
			ankeEntity.setReason(ankeForm.getReason());
			ankeRepository.saveAndFlush(ankeEntity);
			mv.setViewName("ankecomplete");
			return mv;
		}else {
			mv.setViewName("anke");
			return mv;
		}
		
	}

	/**
	 * sale_dataからセール日と割引率を、t_genreから商品ジャンルをもらってace.htmlからsale.htmlに遷移させる
	 * @param mv
	 * @param categoryCd
	 * @return mv
	 */
			@GetMapping("/sale")
			public ModelAndView bargain(@ModelAttribute ModelAndView mv) {
				List<SaleGenreEntity> saleList = genreRepository.findAll();
				mv.addObject("saleList",saleList);
				mv.setViewName("sale");
				return mv;
			}
			
			@AllArgsConstructor
			@Controller
			public class EmployeeController {
				private final LoginService loginService;
//				private final SaleRepository saleRepository;//
				/**
				 * 従業員番号と秘密の質問をフォームから取得して遷移先を決定する
				 * @param loginform 従業員番号、秘密の質問の回答
				 * @param result エラーメッセージ
				 * @param mv 遷移先の値
				 * @return menu.html,login.html
				 */

				@PostMapping("/menu")
				public ModelAndView login(@ModelAttribute @Validated LoginForm loginform, BindingResult result,ModelAndView mv){
					//サービスクラスのメソッドを呼び出してエラーチェックを行う
					loginService.isValidEmpId(loginform, result);
					
					if(!result.hasErrors()) {
						//エラーがない場合.htmlに遷移する。
						mv.setViewName("menu");
						return mv;
						
					}else {
						//エラーがある場合ページにとどまる
						mv.setViewName("login");
						return mv;
					}
				}

				
				/**
				 * メニュー画面からセール情報を挿入する画面に遷移する
				 * @return .html
				 */

				@PostMapping("/info")
				public String info() {
					return "saleinput";
				}
			}
				
}
