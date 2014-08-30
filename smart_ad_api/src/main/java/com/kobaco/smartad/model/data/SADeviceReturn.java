package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SADeviceReturn implements Entity {
	private String DEV_RNT_NO;
	private String DEV_RTN_NO;
	private Date RTN_DTT;
	private String RTN_STTS_EXPL;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	
	
	public String getDEV_RNT_NO() {
		return DEV_RNT_NO;
	}


	public void setDEV_RNT_NO(String dEV_RNT_NO) {
		DEV_RNT_NO = dEV_RNT_NO;
	}


	public String getDEV_RTN_NO() {
		return DEV_RTN_NO;
	}


	public void setDEV_RTN_NO(String dEV_RTN_NO) {
		DEV_RTN_NO = dEV_RTN_NO;
	}


	public Date getRTN_DTT() {
		return RTN_DTT;
	}


	public void setRTN_DTT(Date rTN_DTT) {
		RTN_DTT = rTN_DTT;
	}


	public String getRTN_STTS_EXPL() {
		return RTN_STTS_EXPL;
	}


	public void setRTN_STTS_EXPL(String rTN_STTS_EXPL) {
		RTN_STTS_EXPL = rTN_STTS_EXPL;
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
		return "deviceReturn";
	}
}
