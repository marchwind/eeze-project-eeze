package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.NotificationInfo;

public class SANotification implements Bean , Entity{

	private String NTFC_NO;
	private String NTFC_SBJT;
	private String NTFC_CNTT;
	private int NTFC_COUNT;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	
	public SANotification(){
		
	}

	public SANotification(NotificationInfo info){
		this.NTFC_NO=info.getNotiNo();
		this.NTFC_SBJT=info.getNotiSubject();
		this.NTFC_CNTT=info.getNotiContent();
		}
		
	public String getNTFC_NO() {
		return NTFC_NO;
	}


	public void setNTFC_NO(String nTFC_NO) {
		NTFC_NO = nTFC_NO;
	}


	public String getNTFC_SBJT() {
		return NTFC_SBJT;
	}


	public void setNTFC_SBJT(String nTFC_SBJT) {
		NTFC_SBJT = nTFC_SBJT;
	}


	public String getNTFC_CNTT() {
		return NTFC_CNTT;
	}


	public void setNTFC_CNTT(String nTFC_CNTT) {
		NTFC_CNTT = nTFC_CNTT;
	}


	public int getNTFC_COUNT() {
		return NTFC_COUNT;
	}


	public void setNTFC_COUNT(int nTFC_COUNT) {
		NTFC_COUNT = nTFC_COUNT;
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
		return "notification";
	}

	
}
