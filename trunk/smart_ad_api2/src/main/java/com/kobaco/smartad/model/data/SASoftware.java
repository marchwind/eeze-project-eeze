package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SASoftware implements Entity {
	private String SW_MGMT_NO;
	private String SW_NM;
	private String SW_MDL_NM;
	private String SW_PRCS_NM;
	private String SW_EXPL;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String SW_TP_CD;
	
	public String getSW_MGMT_NO() {
		return SW_MGMT_NO;
	}

	public void setSW_MGMT_NO(String sW_MGMT_NO) {
		SW_MGMT_NO = sW_MGMT_NO;
	}

	public String getSW_NM() {
		return SW_NM;
	}

	public void setSW_NM(String sW_NM) {
		SW_NM = sW_NM;
	}

	public String getSW_MDL_NM() {
		return SW_MDL_NM;
	}

	public void setSW_MDL_NM(String sW_MDL_NM) {
		SW_MDL_NM = sW_MDL_NM;
	}

	public String getSW_PRCS_NM() {
		return SW_PRCS_NM;
	}

	public void setSW_PRCS_NM(String sW_PRCS_NM) {
		SW_PRCS_NM = sW_PRCS_NM;
	}

	public String getSW_EXPL() {
		return SW_EXPL;
	}

	public void setSW_EXPL(String sW_EXPL) {
		SW_EXPL = sW_EXPL;
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

	public String getSW_TP_CD() {
		return SW_TP_CD;
	}

	public void setSW_TP_CD(String sW_TP_CD) {
		SW_TP_CD = sW_TP_CD;
	}

	@Override
	public String getEntityName() {
		return "software";
	}
}
