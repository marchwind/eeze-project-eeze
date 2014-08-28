package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SAImage implements Bean, Entity {
	
	private String IMG_MGMT_NO;
	private String FCLT_MGMT_NO;
	private String EQPM_NO;
	private String IMG_NM;
	private String IMG_SPTH;
	private String IMG_URL;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;

	
	public String getIMG_MGMT_NO() {
		return IMG_MGMT_NO;
	}


	public void setIMG_MGMT_NO(String iMG_MGMT_NO) {
		IMG_MGMT_NO = iMG_MGMT_NO;
	}


	public String getFCLT_MGMT_NO() {
		return FCLT_MGMT_NO;
	}


	public void setFCLT_MGMT_NO(String fCLT_MGMT_NO) {
		FCLT_MGMT_NO = fCLT_MGMT_NO;
	}


	public String getEQPM_NO() {
		return EQPM_NO;
	}


	public void setEQPM_NO(String eQPM_NO) {
		EQPM_NO = eQPM_NO;
	}


	public String getIMG_NM() {
		return IMG_NM;
	}


	public void setIMG_NM(String iMG_NM) {
		IMG_NM = iMG_NM;
	}


	public String getIMG_SPTH() {
		return IMG_SPTH;
	}


	public void setIMG_SPTH(String iMG_SPTH) {
		IMG_SPTH = iMG_SPTH;
	}


	public String getIMG_URL() {
		return IMG_URL;
	}


	public void setIMG_URL(String iMG_URL) {
		IMG_URL = iMG_URL;
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
		return "image";
	}

}
