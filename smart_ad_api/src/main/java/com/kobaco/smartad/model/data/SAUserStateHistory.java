package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAUserStateHistory implements Entity {

	private String HIST_SEQ;
	private String USR_NO;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String MDF_TP_CD;
	private String MDF_CNTT;
	
	public String getHIST_SEQ() {
		return HIST_SEQ;
	}

	public void setHIST_SEQ(String hIST_SEQ) {
		HIST_SEQ = hIST_SEQ;
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

	public String getMDF_TP_CD() {
		return MDF_TP_CD;
	}

	public void setMDF_TP_CD(String mDF_TP_CD) {
		MDF_TP_CD = mDF_TP_CD;
	}

	public String getMDF_CNTT() {
		return MDF_CNTT;
	}

	public void setMDF_CNTT(String mDF_CNTT) {
		MDF_CNTT = mDF_CNTT;
	}

	@Override
	public String getEntityName() {
		return "userStateHistory";
	}

}
