package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SAUserEmailCertification implements Bean, Entity{
	
	private String EML_CRTK;
	private String EML_CRTF_YN;
	private Date EML_CRTF_DTT;
	private String USR_NO;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String USR_EML;
	
	
	public String getEML_CRTK() {
		return EML_CRTK;
	}


	public void setEML_CRTK(String eML_CRTK) {
		EML_CRTK = eML_CRTK;
	}


	public String getEML_CRTF_YN() {
		return EML_CRTF_YN;
	}


	public void setEML_CRTF_YN(String eML_CRTF_YN) {
		EML_CRTF_YN = eML_CRTF_YN;
	}


	public Date getEML_CRTF_DTT() {
		return EML_CRTF_DTT;
	}


	public void setEML_CRTF_DTT(Date eML_CRTF_DTT) {
		EML_CRTF_DTT = eML_CRTF_DTT;
	}


	public String getUSR_NO() {
		return USR_NO;
	}


	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
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

	public String getUSR_EML() {
		return USR_EML;
	}


	public void setUSR_EML(String uSR_EML) {
		USR_EML = uSR_EML;
	}

	@Override
	public String getEntityName() {
		return "userEmailCertification";
	}

	

}
