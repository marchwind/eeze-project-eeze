package com.kobaco.smartad.model.data;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.FacilityInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;

public class SAFacilityMain implements Bean, Entity{

	private String FCLT_NM;
	private String FCLT_MGMT_NO;
	private int FCLT_CHKIN_CNT;
	private String EQPM_NO;
	private String EQPM_NM;
	private String EQPM_STTS_CD;
	private int EQPM_ALV_CNT;
	
	public SAFacilityMain() {
		;
	}
	
	public SAFacilityMain(FacilityInfo facilityInfo) {
		this.FCLT_MGMT_NO = facilityInfo.getFacilityNo();
	}

	public SAFacilityMain(FacilityInfo facilityInfo, PmcMnagerInfo info) {
		// TODO Auto-generated constructor stub
		this.FCLT_MGMT_NO = facilityInfo.getFacilityNo();
		this.FCLT_NM = facilityInfo.getFacilityName();
//		this.FCLT_EXPL = facilityInfo.getFacilityExplain();
//		this.FCLT_STTS_CD = facilityInfo.getFacilityState();
//		this.UPD_ID = info.getManagerId();
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

	public int getFCLT_CHKIN_CNT() {
		return FCLT_CHKIN_CNT;
	}

	public void setFCLT_CHKIN_CNT(int fCLT_CHKIN_CNT) {
		FCLT_CHKIN_CNT = fCLT_CHKIN_CNT;
	}

	public int getEQPM_ALV_CNT() {
		return EQPM_ALV_CNT;
	}

	public void setEQPM_ALV_CNT(int eQPM_ALV_CNT) {
		EQPM_ALV_CNT = eQPM_ALV_CNT;
	}

	public String getEQPM_STTS_CD() {
		return EQPM_STTS_CD;
	}

	public void setEQPM_STTS_CD(String eQPM_STTS_CD) {
		EQPM_STTS_CD = eQPM_STTS_CD;
	}

	@Override
	public String getEntityName() {
		return "facility";
	}

}
