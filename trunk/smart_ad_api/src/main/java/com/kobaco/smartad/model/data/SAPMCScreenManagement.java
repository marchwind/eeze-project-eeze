package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SAPMCScreenManagement implements Bean, Entity {
	
	private String PMC_SCRN_NO;
	private String SCRN_NM;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;

	
	public String getPMC_SCRN_NO() {
		return PMC_SCRN_NO;
	}


	public void setPMC_SCRN_NO(String pMC_SCRN_NO) {
		PMC_SCRN_NO = pMC_SCRN_NO;
	}


	public String getSCRN_NM() {
		return SCRN_NM;
	}


	public void setSCRN_NM(String sCRN_NM) {
		SCRN_NM = sCRN_NM;
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
		return "pmcScreenManagement";
	}
	

}
