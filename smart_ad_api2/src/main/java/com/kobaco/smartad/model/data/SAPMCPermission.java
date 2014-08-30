package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SAPMCPermission implements Bean, Entity {
	
	private String MNGR_PRMS_NO;
	private String MNGR_PRMS_NM;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;

	
	public String getMNGR_PRMS_NO() {
		return MNGR_PRMS_NO;
	}


	public void setMNGR_PRMS_NO(String mNGR_PRMS_NO) {
		MNGR_PRMS_NO = mNGR_PRMS_NO;
	}


	public String getMNGR_PRMS_NM() {
		return MNGR_PRMS_NM;
	}


	public void setMNGR_PRMS_NM(String mNGR_PRMS_NM) {
		MNGR_PRMS_NM = mNGR_PRMS_NM;
	}


	public Date getREG_DTT() {
		return REG_DTT;
	}


	public void setREG_DTT(Date rEG_DTT) {
		REG_DTT = rEG_DTT;
	}


	public Date getUPD_DTT() {
		return UPD_DTT;
	}


	public void setUPD_DTT(Date uPD_DTT) {
		UPD_DTT = uPD_DTT;
	}


	public String getREG_ID() {
		return REG_ID;
	}


	public void setREG_ID(String rEG_ID) {
		REG_ID = rEG_ID;
	}


	public String getUPD_ID() {
		return UPD_ID;
	}


	public void setUPD_ID(String uPD_ID) {
		UPD_ID = uPD_ID;
	}


	@Override
	public String getEntityName() {
		return "pmcPermission";
	}

}
