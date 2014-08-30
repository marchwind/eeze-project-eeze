package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAEquipementProcessHistory implements Entity {
	private String EQPM_NO;
	private Date GTH_DTT;
	private String SW_MGMT_NO;
	private String HIST_CNTT;
	
	
	public String getEQPM_NO() {
		return EQPM_NO;
	}


	public void setEQPM_NO(String eQPM_NO) {
		EQPM_NO = eQPM_NO;
	}


	public Date getGTH_DTT() {
		return GTH_DTT;
	}


	public void setGTH_DTT(Date gTH_DTT) {
		GTH_DTT = gTH_DTT;
	}


	public String getSW_MGMT_NO() {
		return SW_MGMT_NO;
	}


	public void setSW_MGMT_NO(String sW_MGMT_NO) {
		SW_MGMT_NO = sW_MGMT_NO;
	}


	public String getHIST_CNTT() {
		return HIST_CNTT;
	}


	public void setHIST_CNTT(String hIST_CNTT) {
		HIST_CNTT = hIST_CNTT;
	}


	@Override
	public String getEntityName() {
		return "equipementProcessHistory";
	}
}
