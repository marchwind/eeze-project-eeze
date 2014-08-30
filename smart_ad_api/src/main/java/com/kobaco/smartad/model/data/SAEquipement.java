package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.utils.CommonCode;

public class SAEquipement implements Bean, Entity{
	
	private String EQPM_NO;
	private String FCLT_MGMT_NO;
	private String EQPM_NM;
	private String EQPM_IP;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String EQPM_STTS_CD;
	private String MDL_NM;
	private String NFC_TAG_ID;
	private String EQPM_EXPL;
	private String MFTR;
	private String EQPM_TP;
	private String EQPM_OS;
	private String EQPM_MAC;
	private String EQPM_SPC;
	private String EQPM_MEMO;
	private String FCLT_NM;
	private Date LST_CHK_DTT;
	private String LST_DEV_RNT_NO;
	private int EQPM_ALV_CNT;
	private String EQPM_PWR_STTS_CD;
	
	public SAEquipement() {
		// TODO Auto-generated constructor stub
	}

	public SAEquipement(EquipInfo info,PmcMnagerInfo mg) {
		// TODO Auto-generated constructor stub
		this.EQPM_NO	= info.getEquipNo();
		this.EQPM_IP 	= info.getEquipIp();
		this.EQPM_NM 	= info.getEquipName();
		this.EQPM_TP 	= info.getEquipType();
		this.MDL_NM  	= info.getEquipModel();
		this.EQPM_OS	= info.getEquipOs();
		this.EQPM_MAC 	= info.getEquipMac();
		this.FCLT_MGMT_NO	= info.getFacilityNo();
		this.EQPM_STTS_CD = info.getEquipState();
		this.EQPM_SPC	= info.getEquipSpec();
		this.EQPM_MEMO	= info.getEquipMemo();
		this.EQPM_EXPL  = info.getEquipExplain();
		this.REG_ID		= mg.getManagerId();
		this.UPD_ID		= mg.getManagerId();
	}
	
	


	public String getFCLT_NM() {
		return FCLT_NM;
	}

	public void setFCLT_NM(String fCLT_NM) {
		FCLT_NM = fCLT_NM;
	}

	public String getFCLT_MGMT_NO() {
		return FCLT_MGMT_NO;
	}

	public void setFCLT_MGMT_NO(String fCLT_MGMT_NO) {
		FCLT_MGMT_NO = fCLT_MGMT_NO;
	}

	public String getEQPM_MAC() {
		return EQPM_MAC;
	}

	public void setEQPM_MAC(String eQPM_MAC) {
		EQPM_MAC = eQPM_MAC;
	}

	public String getEQPM_SPC() {
		return EQPM_SPC;
	}

	public void setEQPM_SPC(String eQPM_SPC) {
		EQPM_SPC = eQPM_SPC;
	}

	public String getEQPM_MEMO() {
		return EQPM_MEMO;
	}

	public void setEQPM_MEMO(String eQPM_MEMO) {
		EQPM_MEMO = eQPM_MEMO;
	}

	public String getEQPM_OS() {
		return EQPM_OS;
	}

	public void setEQPM_OS(String eQPM_OS) {
		EQPM_OS = eQPM_OS;
	}

	public String getEQPM_TP() {
		return EQPM_TP;
	}

	public void setEQPM_TP(String eQPM_TP) {
		EQPM_TP = eQPM_TP;
	}

	public String getEQPM_NO() {
		return EQPM_NO;
	}



	public void setEQPM_NO(String eQPM_NO) {
		EQPM_NO = eQPM_NO;
	}


	public String getEQPM_NM() {
		return EQPM_NM;
	}



	public void setEQPM_NM(String eQPM_NM) {
		EQPM_NM = eQPM_NM;
	}



	public String getEQPM_IP() {
		return EQPM_IP;
	}



	public void setEQPM_IP(String eQPM_IP) {
		EQPM_IP = eQPM_IP;
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



	public String getEQPM_STTS_CD() {
		return EQPM_STTS_CD;
	}



	public void setEQPM_STTS_CD(String eQPM_STTS_CD) {
		EQPM_STTS_CD = eQPM_STTS_CD;
	}



	public String getMDL_NM() {
		return MDL_NM;
	}



	public void setMDL_NM(String mDL_NM) {
		MDL_NM = mDL_NM;
	}



	public String getNFC_TAG_ID() {
		return NFC_TAG_ID;
	}



	public void setNFC_TAG_ID(String nFC_TAG_ID) {
		NFC_TAG_ID = nFC_TAG_ID;
	}



	public String getEQPM_EXPL() {
		return EQPM_EXPL;
	}



	public void setEQPM_EXPL(String eQPM_EXPL) {
		EQPM_EXPL = eQPM_EXPL;
	}



	public String getMFTR() {
		return MFTR;
	}



	public void setMFTR(String mFTR) {
		MFTR = mFTR;
	}



	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "equipement";
	}

	public Date getLST_CHK_DTT() {
		return LST_CHK_DTT;
	}

	public void setLST_CHK_DTT(Date lST_CHK_DTT) {
		LST_CHK_DTT = lST_CHK_DTT;
	}

	public String getLST_DEV_RNT_NO() {
		return LST_DEV_RNT_NO;
	}

	public void setLST_DEV_RNT_NO(String lST_DEV_RNT_NO) {
		LST_DEV_RNT_NO = lST_DEV_RNT_NO;
	}

	public int getEQPM_ALV_CNT() {
		return EQPM_ALV_CNT;
	}

	public void setEQPM_ALV_CNT(int eQPM_ALV_CNT) {
		EQPM_ALV_CNT = eQPM_ALV_CNT;
	}

	public String getEQPM_PWR_STTS_CD() {
		return EQPM_PWR_STTS_CD;
	}

	public void setEQPM_PWR_STTS_CD(String eQPM_PWR_STTS_CD) {
		EQPM_PWR_STTS_CD = eQPM_PWR_STTS_CD;
	}

}
