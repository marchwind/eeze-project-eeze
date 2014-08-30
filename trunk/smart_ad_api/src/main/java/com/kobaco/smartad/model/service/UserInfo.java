package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAUser;
import com.kobaco.smartad.model.data.SAUserTscsConsent;

public class UserInfo implements ValueObject {
	private String userNo;
	private String userId;
	private String userPassword;
	private String userEmail;
	private String userName;
	private String userCellPhone;
	private String enterpriseName;
	private String enterpriseAddress;
	private String userPhone;
	private String consentTscsYn;        // 약관동의
	private String consentReceiveInfoYn; // 정보수신동의
	private Date userLoginDate;
	private String author;               // 정보 생성자 (혹은 생성처)
	private boolean isLogin;
	private String userIdYn;
	private String userStatus;
	private int searchKey;
	private String searchKeyword;
	
	
	
	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(int searchKey) {
		this.searchKey = searchKey;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserIdYn() {
		return userIdYn;
	}

	public void setUserIdYn(String userIdYn) {
		this.userIdYn = userIdYn;
	}

	public UserInfo(){
	}
	
	public UserInfo(SAUser user) {
		setSAUser(user);
	}
	
	public UserInfo(SAUser user, SAEnterprise ent) {
		setSAUser(user);
		setSAEnterprise(ent);
	}
	
	public void setPmcUser(SAUser info){
		this.userId = info.getUSR_ID();
		this.userNo = info.getUSR_NO();
		this.userName = info.getUSR_NM();
		this.enterpriseName = info.getENTP_NM();
		this.userCellPhone = info.getCPHN();
		this.userStatus = info.getUSR_STTS_CD();
		this.enterpriseAddress = info.getENTP_ADDR();
		this.userPhone = info.getPHN();
		this.userEmail=info.getUSR_EML();		
	}
	public void setSAUser(SAUser user) {
		setUserNo(user.getUSR_NO());
		setUserId(user.getUSR_ID());
		setUserPassword(user.getUSR_PWD());
		setUserEmail(user.getUSR_EML());
		setUserName(user.getUSR_NM());
		setUserCellPhone(user.getCPHN());
		setEnterpriseName(user.getENTP_NM());
		setEnterpriseAddress(user.getENTP_ADDR());
	}
	
	public void setSAEnterprise(SAEnterprise ent) {
		setEnterpriseName(ent.getENTP_NM());
		setEnterpriseAddress(ent.getENTP_ADDR());
	}
	
	
	public SAUser getSAUser() {
		return new SAUser() {
			{
				setUSR_NO(getUserNo());
				setUSR_ID(getUserId());
				setUSR_PWD(getUserPassword());
				setUSR_EML(getUserEmail());
				setUSR_NM(getUserName());
				setCPHN(getUserCellPhone());
				setPHN(getUserPhone());
				setREG_ID(getAuthor());
				setUPD_ID(getAuthor());
				setENTP_NM(getEnterpriseName());
				setENTP_ADDR(getEnterpriseAddress());
			}
		};
	}
	
	public SAEnterprise getSAEnterprise() {
		return new SAEnterprise() {
			{
				setUSR_NO(getUserNo());
				setENTP_NM(getEnterpriseName());
				setENTP_ADDR(getEnterpriseAddress());
				setREG_ID(getAuthor());
				setUPD_ID(getAuthor());
			}
		};
	}
	
	public Date getUserLoginDate() {
		return userLoginDate;
	}
	public void setUserLoginDate(Date userLoginDate) {
		this.userLoginDate = userLoginDate;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCellPhone() {
		return userCellPhone;
	}
	public void setUserCellPhone(String userCellPhone) {
		this.userCellPhone = userCellPhone;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}
	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getConsentTscsYn() {
		return consentTscsYn;
	}
	public void setConsentTscsYn(String consentTscsYn) {
		this.consentTscsYn = consentTscsYn;
	}
	public String getConsentReceiveInfoYn() {
		return consentReceiveInfoYn;
	}
	public void setConsentReceiveInfoYn(String consentReceiveInfoYn) {
		this.consentReceiveInfoYn = consentReceiveInfoYn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}