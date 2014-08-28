package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.ReserveInfo;

public class SAFacilityCheck /* extends SAEquipement */ implements Bean, Entity{
	
	private String FCLT_CHK_NO;
	private String CVST_NO;
	private String CHK_TP;
	private String CHKR;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private Date CHK_DTT;
	private String CHK_CNTT;
	private String FCLT_MGMT_NO;
	
	

	public String getFCLT_CHK_NO() {
		return FCLT_CHK_NO;
	}



	public void setFCLT_CHK_NO(String fCLT_CHK_NO) {
		FCLT_CHK_NO = fCLT_CHK_NO;
	}	

	public String getCVST_NO() {
		return CVST_NO;
	}



	public void setCVST_NO(String cVST_NO) {
		CVST_NO = cVST_NO;
	}



	public String getCHK_TP() {
		return CHK_TP;
	}



	public void setCHK_TP(String cHK_TP) {
		CHK_TP = cHK_TP;
	}



	public String getCHKR() {
		return CHKR;
	}



	public void setCHKR(String cHKR) {
		CHKR = cHKR;
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



	public Date getCHK_DTT() {
		return CHK_DTT;
	}



	public void setCHK_DTT(Date date) {
		CHK_DTT = date;
	}



	public String getCHK_CNTT() {
		return CHK_CNTT;
	}



	public void setCHK_CNTT(String cHK_CNTT) {
		CHK_CNTT = cHK_CNTT;
	}



	public String getFCLT_MGMT_NO() {
		return FCLT_MGMT_NO;
	}



	public void setFCLT_MGMT_NO(String fCLT_MGMT_NO) {
		FCLT_MGMT_NO = fCLT_MGMT_NO;
	}



	@Override
	public String getEntityName() {
		return "facilityCheck";
	}
	

}
