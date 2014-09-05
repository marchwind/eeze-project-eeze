package com.terascope.amano.incheon.dto;

import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.Bitmap;

public class ReceiptDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private final int FLAG = 12345;
	
	private String receiptNumber;
	private String receptionist;
	private String startDate;
	private String carNumber;
	private String carType;
	private String endDate;
	private String carBreakYn;
	private ArrayList<String> carBreakList;
	private String carPosition;
	private String customPhoneNumber;
	private String customSignNm;
	private String multiMediaNm;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public String getReceptionist() {
		return receptionist;
	}
	public void setReceptionist(String receptionist) {
		this.receptionist = receptionist;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCarBreakYn() {
		return carBreakYn;
	}
	public void setCarBreakYn(String carBreakYn) {
		this.carBreakYn = carBreakYn;
	}
	public ArrayList<String> getCarBreakList() {
		return carBreakList;
	}
	public void setCarBreakList(ArrayList<String> carBreakList) {
		this.carBreakList = carBreakList;
	}
	public String getCarPosition() {
		return carPosition;
	}
	public void setCarPosition(String carPosition) {
		this.carPosition = carPosition;
	}
	public String getCustomPhoneNumber() {
		return customPhoneNumber;
	}
	public void setCustomPhoneNumber(String customPhoneNumber) {
		this.customPhoneNumber = customPhoneNumber;
	}

	
	
	
}
