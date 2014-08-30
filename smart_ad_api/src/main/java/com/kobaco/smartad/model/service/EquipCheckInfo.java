package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;

public class EquipCheckInfo implements ValueObject {
	
	private String equipNo;
	private Date checkDate;
	private String checker;
	private String checkContent;
	private String equipState; // E04001: 정상, E04002: 고장, E04003: 수리중

	
	public EquipCheckInfo() {
		;
	}

	public String getEquipNo() {
		return equipNo;
	}

	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getEquipState() {
		return equipState;
	}

	public void setEquipState(String equipState) {
		this.equipState = equipState;
	}

	
}
