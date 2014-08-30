package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.ReserveInfo;

public class SAFacilityVisitHistory /* extends SAFacilityCheck */ implements Entity {

	private Date   REG_DTT;
	private Date   UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private Date   VST_DTT;
	private String VST_NO;
	private String RSRV_NO;
	private String RSRV_DTL_NO;
	private Date   CHKIN_DTT;
	private String CHKIN_CNFMR;
	private Date   CHKOUT_DTT;
	private String CHKOUT_CNFMR;
	
	
	
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


	public Date getVST_DTT() {
		return VST_DTT;
	}


	public void setVST_DTT(Date vST_DTT) {
		VST_DTT = vST_DTT;
	}


	public String getVST_NO() {
		return VST_NO;
	}


	public void setVST_NO(String vST_NO) {
		VST_NO = vST_NO;
	}


	public String getRSRV_NO() {
		return RSRV_NO;
	}


	public void setRSRV_NO(String rSRV_NO) {
		RSRV_NO = rSRV_NO;
	}


	public String getRSRV_DTL_NO() {
		return RSRV_DTL_NO;
	}


	public void setRSRV_DTL_NO(String rSRV_DTL_NO) {
		RSRV_DTL_NO = rSRV_DTL_NO;
	}


	public Date getCHKIN_DTT() {
		return CHKIN_DTT;
	}


	public void setCHKIN_DTT(Date cHKIN_DTT) {
		CHKIN_DTT = cHKIN_DTT;
	}


	public String getCHKIN_CNFMR() {
		return CHKIN_CNFMR;
	}


	public void setCHKIN_CNFMR(String cHKIN_CNFMR) {
		CHKIN_CNFMR = cHKIN_CNFMR;
	}


	public Date getCHKOUT_DTT() {
		return CHKOUT_DTT;
	}


	public void setCHKOUT_DTT(Date cHKOUT_DTT) {
		CHKOUT_DTT = cHKOUT_DTT;
	}


	public String getCHKOUT_CNFMR() {
		return CHKOUT_CNFMR;
	}


	public void setCHKOUT_CNFMR(String cHKOUT_CNFMR) {
		CHKOUT_CNFMR = cHKOUT_CNFMR;
	}


	@Override
	public String getEntityName() {
		return "facilityVisitHistory";
	}

}
