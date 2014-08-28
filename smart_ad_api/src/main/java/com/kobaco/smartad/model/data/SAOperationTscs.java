package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

/*
 * �댁쁺�쎄�
 */
public class SAOperationTscs implements Entity {

	private String OPRT_TSCS_MGMT_NO;
	private String TSCS_CNTT;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String TSCS_TP_CD;
	
	public String getOPRT_TSCS_MGMT_NO() {
		return OPRT_TSCS_MGMT_NO;
	}

	public void setOPRT_TSCS_MGMT_NO(String oPRT_TSCS_MGMT_NO) {
		OPRT_TSCS_MGMT_NO = oPRT_TSCS_MGMT_NO;
	}

	public String getTSCS_CNTT() {
		return TSCS_CNTT;
	}

	public void setTSCS_CNTT(String tSCS_CNTT) {
		TSCS_CNTT = tSCS_CNTT;
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

	public String getTSCS_TP_CD() {
		return TSCS_TP_CD;
	}

	public void setTSCS_TP_CD(String tSCS_TP_CD) {
		TSCS_TP_CD = tSCS_TP_CD;
	}

	@Override
	public String getEntityName() {
		return "operationTscs";
	}

}
