package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

public class SAEquipementStateHistory /* extends SAFacilityReserveDate */ implements Entity {
	
	private String EQPM_NO;
	private Date GTH_DTT;
	private String EQPM_CNST_NO;
	private String CPU;
	private String MEM;
	
	
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


	public String getEQPM_CNST_NO() {
		return EQPM_CNST_NO;
	}


	public void setEQPM_CNST_NO(String eQPM_CNST_NO) {
		EQPM_CNST_NO = eQPM_CNST_NO;
	}


	public String getCPU() {
		return CPU;
	}


	public void setCPU(String cPU) {
		CPU = cPU;
	}


	public String getMEM() {
		return MEM;
	}


	public void setMEM(String mEM) {
		MEM = mEM;
	}


	@Override
	public String getEntityName() {
		return "equipementStateHistory";
	}

}
