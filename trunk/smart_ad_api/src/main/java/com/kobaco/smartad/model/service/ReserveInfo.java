package com.kobaco.smartad.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAFacilityReserve;

public class ReserveInfo implements ValueObject {
	private String reserveNo;
	private String facilityNo;
	private String facilityName;	
	private ArrayList<ReserveDetail> reserveDetail;
	private String cancelYn;
	private Date registerDate;
	private Date cancelDate;
	private String reserveMonth;
	private String reserveYear;
	private Date reserveStartDate;
	private String reserveState;
	private String userNo;
	private String enterpriseName;
	private String workerName;
	private int visitCount;
	private String workContent;
	private String reserveDetailNo;
	private int reserveTimeZone;
	private String facilityCheck;	
	private String userId;
	private String userCellPhone;
	private String checkInYn;
	private String checkOutYn;
	
	
	public String getCheckInYn() {
		return checkInYn;
	}
	public void setCheckInYn(String checkInYn) {
		this.checkInYn = checkInYn;
	}
	public String getCheckOutYn() {
		return checkOutYn;
	}
	public void setCheckOutYn(String checkOutYn) {
		this.checkOutYn = checkOutYn;
	}
	public ReserveInfo(SAFacilityReserve rev) {
		super();
		this.reserveNo        = rev.getRSRV_NO();
		this.facilityNo       = rev.getFCLT_MGMT_NO();
		this.facilityName     = rev.getFCLT_NM();
		this.cancelYn         = rev.getCNCL_YN();
		this.cancelDate       = rev.getCNCL_DTT();
		this.userNo           = rev.getUSR_NO();
		this.enterpriseName   = rev.getENTP_NM();
		this.workerName       = rev.getWRKR_NM();
		this.visitCount       = rev.getRSRV_VSTN();
		this.workContent      = rev.getWRK_CNTT();
		this.reserveDetailNo  = rev.getRSRV_DTL_NO();
		this.facilityCheck    = rev.getCHK_CNTT();
		this.userCellPhone    = rev.getCPHN();
		this.userId           = rev.getUPD_ID();
		this.reserveStartDate = rev.getRSRV_DT();
		this.visitCount       = rev.getRSRV_VSTN();
		
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCellPhone() {
		return userCellPhone;
	}

	public void setUserCellPhone(String userCellPhone) {
		this.userCellPhone = userCellPhone;
	}

	public ReserveInfo(){
		
	}
	public String getFacilityCheck() {
		return facilityCheck;
	}
	public void setFacilityCheck(String facilityCheck) {
		this.facilityCheck = facilityCheck;
	}
	public int getReserveTimeZone() {
		return reserveTimeZone;
	}
	public void setReserveTimeZone(int reserveTimeZone) {
		this.reserveTimeZone = reserveTimeZone;
	}
	
	public String getReserveDetailNo() {
		return reserveDetailNo;
	}
	public void setReserveDetailNo(String reserveDetailNo) {
		this.reserveDetailNo = reserveDetailNo;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getWorkerName() {
		return workerName;
	}
	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public String getWorkContent() {
		return workContent;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getReserveNo() {
		return reserveNo;
	}
	public void setReserveNo(String reserveNo) {
		this.reserveNo = reserveNo;
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

	public ArrayList<ReserveDetail> getReserveDetail() {
		return reserveDetail;
	}
	public void setReserveDetail(ArrayList<ReserveDetail> reserveDetail) {
		this.reserveDetail = reserveDetail;
	}
	public String getCancelYn() {
		return cancelYn;
	}
	public void setCancelYn(String cancelYn) {
		this.cancelYn = cancelYn;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getReserveMonth() {
		return reserveMonth;
	}
	public void setReserveMonth(String reserveMonth) {
		this.reserveMonth = reserveMonth;
	}
	public String getReserveYear() {
		return reserveYear;
	}
	public void setReserveYear(String reserveYear) {
		this.reserveYear = reserveYear;
	}
	public Date getReserveStartDate() {
		return reserveStartDate;
	}
	public void setReserveStartDate(Date reserveStartDate) {
		this.reserveStartDate = reserveStartDate;
	}
	public String getReserveState() {
		return reserveState;
	}
	public void setReserveState(String reserveState) {
		this.reserveState = reserveState;
	}
	

	
	
}
