package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAUserEmailCertification;

public class UserEmailCert implements ValueObject {

	private String emailCertKey;
	private String emailCertYn;
	private Date emailCertDate;
	private String userEmail;
	private String userNo;
	
	public UserEmailCert(SAUserEmailCertification sa) {
		this.emailCertDate = sa.getEML_CRTF_DTT();
		this.userEmail = sa.getUSR_EML();
		this.userNo = sa.getUSR_NO();
		this.emailCertKey = sa.getEML_CRTK();
		this.emailCertYn = sa.getEML_CRTF_YN();
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
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
}
