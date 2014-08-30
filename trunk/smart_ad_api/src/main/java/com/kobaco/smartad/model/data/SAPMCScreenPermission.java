package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SAPMCScreenPermission implements Bean,Entity {
	
	private String MNGR_PRMS_NO;
	private String PMC_SCRN_NO;
	private String SCRN_PRMS_CD;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	
	
	public String getMNGR_PRMS_NO() {
		return MNGR_PRMS_NO;
	}


	public void setMNGR_PRMS_NO(String mNGR_PRMS_NO) {
		MNGR_PRMS_NO = mNGR_PRMS_NO;
	}


	public String getPMC_SCRN_NO() {
		return PMC_SCRN_NO;
	}


	public void setPMC_SCRN_NO(String pMC_SCRN_NO) {
		PMC_SCRN_NO = pMC_SCRN_NO;
	}


	public String getSCRN_PRMS_CD() {
		return SCRN_PRMS_CD;
	}


	public void setSCRN_PRMS_CD(String sCRN_PRMS_CD) {
		SCRN_PRMS_CD = sCRN_PRMS_CD;
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
		return "pmcScreenPermission";
	}

}
