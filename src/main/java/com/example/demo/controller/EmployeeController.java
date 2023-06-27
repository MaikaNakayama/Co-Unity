package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.LoginEntity;
import com.example.demo.entity.SaleEntity;
import com.example.demo.form.LoginForm;
import com.example.demo.form.SaleForm;
import com.example.demo.repository.SaleRepository;
import com.example.demo.service.LoginService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class EmployeeController {

	private final LoginService loginService;

	private final SaleRepository saleRepository;//

	/**
	 * 従業員番号と秘密の質問をフォームから取得して遷移先を決定する
	 * @param loginform 従業員番号、秘密の質問の回答
	 * @param result エラーメッセージ
	 * @param mv 遷移先の値
	 * @return menu.html,login.html
	 */

	@PostMapping("/menu")
	public ModelAndView login(@ModelAttribute @Validated LoginForm loginForm, BindingResult result, ModelAndView mv) {

		if (result.hasErrors()) {
			//エラーがある場合login.htmlに遷移する。
			mv.setViewName("login");
			return mv;
		}

		//サービスクラスのメソッドを呼び出してエラーチェックを行う
		LoginEntity loginEntity = new LoginEntity();
		loginEntity.setEmpId(Integer.parseInt(loginForm.getEmpId()));
		loginEntity.setQuestion(loginForm.getQuestion());
		loginService.isValidEmpId(loginEntity, result);
		loginService.validQuestion(loginForm, result);

		if (result.hasErrors()) {
			//エラーがある場合login.htmlに遷移する。
			mv.addObject("loginForm", loginForm);
			mv.setViewName("login");
			return mv;

		} else {
			//エラーがない場合menu.htmlに遷移する。
			mv.setViewName("menu");
			return mv;
		}
	}

	/**
	 * メニュー画面からセール情報を挿入する画面に遷移する
	 * @return .html
	 */

	@GetMapping("/info")
	public ModelAndView info(SaleForm saleForm, ModelAndView mv) {
		mv.addObject("saleForm", saleForm);
		mv.setViewName("saleinput");
		return mv;
	}

	@PostMapping("/salecomplete")
	public ModelAndView insert(@ModelAttribute @Validated SaleForm saleForm, BindingResult result, ModelAndView mv) {
		//サービスクラスのメソッドを呼び出してエラーチェックを行う
		//insertService.valid(saleForm, result);

		if (!result.hasErrors()) {
			//エラーがない場合、DBに情報を登録し、次画面に遷移する

			SaleEntity saleEntity = new SaleEntity();
			saleEntity.setGenreCd(saleForm.getGenreCd());
			saleEntity.setDate(saleForm.getDate());
			saleEntity.setSaleRate(saleForm.getSaleRate());
			saleEntity.setRec(saleForm.getRec());
			saleRepository.saveAndFlush(saleEntity);
			mv.setViewName("salecomplete");
			return mv;

		} else {
			//エラーがある場合ページにとどまる
			mv.setViewName("saleinput");
			return mv;
		}
	}
}
