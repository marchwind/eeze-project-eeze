package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAPMCManager;

public class PmcSessionInfo implements ValueObject{
	
	private PmcMnagerInfo loginInfo;
//	private String managerNo;
//	private String managerId;
//	private String managerEmail;
//	private String managerName;
//	private Date loginDate;
	
	private boolean isLogin =false;
	
	
	public PmcSessionInfo(){
		
	}
	

	public PmcMnagerInfo getLoginInfo() {
		return loginInfo;
	}


	public void setLoginInfo(PmcMnagerInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public Date getLoginDate() {
		return loginInfo.getLoginDate();
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getManagerNo() {
		return loginInfo.getManagerNo();
	}
	public String getManagerId() {
		return loginInfo.getManagerId();
	}
	public String getManagerEmail() {
		return loginInfo.getManagerEmail();
	}
	public String getManagerName() {
		return loginInfo.getManagerName();
	}
}