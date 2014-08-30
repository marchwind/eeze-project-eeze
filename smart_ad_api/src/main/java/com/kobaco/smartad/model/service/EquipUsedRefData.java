package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAEquipementProcessHistory;

public class EquipUsedRefData implements ValueObject{
	
	private SAEquipementProcessHistory dm;
	
	public EquipUsedRefData(SAEquipementProcessHistory  sa) {
		this.dm = sa;
	}

	public String getEquipNo() {
		return this.dm.getEQPM_NO();
	}


	public Date getUsedDate() {
		return this.dm.getGTH_DTT();
	}


	public String getUsedSoftware() {
		return this.dm.getHIST_CNTT();
	}

	
}
