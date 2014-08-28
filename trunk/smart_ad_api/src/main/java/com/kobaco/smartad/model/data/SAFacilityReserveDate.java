package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAFacilityReserveDate /* extends SAFacilityVisitHistory */ implements Entity {

	private String RSRV_NO;
	private String RSRV_DTL_NO;
	private Date   RSRV_DT;
	private String TZ_TP_CD;
	private Date   REG_DTT;
	private Date   UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	
	
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

	public Date getRSRV_DT() {
		return RSRV_DT;
	}


	public void setRSRV_DT(Date rSRV_DT) {
		RSRV_DT = rSRV_DT;
	}


	public String getTZ_TP_CD() {
		return TZ_TP_CD;
	}


	public void setTZ_TP_CD(String tZ_TP_CD) {
		TZ_TP_CD = tZ_TP_CD;
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
		return "facilityReserveDate";
	}

}
