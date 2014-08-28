package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAEquipementConstitute implements Entity {
	private String PART_NM;
	private String PART_SPC;
	private String PART_TP;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String EQPM_CNST_NO;
	private String EQPM_NO;
	private String MFTR;
	
	public String getPART_NM() {
		return PART_NM;
	}


	public void setPART_NM(String pART_NM) {
		PART_NM = pART_NM;
	}


	public String getPART_SPC() {
		return PART_SPC;
	}


	public void setPART_SPC(String pART_SPC) {
		PART_SPC = pART_SPC;
	}


	public String getPART_TP() {
		return PART_TP;
	}


	public void setPART_TP(String pART_TP) {
		PART_TP = pART_TP;
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


	public String getEQPM_CNST_NO() {
		return EQPM_CNST_NO;
	}


	public void setEQPM_CNST_NO(String eQPM_CNST_NO) {
		EQPM_CNST_NO = eQPM_CNST_NO;
	}


	public String getEQPM_NO() {
		return EQPM_NO;
	}


	public void setEQPM_NO(String eQPM_NO) {
		EQPM_NO = eQPM_NO;
	}


	public String getMFTR() {
		return MFTR;
	}


	public void setMFTR(String mFTR) {
		MFTR = mFTR;
	}


	public String getEntityName() {
		return "equipementConstitute";
	}
}
