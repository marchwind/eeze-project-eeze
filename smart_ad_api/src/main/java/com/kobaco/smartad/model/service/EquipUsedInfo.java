package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;

public class EquipUsedInfo implements ValueObject{
	
	private String equipNo;
	private Date usedDate;
	private String usedSoftware;
	
	public EquipUsedInfo() {
		;
	}

	public String getEquipNo() {
		return equipNo;
	}

	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public String getUsedSoftware() {
		return usedSoftware;
	}

	public void setUsedSoftware(String usedSoftware) {
		this.usedSoftware = usedSoftware;
	}
	
}
