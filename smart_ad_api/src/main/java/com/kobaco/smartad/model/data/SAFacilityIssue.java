package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;

public class SAFacilityIssue implements Bean, Entity {
	
	private String FCLT_MGMT_NO;
	private String ISS_NO;
	private Date ST_DTT;
	private Date ED_DTT;
	private String FCLT_OPRT_YN;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String ISS_CNTT;
	
	
	public String getFCLT_MGMT_NO() {
		return FCLT_MGMT_NO;
	}


	public void setFCLT_MGMT_NO(String fCLT_MGMT_NO) {
		FCLT_MGMT_NO = fCLT_MGMT_NO;
	}


	public String getISS_NO() {
		return ISS_NO;
	}


	public void setISS_NO(String iSS_NO) {
		ISS_NO = iSS_NO;
	}


	public Date getST_DTT() {
		return ST_DTT;
	}


	public void setST_DTT(Date sT_DTT) {
		ST_DTT = sT_DTT;
	}


	public Date getED_DTT() {
		return ED_DTT;
	}


	public void setED_DTT(Date eD_DTT) {
		ED_DTT = eD_DTT;
	}


	public String getFCLT_OPRT_YN() {
		return FCLT_OPRT_YN;
	}


	public void setFCLT_OPRT_YN(String fCLT_OPRT_YN) {
		FCLT_OPRT_YN = fCLT_OPRT_YN;
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


	public String getISS_CNTT() {
		return ISS_CNTT;
	}


	public void setISS_CNTT(String iSS_CNTT) {
		ISS_CNTT = iSS_CNTT;
	}


	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "facilityIssue";
	}

}
