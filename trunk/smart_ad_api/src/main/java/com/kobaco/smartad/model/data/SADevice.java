package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SADevice implements Entity {

	public String DEV_MGMT_NO ;
	public String MDL_NM;
	public int WDT_RESO;
	public int HGT_RESO;
	public String MFTR;
	public String DEV_NM;
	public String DEV_STTS_CD;
	public Date REG_DTT;
	public Date UPD_DTT;
	public String REG_ID;
	public String UPD_ID;
	public String OS;
	
	



	public String getDEV_MGMT_NO() {
		return DEV_MGMT_NO;
	}


	public void setDEV_MGMT_NO(String dEV_MGMT_NO) {
		DEV_MGMT_NO = dEV_MGMT_NO;
	}


	public String getMDL_NM() {
		return MDL_NM;
	}


	public void setMDL_NM(String mDL_NM) {
		MDL_NM = mDL_NM;
	}


	public int getWDT_RESO() {
		return WDT_RESO;
	}


	public void setWDT_RESO(int wDT_RESO) {
		WDT_RESO = wDT_RESO;
	}


	public int getHGT_RESO() {
		return HGT_RESO;
	}


	public void setHGT_RESO(int hGT_RESO) {
		HGT_RESO = hGT_RESO;
	}


	public String getMFTR() {
		return MFTR;
	}


	public void setMFTR(String mFTR) {
		MFTR = mFTR;
	}


	public String getDEV_NM() {
		return DEV_NM;
	}


	public void setDEV_NM(String dEV_NM) {
		DEV_NM = dEV_NM;
	}


	public String getDEV_STTS_CD() {
		return DEV_STTS_CD;
	}


	public void setDEV_STTS_CD(String dEV_STTS_CD) {
		DEV_STTS_CD = dEV_STTS_CD;
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


	public String getOS() {
		return OS;
	}


	public void setOS(String oS) {
		OS = oS;
	}


	@Override
	public String getEntityName() {
		return "device";
	}

}
