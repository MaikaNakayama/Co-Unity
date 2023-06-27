package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * ログイン情報を照合するためのフォームクラス
 * @author 平松
 *
 */
@Data
public class LoginForm {
	@NotNull(message = "従業員番号が入力されていません。")
	@Pattern(regexp = "^[0-9０－９]{4,10}$", message = "従業員番号は桁数に注意して、1~10の数字で入力してください。")
	private String empId;

	@NotBlank(message = "秘密の質問が入力されていません。")
	private String question;

}
