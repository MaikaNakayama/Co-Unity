package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.form.SaleForm;

@Service
public class SaleService {
	
	public boolean validRec(SaleForm saleForm, BindingResult result) {
		boolean ret = true;
		String inputRec = saleForm.getRec();

		if (inputRec != null && !"".equals(inputRec)) {
			if (inputRec.strip().length() == 0) {
				//inputQuestionが全角スペースだった場合

				result.addError(new FieldError(
						result.getObjectName(),
						"rec",
						"全角スペースです"));
			}
		}
		return ret;
	}
}
