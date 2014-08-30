package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SANotification;

public class NotificationInfo implements ValueObject {
	private String notiNo;
	private String notiSubject;
	private String notiContent;
	private Date notiRegisteDate;
	private Date notiUpdateDate;
	private int notiConfirmCount;
	
	public NotificationInfo(){
		
	}
	public NotificationInfo(SANotification noti){
		
		this.notiNo = noti.getNTFC_NO();
		this.notiContent = noti.getNTFC_CNTT();
		this.notiSubject = noti.getNTFC_SBJT();
		this.notiRegisteDate= noti.getREG_DTT();
	}
	public String getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}
	public String getNotiSubject() {
		return notiSubject;
	}
	public void setNotiSubject(String notiSubject) {
		this.notiSubject = notiSubject;
	}
	public String getNotiContent() {
		return notiContent;
	}
	public void setNotiContent(String notiContent) {
		this.notiContent = notiContent;
	}
	public Date getNotiRegisteDate() {
		return notiRegisteDate;
	}
	public void setNotiRegisteDate(Date notiRegisteDate) {
		this.notiRegisteDate = notiRegisteDate;
	}
	public Date getNotiUpdateDate() {
		return notiUpdateDate;
	}
	public void setNotiUpdateDate(Date notiUpdateDate) {
		this.notiUpdateDate = notiUpdateDate;
	}
	public int getNotiConfirmCount() {
		return notiConfirmCount;
	}
	public void setNotiConfirmCount(int notiConfirmCount) {
		this.notiConfirmCount = notiConfirmCount;
	}

}
