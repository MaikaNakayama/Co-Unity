package com.example.demo.entity;

import java.time.LocalDate;

/**
 * セール情報を格納するエンティティクラス
 * @author 五十嵐
 *　@version 1.0
 */
public class Adv {

	private Integer genreCd;

	private LocalDate date;

	private Integer saleRate;

	private String rec;

	public int getGenreCd() {
		return genreCd;
	}

	public void setGenreCd(int genreCd) {
		this.genreCd = genreCd;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setdate(LocalDate date) {
		this.date = date;
	}

	public int getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(int saleRate) {
		this.saleRate = saleRate;
	}

	public String getRec() {
		return rec;
	}

	public void setRec(String rec) {
		this.rec = rec;
	}

}
