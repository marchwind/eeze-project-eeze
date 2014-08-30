package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;

public class UserEmailCert implements ValueObject {
	private UserInfo user;
	private String emailCertKey;
	private String emailCertYn;
	private Date emailCertDate;
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public String getEmailCertKey() {
		return emailCertKey;
	}
	public void setEmailCertKey(String emailCertKey) {
		this.emailCertKey = emailCertKey;
	}
	public String getEmailCertYn() {
		return emailCertYn;
	}
	public void setEmailCertYn(String emailCertYn) {
		this.emailCertYn = emailCertYn;
	}
	public Date getEmailCertDate() {
		return emailCertDate;
	}
	public void setEmailCertDate(Date emailCertDate) {
		this.emailCertDate = emailCertDate;
	}
	
}
