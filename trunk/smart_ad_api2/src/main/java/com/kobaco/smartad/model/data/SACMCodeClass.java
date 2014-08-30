package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SACMCodeClass implements Bean, Entity {

	private String CMCD_CLS;
	private String CMCD_CLS_NM;
	private String CMCD_CLS_EXPL;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;

	
	public String getCMCD_CLS() {
		return CMCD_CLS;
	}


	public void setCMCD_CLS(String cMCD_CLS) {
		CMCD_CLS = cMCD_CLS;
	}


	public String getCMCD_CLS_NM() {
		return CMCD_CLS_NM;
	}


	public void setCMCD_CLS_NM(String cMCD_CLS_NM) {
		CMCD_CLS_NM = cMCD_CLS_NM;
	}


	public String getCMCD_CLS_EXPL() {
		return CMCD_CLS_EXPL;
	}


	public void setCMCD_CLS_EXPL(String cMCD_CLS_EXPL) {
		CMCD_CLS_EXPL = cMCD_CLS_EXPL;
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
		// TODO Auto-generated method stub
		return "cmcodeClass";
	}
}
