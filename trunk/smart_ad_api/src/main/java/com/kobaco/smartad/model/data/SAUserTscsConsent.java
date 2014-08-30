package com.kobaco.smartad.model.data;

import java.util.Date;



import com.kobaco.smartad.model.Entity;

public class SAUserTscsConsent implements Entity {
	
	private String OPRT_TSCS_MGMT_NO;
	private String USR_NO;
	private String TSCS_TP_CD;
	private String TSCS_CNTT;
	private String CNST_YN;
	private Date CNST_DTT;
	private Date MDF_DTT;
	
	
	public String getCNST_YN() {
		return CNST_YN;
	}

	public void setCNST_YN(String cNST_YN) {
		CNST_YN = cNST_YN;
	}

	public String getTSCS_TP_CD() {
		return TSCS_TP_CD;
	}

	public void setTSCS_TP_CD(String tSCS_TP_CD) {
		TSCS_TP_CD = tSCS_TP_CD;
	}

	public String getTSCS_CNTT() {
		return TSCS_CNTT;
	}

	public void setTSCS_CNTT(String tSCS_CNTT) {
		TSCS_CNTT = tSCS_CNTT;
	}

	public String getOPRT_TSCS_MGMT_NO() {
		return OPRT_TSCS_MGMT_NO;
	}

	public void setOPRT_TSCS_MGMT_NO(String oPRT_TSCS_MGMT_NO) {
		OPRT_TSCS_MGMT_NO = oPRT_TSCS_MGMT_NO;
	}

	public String getUSR_NO() {
		return USR_NO;
	}

	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
	}

	public Date getCNST_DTT() {
		return CNST_DTT;
	}

	public void setCNST_DTT(Date cNST_DTT) {
		CNST_DTT = cNST_DTT;
	}

	public Date getMDF_DTT() {
		return MDF_DTT;
	}

	public void setMDF_DTT(Date date) {
		MDF_DTT = date;
	}

	@Override
	public String getEntityName() {
		return "userTscsConsent";
	}

}
