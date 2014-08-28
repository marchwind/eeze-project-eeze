package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SAEnterprise implements Bean, Entity {

	private String USR_NO;
	private String ENTP_NO;
	private String ENTP_NM;
	private String ENTP_ADDR;
	private String PHN;
	private String BRN;
	private String FAX;
	private String ENTP_EML;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String  REG_ID;
	private String UPD_ID;
	private String BSTP_CD;
	
	
	public String getUSR_NO() {
		return USR_NO;
	}


	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
	}


	public String getENTP_NO() {
		return ENTP_NO;
	}


	public void setENTP_NO(String eNTP_NO) {
		ENTP_NO = eNTP_NO;
	}


	public String getENTP_NM() {
		return ENTP_NM;
	}


	public void setENTP_NM(String eNTP_NM) {
		ENTP_NM = eNTP_NM;
	}


	public String getENTP_ADDR() {
		return ENTP_ADDR;
	}


	public void setENTP_ADDR(String eNTP_ADDR) {
		ENTP_ADDR = eNTP_ADDR;
	}


	public String getPHN() {
		return PHN;
	}


	public void setPHN(String pHN) {
		PHN = pHN;
	}


	public String getBRN() {
		return BRN;
	}


	public void setBRN(String bRN) {
		BRN = bRN;
	}


	public String getFAX() {
		return FAX;
	}


	public void setFAX(String fAX) {
		FAX = fAX;
	}


	public String getENTP_EML() {
		return ENTP_EML;
	}


	public void setENTP_EML(String eNTP_EML) {
		ENTP_EML = eNTP_EML;
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


	public String getBSTP_CD() {
		return BSTP_CD;
	}


	public void setBSTP_CD(String bSTP_CD) {
		BSTP_CD = bSTP_CD;
	}


	@Override
	public String getEntityName() {
		return "enterprise";
	}

}
