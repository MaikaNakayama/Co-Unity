package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.form.AnkeForm;

/**
 * 店舗名の入力判定、理由の判定を行うServiceクラスです。
 * @author 青木
 * @version 1.0
 */
@Service
public class AnkeService {

	/**
	 * 店舗名が入力された時に空欄ではないが入力値がない場合。
	 * @param ankeForm ユーザが入力した値
	 * @param result エラーメッセージ
	 * @return 変数ret boolean型で判断するための戻り値
	 */
	public boolean validName(AnkeForm ankeForm, BindingResult result) {

		boolean ret = true;
		String inputShopName = ankeForm.getShopName();

		if (inputShopName != null && !"".equals(inputShopName)) {
			if (inputShopName.strip().length() == 0) {
				//inputShopNameが全角スペースだった場合

				result.addError(new FieldError(
						result.getObjectName(),
						"shopName",
						"全角スペースです。"));
			}
		}
		return ret;
	}

	/**
	 * 評価理由が空欄ではないが、入力値がない場合。
	 * @param ankeForm ユーザーが入力した値。
	 * @param result エラーメッセージ
	 * @return 変数ret boolean型で判断するための戻り値
	 */
	public boolean validReason(AnkeForm ankeForm, BindingResult result) {
		boolean ret = true;
		String inputReason = ankeForm.getReason();

		if (inputReason != null && !"".equals(inputReason)) {
			if (inputReason.strip().length() == 0) {
				//inputReasonが全角スペースだった場合

				result.addError(new FieldError(
						result.getObjectName(),
						"reason",
						"全角スペースです"));
			}
		}
		return ret;
	}

	/**
	 * 店舗名が入力されていないときの処理
	 * @param ankeForm ユーザーが入力した情報
	 * @param result エラーメッセージ
	 * @return 変数ret boolean型で判断するための戻り値
	 */
	public boolean validEmp(AnkeForm ankeForm, BindingResult result) {
		boolean ret = true;
		String inputShopName = ankeForm.getShopName();

		if ("".equals(inputShopName)) {
			//inputShopNameが未入力だった場合

			result.addError(new FieldError(
					result.getObjectName(),
					"shopName",
					"選択されていません"));
		}
		return ret;
	}
}