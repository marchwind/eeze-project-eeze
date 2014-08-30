package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.FacilityInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;

public class SAFacility extends SAEquipement implements Bean, Entity{
	
	private String FCLT_MGMT_NO;
	private String FCLT_NM;
	private String FCLT_EXPL;
	private String STTN;
	private Date   REG_DTT;
	private Date   UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String FCLT_LOC;
	private String FCLT_STTS_CD;
	private String NFC_TAG_ID;
	
	private Date VST_DTT;
	private String USR_EML;
	private String USR_NM;
	private String CHK_CNTT;
	private String ENTP_NM;
	
	public SAFacility() {
		// TODO Auto-generated constructor stub
	}
	
	public SAFacility(FacilityInfo facilityInfo) {
		// TODO Auto-generated constructor stub
		this.FCLT_MGMT_NO = facilityInfo.getFacilityNo();
	}


	public SAFacility(FacilityInfo facilityInfo, PmcMnagerInfo info) {
		// TODO Auto-generated constructor stub
		this.FCLT_MGMT_NO = facilityInfo.getFacilityNo();
		this.FCLT_NM = facilityInfo.getFacilityName();
		this.FCLT_EXPL = facilityInfo.getFacilityExplain();
		this.FCLT_STTS_CD = facilityInfo.getFacilityState();
		this.UPD_ID = info.getManagerId();
	}

	public String getFCLT_MGMT_NO() {
		return FCLT_MGMT_NO;
	}


	public void setFCLT_MGMT_NO(String fCLT_MGMT_NO) {
		FCLT_MGMT_NO = fCLT_MGMT_NO;
	}


	public String getFCLT_NM() {
		return FCLT_NM;
	}


	public void setFCLT_NM(String fCLT_NM) {
		FCLT_NM = fCLT_NM;
	}


	public String getFCLT_EXPL() {
		return FCLT_EXPL;
	}


	public void setFCLT_EXPL(String fCLT_EXPL) {
		FCLT_EXPL = fCLT_EXPL;
	}


	public String getSTTN() {
		return STTN;
	}


	public void setSTTN(String sTTN) {
		STTN = sTTN;
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


	public String getFCLT_LOC() {
		return FCLT_LOC;
	}


	public void setFCLT_LOC(String fCLT_LOC) {
		FCLT_LOC = fCLT_LOC;
	}


	public String getFCLT_STTS_CD() {
		return FCLT_STTS_CD;
	}


	public void setFCLT_STTS_CD(String fCLT_STTS_CD) {
		FCLT_STTS_CD = fCLT_STTS_CD;
	}


	public String getNFC_TAG_ID() {
		return NFC_TAG_ID;
	}


	public void setNFC_TAG_ID(String nFC_TAG_ID) {
		NFC_TAG_ID = nFC_TAG_ID;
	}

	public Date getVST_DTT() {
		return VST_DTT;
	}

	public void setVST_DTT(Date vST_DTT) {
		VST_DTT = vST_DTT;
	}

	public String getUSR_EML() {
		return USR_EML;
	}

	public void setUSR_EML(String uSR_EML) {
		USR_EML = uSR_EML;
	}

	public String getCHK_CNTT() {
		return CHK_CNTT;
	}

	public void setCHK_CNTT(String cHK_CNTT) {
		CHK_CNTT = cHK_CNTT;
	}

	public String getENTP_NM() {
		return ENTP_NM;
	}

	public void setENTP_NM(String eNTP_NM) {
		ENTP_NM = eNTP_NM;
	}

	
	public String getUSR_NM() {
		return USR_NM;
	}

	public void setUSR_NM(String uSR_NM) {
		USR_NM = uSR_NM;
	}

	@Override
	public String getEntityName() {
		return "facility";
	}
	

}
