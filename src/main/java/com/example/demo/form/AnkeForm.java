package com.example.demo.form;

import com.example.demo.entity.AnkeEntity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * アンケート情報を入力用のFormクラス
 * @author 道田
 * @version 1.0
 */
@Data
public class AnkeForm {
	
	
	@NotBlank(message = "店舗名をご記入ください")
	private String shopName;
	
	
	@NotNull(message = "評価点数をご記入ください")
	@Min(value = 1, message = "1～5の中から選択してください")
	@Max(value = 5, message = "1～5の中から選択してください")
	private Integer evaCd;
	
	@NotNull(message = "評価点数をご記入ください")
	@Min(value = 1, message = "1～5の中から選択してください")
	@Max(value = 5, message = "1～5の中から選択してください")
	private Integer cevaCd;
	
	@NotNull(message = "評価点数をご記入ください")
	@Min(value = 1, message = "1～5の中から選択してください")
	@Max(value = 5, message = "1～5の中から選択してください")
	private Integer sevaCd;
	
	
	@NotBlank(message = "理由をご記入ください")
	private String reason;
	
	private String genre;
	
	/**
	 * AnkeEntityに値を格納
	 * @return AnkeEntity
	 */
	public AnkeEntity getEntity() {
		AnkeEntity ankeEntity = new AnkeEntity();
		ankeEntity.setShopName(shopName);
		ankeEntity.setEvaCd(evaCd);
		ankeEntity.setEvaCd(cevaCd);
		ankeEntity.setEvaCd(sevaCd);
		ankeEntity.setReason(reason);
		return ankeEntity;
		
	}
}
