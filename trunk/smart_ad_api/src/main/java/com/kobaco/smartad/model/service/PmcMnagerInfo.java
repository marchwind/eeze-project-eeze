package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAPMCManager;

public class PmcMnagerInfo implements ValueObject{
	
	private String managerMode;
	private String managerNo;
	private String managerId;
	private String managerEmail;
	private String managerName;
	private String department;
	private String position;
	private String managerStatus;
	private String managerPassword;
	private String phone;
	private String cellPhone;
	private boolean isLogin =false;
	private Date loginDate;
	private String pwReset;
	private String managerPhone;
	
	public PmcMnagerInfo(){
		
	}
	public PmcMnagerInfo(SAPMCManager sa){
		
		this.managerNo=sa.getMNGR_NO();
		this.managerId=sa.getMNGR_ID();
		this.managerName=sa.getMNGR_NM();
		this.managerEmail=sa.getMNGR_EML();
		this.department = sa.getBLT();
		this.position = sa.getPST_CD();
		this.managerStatus = sa.getMNGR_STTS_CD();
		this.phone = sa.getPHN();
		this.cellPhone = sa.getCPHN();
	}	
	
	public PmcMnagerInfo(PmcSessionInfo sess){
		
		this.managerNo=sess.getManagerNo();
		this.managerId=sess.getManagerId();
		this.managerName=sess.getManagerName();
		this.managerEmail=sess.getManagerEmail();
		this.isLogin = sess.isLogin();
	}
	
	public String getPwReset() {
		return pwReset;
	}
	public void setPwReset(String pwReset) {
		this.pwReset = pwReset;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getManagerPassword() {
		return managerPassword;
	}
	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}
	public String getManagerMode() {
		return managerMode;
	}
	public void setManagerMode(String managerMode) {
		this.managerMode = managerMode;
	}
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
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getManagerStatus() {
		return managerStatus;
	}
	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}
	
	public String getManagerPhone() {
		if ( "".equals(this.cellPhone) || this.cellPhone==null) {
			return this.phone;
		} else {
			return this.cellPhone;
		}
	}
	
	public void setManagerPhone(String phone) {
		this.cellPhone = phone;
		this.phone = phone;
	}
}