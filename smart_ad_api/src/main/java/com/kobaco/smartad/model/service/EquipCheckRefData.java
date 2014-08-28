package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAEquipementCheck;

public class EquipCheckRefData implements ValueObject {
	
	private SAEquipementCheck dm;
	
	public EquipCheckRefData(SAEquipementCheck model) {
		this.dm = model;
	}

	public String getEquipNo() {
		return this.dm.getENTP_NO();
	}
	public Date getCheckDate() {
		return this.dm.getCHK_DTT();
	}
	public String getChecker() {
		return this.dm.getCHKR();
	}
	public String getCheckContent() {
		return this.dm.getCHK_CNTT();
	}
	
}
