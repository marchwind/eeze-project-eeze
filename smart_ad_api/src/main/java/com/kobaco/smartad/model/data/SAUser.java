package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.UserInfo;

public class SAUser implements Entity, Bean {
	private String USR_NO;
	private String USR_PWD;
	private String USR_NM;
	private String USR_EML;
	private String CPHN;
	private String PHN;
	private String ENTP_NO;
	private String ENTP_NM;
	private String ENTP_ADDR;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String USR_ID;
	private String PST_CD;
	private String JOB_CD;
	private String USR_STTS_CD;

	public String getUSR_NO() {
		return USR_NO;
	}

	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
	}

	public String getUSR_PWD() {
		return USR_PWD;
	}

	public void setUSR_PWD(String uSR_PWD) {
		USR_PWD = uSR_PWD;
	}

	public String getUSR_NM() {
		return USR_NM;
	}

	public void setUSR_NM(String uSR_NM) {
		USR_NM = uSR_NM;
	}

	public String getUSR_EML() {
		return USR_EML;
	}

	public void setUSR_EML(String uSR_EML) {
		USR_EML = uSR_EML;
	}

	public String getCPHN() {
		return CPHN;
	}

	public void setCPHN(String cPHN) {
		CPHN = cPHN;
	}

	public String getPHN() {
		return PHN;
	}

	public void setPHN(String pHN) {
		PHN = pHN;
	}

	public String getENTP_NO() {
		return ENTP_NO;
	}

	public void setENTP_NO(String eNTP_NO) {
		ENTP_NO = eNTP_NO;
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

	public String getUSR_ID() {
		return USR_ID;
	}

	public void setUSR_ID(String uSR_ID) {
		USR_ID = uSR_ID;
	}

	public String getPST_CD() {
		return PST_CD;
	}

	public void setPST_CD(String pST_CD) {
		PST_CD = pST_CD;
	}

	public String getJOB_CD() {
		return JOB_CD;
	}

	public void setJOB_CD(String jOB_CD) {
		JOB_CD = jOB_CD;
	}

	public String getUSR_STTS_CD() {
		return USR_STTS_CD;
	}

	public void setUSR_STTS_CD(String uSR_STTS_CD) {
		USR_STTS_CD = uSR_STTS_CD;
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

	@Override
	public String getEntityName() {
		return "user";
	}
}
