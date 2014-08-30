package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SACMCode implements Bean, Entity{
	
	private String CMCD;
	private String CMCD_CLS;
	private String CMCD_NM;
	private String CMCD_EXPL;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;

	
	public String getCMCD() {
		return CMCD;
	}


	public void setCMCD(String cMCD) {
		CMCD = cMCD;
	}


	public String getCMCD_CLS() {
		return CMCD_CLS;
	}


	public void setCMCD_CLS(String cMCD_CLS) {
		CMCD_CLS = cMCD_CLS;
	}


	public String getCMCD_NM() {
		return CMCD_NM;
	}


	public void setCMCD_NM(String cMCD_NM) {
		CMCD_NM = cMCD_NM;
	}


	public String getCMCD_EXPL() {
		return CMCD_EXPL;
	}


	public void setCMCD_EXPL(String cMCD_EXPL) {
		CMCD_EXPL = cMCD_EXPL;
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
		return "cmCode";
	}
	

}
