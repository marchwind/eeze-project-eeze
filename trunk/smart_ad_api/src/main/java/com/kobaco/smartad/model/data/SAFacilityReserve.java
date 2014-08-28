package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAFacilityReserve extends SAFacilityReserveDate /* SAFacility */  implements Entity {

	private String USR_NO;
	private int RSRV_VSTN;
	private String RSRV_NO;
	private String FCLT_MGMT_NO;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String CNCL_YN;
	private String ENTP_NM;
	private String WRKR_NM;
	private Date CNCL_DTT;
	private String WRK_CNTT;
	
	private String FCLT_NM;
	private String CHK_CNTT;
	private String CPHN;
	
	private String CHKIN_CNFMR;
	private String CHKOUT_CNFMR;
	
	private String CVST_NO;
	private String VST_NO;
	private String FCLT_CHK_NO;
	
	public String getUSR_NO() {
		return USR_NO;
	}

	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
	}
	
	public int getRSRV_VSTN() {
		return RSRV_VSTN;
	}

	public void setRSRV_VSTN(int rSRV_VSTN) {
		RSRV_VSTN = rSRV_VSTN;
	}

	public String getRSRV_NO() {
		return RSRV_NO;
	}

	public void setRSRV_NO(String rSRV_NO) {
		RSRV_NO = rSRV_NO;
	}

	public String getFCLT_MGMT_NO() {
		return FCLT_MGMT_NO;
	}

	public void setFCLT_MGMT_NO(String fCLT_MGMT_NO) {
		FCLT_MGMT_NO = fCLT_MGMT_NO;
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

	public String getCNCL_YN() {
		return CNCL_YN;
	}

	public void setCNCL_YN(String cNCL_YN) {
		CNCL_YN = cNCL_YN;
	}

	public String getENTP_NM() {
		return ENTP_NM;
	}

	public void setENTP_NM(String eNTP_NM) {
		ENTP_NM = eNTP_NM;
	}

	public String getWRKR_NM() {
		return WRKR_NM;
	}

	public void setWRKR_NM(String wRKR_NM) {
		WRKR_NM = wRKR_NM;
	}

	public Date getCNCL_DTT() {
		return CNCL_DTT;
	}

	public void setCNCL_DTT(Date cNCL_DTT) {
		CNCL_DTT = cNCL_DTT;
	}

	public String getWRK_CNTT() {
		return WRK_CNTT;
	}

	public void setWRK_CNTT(String wRK_CNTT) {
		WRK_CNTT = wRK_CNTT;
	}

	public String getFCLT_NM() {
		return FCLT_NM;
	}

	public void setFCLT_NM(String fCLT_NM) {
		FCLT_NM = fCLT_NM;
	}

	public String getCHK_CNTT() {
		return CHK_CNTT;
	}

	public void setCHK_CNTT(String cHK_CNTT) {
		CHK_CNTT = cHK_CNTT;
	}

	public String getCPHN() {
		return CPHN;
	}

	public void setCPHN(String cPHN) {
		CPHN = cPHN;
	}
	
	public String getCHKIN_CNFMR() {
		return CHKIN_CNFMR;
	}

	public void setCHKIN_CNFMR(String cHKIN_CNFMR) {
		CHKIN_CNFMR = cHKIN_CNFMR;
	}

	public String getCHKOUT_CNFMR() {
		return CHKOUT_CNFMR;
	}

	public void setCHKOUT_CNFMR(String cHKOUT_CNFMR) {
		CHKOUT_CNFMR = cHKOUT_CNFMR;
	}

	public String getCVST_NO() {
		return CVST_NO;
	}

	public void setCVST_NO(String cVST_NO) {
		CVST_NO = cVST_NO;
	}

	public String getVST_NO() {
		return VST_NO;
	}

	public void setVST_NO(String vST_NO) {
		VST_NO = vST_NO;
	}

	public String getFCLT_CHK_NO() {
		return FCLT_CHK_NO;
	}

	public void setFCLT_CHK_NO(String fCLT_CHK_NO) {
		FCLT_CHK_NO = fCLT_CHK_NO;
	}

	@Override
	public String getEntityName() {
		return "facilityReserve";
	}
}
