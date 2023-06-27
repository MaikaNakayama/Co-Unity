package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * ログイン情報を照合するためのフォームクラス
 * @author 平松
 *
 */
@Data
public class LoginForm {
	@NotNull(message = "従業員番号が入力されていません。")
	private Integer empId;

	@NotBlank(message = "秘密の質問が入力されていません。")
	private String question;
}
