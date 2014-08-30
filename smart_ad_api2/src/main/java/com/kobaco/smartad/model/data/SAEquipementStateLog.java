package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAEquipementStateLog implements Entity {

	private String EQPM_NO;
	private Long SEQ;
	private Date LOG_GTH_DTT;
	private String LOG_TP_CD;
	private String LOG_CNTT;
	private Date REG_DTT;
	private String REG_ID;
	private String UPD_ID;

	public String getEQPM_NO() {
		return EQPM_NO;
	}

	public void setEQPM_NO(String eQPM_NO) {
		EQPM_NO = eQPM_NO;
	}

	public Long getSEQ() {
		return SEQ;
	}

	public void setSEQ(Long sEQ) {
		SEQ = sEQ;
	}

	public Date getLOG_GTH_DTT() {
		return LOG_GTH_DTT;
	}

	public void setLOG_GTH_DTT(Date lOG_GTH_DTT) {
		LOG_GTH_DTT = lOG_GTH_DTT;
	}

	public String getLOG_TP_CD() {
		return LOG_TP_CD;
	}

	public void setLOG_TP_CD(String lOG_TP_CD) {
		LOG_TP_CD = lOG_TP_CD;
	}

	public String getLOG_CNTT() {
		return LOG_CNTT;
	}

	public void setLOG_CNTT(String lOG_CNTT) {
		LOG_CNTT = lOG_CNTT;
	}

	public Date getREG_DTT() {
		return REG_DTT;
	}

	public void setREG_DTT(Date rEG_DTT) {
		REG_DTT = rEG_DTT;
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
		return "equipementStateLog";
	}

}
