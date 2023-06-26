package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
/**
 * セール情報を格納するためのフォームクラス
 * @author 平松
 *
 */

@Data
public class SaleForm {
	@NotNull(message = "ジャンルを選択してください。")
	private Integer genreCd;

	@NotNull(message = "日付を入力してください。")
	@Future(message = "明日以降の日付を入力してください。")
	private LocalDate date;

	@NotBlank(message = "割引率を入力してください。")
	@Size(min = 1, max = 90, message = "割引率は1~90までの数字を入力してください。")
	private Integer saleRate;
	
	@NotBlank(message = "謳い文句を入力してください。")
	private String rec;

}
