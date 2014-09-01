package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;

/**
 * Equipement CPU, Memory Status 
 * @author smart
 */
public class SAStatisticsEQS implements Entity {

	private String GTH_TM ;
	private Date GTH_DTT;
	private float CPU;
	private float MEM;
	private String PRCS;
	

	public String getGTH_TM() {
		return GTH_TM;
	}



	public void setGTH_TM(String gTH_TM) {
		GTH_TM = gTH_TM;
	}



	public Date getGTH_DTT() {
		return GTH_DTT;
	}



	public void setGTH_DTT(Date gTH_DTT) {
		GTH_DTT = gTH_DTT;
	}



	public float getCPU() {
		return CPU;
	}



	public void setCPU(float cPU) {
		CPU = cPU;
	}



	public float getMEM() {
		return MEM;
	}



	public void setMEM(float mEM) {
		MEM = mEM;
	}



	public String getPRCS() {
		return PRCS;
	}



	public void setPRCS(String pRCS) {
		PRCS = pRCS;
	}



	@Override
	public String getEntityName() {
		return "statistics";
	}

}
