package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.form.SaleForm;

import lombok.AllArgsConstructor;

/*:
 * 謳い文句の全角スペースチェックを行うサービスクラス
 * @author 青木
 * @version 1.0
 */
@AllArgsConstructor
@Service
public class SaleService {
	
	/**
	 * 謳い文句が空欄ではないが、入力値がない場合。
	 * @param saleForm ユーザーが入力した値。
	 * @param result エラーメッセージ
	 * @return 変数ret boolean型で判断する為の戻り値。
	 */
	public boolean validRec(SaleForm saleForm, BindingResult result) {
		boolean ret = true;
		String inputRec = saleForm.getRec();

		if (inputRec != null && !"".equals(inputRec)) {
			if (inputRec.strip().length() == 0) {
				//inputRecが全角スペースだった場合

				result.addError(new FieldError(
						result.getObjectName(),
						"rec",
						"全角スペースです"));
			}
			
		}
		return ret;
	}
}

