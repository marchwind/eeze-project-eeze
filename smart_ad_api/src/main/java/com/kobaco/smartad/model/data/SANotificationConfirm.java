package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SANotificationConfirm implements Bean, Entity{

	private String USR_NO;
	private String NTFC_NO;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;

	public String getUSR_NO() {
		return USR_NO;
	}



	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
	}



	public String getNTFC_NO() {
		return NTFC_NO;
	}



	public void setNTFC_NO(String nTFC_NO) {
		NTFC_NO = nTFC_NO;
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
		return "notificationConfirm";
	}

}
