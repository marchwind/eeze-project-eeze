package com.kobaco.smartad.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAFacility;

public class FacilityInfo implements ValueObject {
	private String facilityNo;
	private String facilityName;
	private String facilityExplain;
	private String facilityState;
	private List<EquipInfo> list;	

	private Date visitDate;
	private String userEnterpriseName;
	private String userEmail;
	private String userName;
	private String etc;
	private String facilityCheckInYn;

	public FacilityInfo(SAFacility dr) {
		this.facilityNo = dr.getFCLT_MGMT_NO();
		this.facilityName = dr.getFCLT_NM();
		this.visitDate = dr.getVST_DTT();
		this.userEmail = dr.getUSR_EML();
		this.etc       = dr.getCHK_CNTT();
		this.userEnterpriseName = dr.getENTP_NM();
		this.userName = dr.getUSR_NM();
	}
	
	public FacilityInfo() {
		;
	}
	
	public String getFacilityCheckInYn() {
		return facilityCheckInYn;
	}
	public void setFacilityCheckInYn(String facilityCheckInYn) {
		this.facilityCheckInYn = facilityCheckInYn;
	}
	
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getUserEnterpriseName() {
		return userEnterpriseName;
	}
	public void setUserEnterpriseName(String userEnterpriseName) {
		this.userEnterpriseName = userEnterpriseName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getFacilityState() {
		return facilityState;
	}
	public void setFacilityState(String facilityState) {
		this.facilityState = facilityState;
	}
	public List<EquipInfo> getList() {
		return list;
	}
	public void setList(List<EquipInfo> list) {
		this.list = list;
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
	public String getFacilityExplain() {
		return facilityExplain;
	}
	public void setFacilityExplain(String facilityExplain) {
		this.facilityExplain = facilityExplain;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
