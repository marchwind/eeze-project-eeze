package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.PmcMnagerInfo;

public class SAPMCManager extends SAUser implements Bean, Entity {
	
	private String MNGR_NO;
	private String MNGR_NM;
	private String PWD;
	private String CPHN;
	private String PHN;
	private String CNFM_YN;
	private String CNFR_ID;
	private Date CNFM_DTT;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String MNGR_PRMS_NO;
	private String MNGR_EML;
	private String BLT;
	private String PST_CD;
	private String MNGR_ID;
	private String MNGR_STTS_CD;
	
	public SAPMCManager() {
		
	}

	public SAPMCManager(PmcMnagerInfo info ){		
			
			this.MNGR_NO = info.getManagerNo();	
			this.MNGR_ID = info.getManagerId();
			this.PWD = info.getManagerPassword();
			this.MNGR_EML = info.getManagerEmail();
			this.MNGR_NM = info.getManagerName();
			this.UPD_ID =  info.getManagerId();
			this.PHN = info.getPhone();
			this.CPHN=info.getCellPhone();
			
	}

	
	public String getMNGR_STTS_CD() {
		return MNGR_STTS_CD;
	}

	public void setMNGR_STTS_CD(String mNGR_STTS_CD) {
		MNGR_STTS_CD = mNGR_STTS_CD;
	}

	public String getMNGR_NO() {
		return MNGR_NO;
	}


	public void setMNGR_NO(String mNGR_NO) {
		MNGR_NO = mNGR_NO;
	}


	public String getMNGR_NM() {
		return MNGR_NM;
	}


	public void setMNGR_NM(String mNGR_NM) {
		MNGR_NM = mNGR_NM;
	}


	public String getPWD() {
		return PWD;
	}


	public void setPWD(String pWD) {
		PWD = pWD;
	}


	public String getCPHN() {
		return CPHN;
	}


	public void setCPHN(String cPHN) {
		CPHN = cPHN;
	}


	public String getPHN() {
		return PHN;
	}


	public void setPHN(String pHN) {
		PHN = pHN;
	}


	public String getCNFM_YN() {
		return CNFM_YN;
	}


	public void setCNFM_YN(String cNFM_YN) {
		CNFM_YN = cNFM_YN;
	}


	public String getCNFR_ID() {
		return CNFR_ID;
	}


	public void setCNFR_ID(String cNFR_ID) {
		CNFR_ID = cNFR_ID;
	}


	public Date getCNFM_DTT() {
		return CNFM_DTT;
	}


	public void setCNFM_DTT(Date cNFM_DTT) {
		CNFM_DTT = cNFM_DTT;
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


	public String getMNGR_PRMS_NO() {
		return MNGR_PRMS_NO;
	}


	public void setMNGR_PRMS_NO(String mNGR_PRMS_NO) {
		MNGR_PRMS_NO = mNGR_PRMS_NO;
	}


	public String getMNGR_EML() {
		return MNGR_EML;
	}


	public void setMNGR_EML(String mNGR_EML) {
		MNGR_EML = mNGR_EML;
	}


	public String getBLT() {
		return BLT;
	}


	public void setBLT(String bLT) {
		BLT = bLT;
	}


	public String getPST_CD() {
		return PST_CD;
	}


	public void setPST_CD(String pST_CD) {
		PST_CD = pST_CD;
	}


	public String getMNGR_ID() {
		return MNGR_ID;
	}


	public void setMNGR_ID(String mNGR_ID) {
		MNGR_ID = mNGR_ID;
	}


	@Override
	public String getEntityName() {
		return "pmcManager";
	}

}
