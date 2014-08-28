package com.kobaco.smartad.model.data;

import com.kobaco.smartad.model.Entity;

public class SAStatistics implements Entity {

	private String FCLT_MGMT_NO ;
	private String FCLT_NM;
	private float PRATE;
	private int TOT_CNT;
	private int FST;
	private int SND;
	private int TRD;
	private int FRT;
	private int FFT;

	public String getFCLT_MGMT_NO() {
		return FCLT_MGMT_NO;
	}


	public void setFCLT_MGMT_NO(String fCLT_MGMT_NO) {
		FCLT_MGMT_NO = fCLT_MGMT_NO;
	}


	public String getFCLT_NM() {
		return FCLT_NM;
	}


	public void setFCLT_NM(String fCLT_NM) {
		FCLT_NM = fCLT_NM;
	}


	public float getPRATE() {
		return PRATE;
	}


	public void setPRATE(float pRATE) {
		PRATE = pRATE;
	}


	public int getTOT_CNT() {
		return TOT_CNT;
	}


	public void setTOT_CNT(int tOT_CNT) {
		TOT_CNT = tOT_CNT;
	}


	public int getFST() {
		return FST;
	}


	public void setFST(int fST) {
		FST = fST;
	}


	public int getSND() {
		return SND;
	}


	public void setSND(int sND) {
		SND = sND;
	}


	public int getTRD() {
		return TRD;
	}


	public void setTRD(int tRD) {
		TRD = tRD;
	}


	public int getFRT() {
		return FRT;
	}


	public void setFRT(int fRT) {
		FRT = fRT;
	}


	public int getFFT() {
		return FFT;
	}


	public void setFFT(int fFT) {
		FFT = fFT;
	}


	@Override
	public String getEntityName() {
		return "statistics";
	}

}
