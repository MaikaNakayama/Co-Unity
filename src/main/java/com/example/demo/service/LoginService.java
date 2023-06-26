package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.entity.LoginEntity;
import com.example.demo.form.LoginForm;
import com.example.demo.repository.LoginRepository;

import lombok.AllArgsConstructor;

/**
 * 従業員ID、秘密の質問の入力判定を行うServiceクラスです。
 * @author 道田
 * @version 1.0
 */

@AllArgsConstructor
@Service
public class LoginService {
	private final LoginRepository loginRepository;

	/**
	 * 従業員idが入力された時に空欄ではないが入力値が場合。
	 * @param LoginForm ユーザが入力した値
	 * @param result boolean型で判断するための戻り値
	 * @return
	 */

	public boolean isValidEmpId(LoginForm loginForm, BindingResult result) {
		boolean ret = true;
		List<LoginEntity> optionalLoginForm = loginRepository.findByEmpIdANDQuestion(loginForm.getEmpId(), loginForm.getQuestion());

		//EmpIdとQuestionが空欄ではないが入力値が存在しない場合

		if (optionalLoginForm.isEmpty()) {
			result.addError(new FieldError(
					result.getObjectName(),
					"login",
					"ログインできませんでした。もう一度入力してください。"));
			return false;

		}
		return ret;
	}

	
	/**
	 * 秘密の質問が空欄ではないが、入力値がない場合。
	 * @param loginForm ユーザーが入力した値。
	 * @param result boolean型で判断する為の戻り値。
	 * @return
	 */
	public boolean validQuestion(LoginForm loginForm, BindingResult result) {
		boolean ret = true;
		String inputQuestion = loginForm.getQuestion();

		if (inputQuestion != null && !"".equals(inputQuestion)) {
			if (inputQuestion.strip().length() == 0) {
				//inputQuestionが全角スペースだった場合

				result.addError(new FieldError(
						result.getObjectName(),
						"question",
						"全角スペースです"));
			}
		}
		return ret;
	}

}
