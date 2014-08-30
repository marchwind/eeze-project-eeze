package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAFacilityVisitor implements Entity {

	private String VST_NO;
	private String FCLT_VSTR_NO;
	private String VSTR_NM;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private int VSTR_CNT;
	
	public String getVST_NO() {
		return VST_NO;
	}

	public void setVST_NO(String vST_NO) {
		VST_NO = vST_NO;
	}

	public String getFCLT_VSTR_NO() {
		return FCLT_VSTR_NO;
	}

	public void setFCLT_VSTR_NO(String fCLT_VSTR_NO) {
		FCLT_VSTR_NO = fCLT_VSTR_NO;
	}

	public String getVSTR_NM() {
		return VSTR_NM;
	}

	public void setVSTR_NM(String vSTR_NM) {
		VSTR_NM = vSTR_NM;
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

	public int getVSTR_CNT() {
		return VSTR_CNT;
	}

	public void setVSTR_CNT(int vSTR_CNT) {
		VSTR_CNT = vSTR_CNT;
	}

	@Override
	public String getEntityName() {
		return "facilityVisitor";
	}

}
