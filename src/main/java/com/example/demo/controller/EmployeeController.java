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
import com.example.demo.service.SaleService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

/**
 * 従業員ページログインや遷移を行うコントローラークラス
 * @author 青木
 * @version 1.0
 */
@AllArgsConstructor
@Controller
public class EmployeeController {

	private final HttpSession session;
	private final LoginService loginService;
	private final SaleRepository saleRepository;
	private final SaleService saleService;

	/**
	 * 従業員番号と秘密の質問をフォームから取得して遷移先を決定する
	 * @param loginform 従業員番号、秘密の質問の回答
	 * @param result エラーメッセージ
	 * @param mv 遷移先の値
	 * @return login.html、/admin/menu
	 */
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute @Validated LoginForm loginForm, BindingResult result, ModelAndView mv) {

		if (result.hasErrors()) {
			//バリデーションにてエラーがある場合の処理
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
			//エラーがない場合admin/menuに遷移する。
			session.setAttribute("key", loginForm);
			mv.setViewName("redirect:/admin/menu");
			return mv;
		}
	}

	/**
	 * セッションのチェックを行いつつメニュー画面に遷移を行う
	 * @return menu.html
	 */
	@GetMapping("/admin/menu")
	public String someMethod() {

		//セッションに値があればmenu.htmlに遷移する
		return "menu";
	}

	/**
	 * メニュー画面からセール情報を入力する画面に遷移する
	 * @return .html
	 */
	@GetMapping("/admin/info")
	public ModelAndView info(SaleForm saleForm, ModelAndView mv) {

		//セッションに値があればsaleinput.htmlに遷移する
		mv.addObject("saleForm", saleForm);
		mv.setViewName("saleinput");
		return mv;
	}

	/**
	 * セール情報を更新するための操作を行い画面を遷移する
	 * @param saleForm ユーザーが入力したセール情報
	 * @param result エラーメッセージ
	 * @param mv 遷移する値
	 * @return
	 */
	@PostMapping("/admin/salecomplete")
	public ModelAndView insert(@ModelAttribute @Validated SaleForm saleForm, BindingResult result, ModelAndView mv) {

		if (result.hasErrors()) {
			//バリデーションのエラーをチェックしエラーがある場合saleinput.htmlに遷移する
			mv.setViewName("saleinput");
			return mv;
		}

		//サービスクラスのメソッドを呼び出してエラーチェックを行う
		saleService.validRec(saleForm, result);

		if (result.hasErrors()) {
			//サービスクラスの処理でエラーがある場合saleinput.htmlに遷移する
			mv.setViewName("saleinput");
			return mv;

		} else {
			//エラーがない場合、DBに情報を登録
			SaleEntity saleEntity = new SaleEntity();
			saleEntity.setGenreCd(saleForm.getGenreCd());
			saleEntity.setDate(saleForm.getDate());
			saleEntity.setSaleRate(saleForm.getSaleRate());
			saleEntity.setRec(saleForm.getRec());
			saleRepository.saveAndFlush(saleEntity);

			//salecomplete.htmlに画面に遷移する
			mv.setViewName("salecomplete");
			return mv;
		}
	}

	/**
	 * セッションのチェックを行い、PowerBIのサイトに遷移する
	 * @return powerBI.html
	 */
	@GetMapping("/admin/power")
	public String power() {
		//セッションがあればpowerBI.htnlに遷移する
		return "powerBI";
	}

	/**
	 * セッションのチェックがエラーだった場合に、newlogin.htmlのサイトに遷移する
	 * @return
	 */
	@GetMapping("/err")
	public String error() {
		//各画面遷移にてセッションがなかった場合、newlogin.htmlに遷移する
		return "newlogin";
	}
}
