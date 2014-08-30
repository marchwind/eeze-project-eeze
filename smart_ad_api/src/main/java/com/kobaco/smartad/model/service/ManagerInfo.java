package com.kobaco.smartad.model.service;

import com.kobaco.smartad.model.ValueObject;

public class ManagerInfo implements ValueObject {
	private String managerNo;
	private String managerId;
	private String managerPassword;
	private String managerEmail;
	private String managerCellPhone;
	public String getManagerNo() {
		return managerNo;
	}
	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerCellPhone() {
		return managerCellPhone;
	}
	public void setManagerCellPhone(String managerCellPhone) {
		this.managerCellPhone = managerCellPhone;
	}
	
}
