package com.kobaco.smartad.model.service;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAFaq;
import com.kobaco.smartad.model.data.SAStatistics;

public class StatisticsInfo implements ValueObject {

	private String facilityNo;
	private String facilityName;
	private float percentRate;
	
	private int totalCnt;
	private int firstWeekCnt;
	private int secondWeekCnt;
	private int thirdWeekCnt;
	private int forthWeekCnt;
	private int fifthWeekCnt;

	public StatisticsInfo(){
		
	}
	
	public StatisticsInfo(SAStatistics sa){
		this.facilityNo   = sa.getFCLT_MGMT_NO();
		this.facilityName = sa.getFCLT_NM();
		this.percentRate  = sa.getPRATE();
		this.totalCnt     = sa.getTOT_CNT();
		this.firstWeekCnt = sa.getFST();
		this.secondWeekCnt= sa.getSND();
		this.thirdWeekCnt = sa.getTRD();
		this.forthWeekCnt = sa.getFRT();
		this.fifthWeekCnt = sa.getFFT();
	}

	public String getFacilityNo() {
		return facilityNo;
	}

	public void setFacilityNo(String facilityNo) {
		this.facilityNo = facilityNo;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public float getPercentRate() {
		return percentRate;
	}

	public void setPercentRate(float percentRate) {
		this.percentRate = percentRate;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getFirstWeekCnt() {
		return firstWeekCnt;
	}

	public void setFirstWeekCnt(int firstWeekCnt) {
		this.firstWeekCnt = firstWeekCnt;
	}

	public int getSecondWeekCnt() {
		return secondWeekCnt;
	}

	public void setSecondWeekCnt(int secondWeekCnt) {
		this.secondWeekCnt = secondWeekCnt;
	}

	public int getThirdWeekCnt() {
		return thirdWeekCnt;
	}

	public void setThirdWeekCnt(int thirdWeekCnt) {
		this.thirdWeekCnt = thirdWeekCnt;
	}

	public int getForthWeekCnt() {
		return forthWeekCnt;
	}

	public void setForthWeekCnt(int forthWeekCnt) {
		this.forthWeekCnt = forthWeekCnt;
	}

	public int getFifthWeekCnt() {
		return fifthWeekCnt;
	}

	public void setFifthWeekCnt(int fifthWeekCnt) {
		this.fifthWeekCnt = fifthWeekCnt;
	}
	
}
