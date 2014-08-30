package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SANfcTag implements Entity {

	private String NFC_TAG_ID;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	

	public String getNFC_TAG_ID() {
		return NFC_TAG_ID;
	}


	public void setNFC_TAG_ID(String nFC_TAG_ID) {
		NFC_TAG_ID = nFC_TAG_ID;
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
		return "nfcTag";
	}

}
