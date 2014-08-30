package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;

public class ReserveDetail implements ValueObject {
	private String userNo;
	private Date reserveDate;
	private int reserveTimeZone; // 1: 오전, 2: 오후
	private String reserveDetailNo;
	private String enterpriseName;
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
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getReserveDetailNo() {
		return reserveDetailNo;
	}
	public void setReserveDetailNo(String reserveDetailNo) {
		this.reserveDetailNo = reserveDetailNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public int getReserveTimeZone() {
		return reserveTimeZone;
	}
	public void setReserveTimeZone(int reserveTimeZone) {
		this.reserveTimeZone = reserveTimeZone;
	}
	
	
}
