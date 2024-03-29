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
import com.example.demo.repository.AnkeRepository;
import com.example.demo.repository.GenreRepository;
import com.example.demo.service.AnkeService;

import lombok.AllArgsConstructor;

/**
 * 顧客側の画面遷移を決定するコントローラークラス
 * @author 中山、青木、平松
 * @version 1.0
 */
@AllArgsConstructor
@Controller
public class AceController {

	private final AnkeRepository ankeRepository;
	private final AnkeService ankeService;
	private final GenreRepository genreRepository;

	/**
	 * ace.htmlからanke.htmlに遷移させる
	 * @param ankeForm　アンケートフォーム
	 * @param mv　アンケートフォームへの遷移
	 * @return mv(/anke)　アンケートフォームへの遷移
	 */

	@GetMapping("/anke")
	public ModelAndView sample(AnkeForm ankeForm, ModelAndView mv) {
		mv.addObject("ankeForm", new AnkeForm());
		mv.setViewName("anke");

		return mv;
	}

	/**
	 * anke.htmlからankeComplete.htmlに遷移させる。
	 * @param ankeForm アンケートフォームに入力された情報を保持するフォームクラス
	 * @param result　エラーメッセージ
	 * @param mv 遷移先の値
	 * @return mv　遷移先の値(/ankeComplete)
	 */

	//エラーがある場合はanke.htmlにエラーを返す。ない場合はankeComplete.htmlに遷移。
	@PostMapping("/ankeComplete")
	public ModelAndView ankeInput(@ModelAttribute @Validated AnkeForm ankeForm, BindingResult result, ModelAndView mv) {
		ankeForm.setShopName(ankeForm.getShopName().replace(",", ""));
		if (result.hasErrors()) {
			//バリデーションにてエラーがある場合の処理
			String genre = ankeForm.getGenre();
			String[] shopList = null;
			if (genre.equals("goods")) {
				//anke.htmlでジャンルが選ばれた場合にジャンルのリストを返す。
				shopList = new String[] { "OWNDAYS", "ダイソー" };
			} else if (genre.equals("gourmet")) {
				//anke.htmlでグルメが選ばれた場合にグルメのリストを返す。
				shopList = new String[] { "コメダ珈琲", "イタリアンダイニングDONA", "リンガーハット", "マクドナルド" };
			} else if (genre.equals("service")) {
				//anke.htmlでサービスが選ばれた場合にサービスのリストを返す。
				shopList = new String[] { "ママショップ加納クリーニング", "マジックミシン", "ラフィネ", "イオンカルチャークラブ" };
			}
			mv.addObject("shopList", shopList);
			mv.setViewName("anke");
			return mv;
		}
		
		ankeService.validName(ankeForm, result);
		ankeService.validReason(ankeForm, result);
		ankeService.validEmp(ankeForm, result);
		if (!result.hasErrors()) {
			AnkeEntity ankeEntity = new AnkeEntity();
			ankeEntity.setShopName(ankeForm.getShopName());
			ankeEntity.setEvaCd(ankeForm.getEvaCd());
			ankeEntity.setCevaCd(ankeForm.getCevaCd());
			ankeEntity.setSevaCd(ankeForm.getSevaCd());
			ankeEntity.setReason(ankeForm.getReason());
			ankeRepository.saveAndFlush(ankeEntity);
			mv.setViewName("ankecomplete");
			return mv;
		} else {
			String genre = ankeForm.getGenre();
			String[] shopList = null;
			if (genre.equals("goods")) {
				//anke.htmlでジャンルが選ばれた場合にジャンルのリストを返す。
				shopList = new String[] { "OWNDAYS", "ダイソー" };
			} else if (genre.equals("gourmet")) {
				//anke.htmlでグルメが選ばれた場合にグルメのリストを返す。
				shopList = new String[] { "コメダ珈琲", "イタリアンダイニングDONA", "リンガーハット", "マクドナルド" };
			} else if (genre.equals("service")) {
				//anke.htmlでサービスが選ばれた場合にサービスのリストを返す。
				shopList = new String[] { "ママショップ加納クリーニング", "マジックミシン", "ラフィネ", "イオンカルチャークラブ" };
			}
			mv.addObject("shopList", shopList);
			mv.setViewName("anke");
			return mv;
		}

	}

	/**
	 * sale_dataからセール日と割引率を、t_genreから商品ジャンルをもらってace.htmlからsale.htmlに遷移させる
	 * @param mv　遷移先の値
	 * @return mv　遷移先の値(/sale)
	 */
	@GetMapping("/sale")
	public ModelAndView bargain(@ModelAttribute ModelAndView mv) {
		List<SaleGenreEntity> saleList = genreRepository.findAll();
		mv.addObject("saleList", saleList);
		mv.setViewName("sale");
		return mv;
	}

}