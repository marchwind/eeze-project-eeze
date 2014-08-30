package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAUserLog implements Entity {

	private String USR_NO;
	private String USR_ID;
	private long SEQ;
	private String USR_LOG_TP;
	private String USR_LOG_CNTT;
	private Date USR_LOG_GTH_DT;
		
	
	public String getUSR_ID() {
		return USR_ID;
	}


	public void setUSR_ID(String uSR_ID) {
		USR_ID = uSR_ID;
	}


	public String getUSR_NO() {
		return USR_NO;
	}


	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
	}


	public long getSEQ() {
		return SEQ;
	}


	public void setSEQ(long sEQ) {
		SEQ = sEQ;
	}


	public String getUSR_LOG_TP() {
		return USR_LOG_TP;
	}


	public void setUSR_LOG_TP(String uSR_LOG_TP) {
		USR_LOG_TP = uSR_LOG_TP;
	}


	public String getUSR_LOG_CNTT() {
		return USR_LOG_CNTT;
	}


	public void setUSR_LOG_CNTT(String uSR_LOG_CNTT) {
		USR_LOG_CNTT = uSR_LOG_CNTT;
	}


	public Date getUSR_LOG_GTH_DT() {
		return USR_LOG_GTH_DT;
	}


	public void setUSR_LOG_GTH_DT(Date uSR_LOG_GTH_DT) {
		USR_LOG_GTH_DT = uSR_LOG_GTH_DT;
	}


	@Override
	public String getEntityName() {
		return "userLog";
	}

}
