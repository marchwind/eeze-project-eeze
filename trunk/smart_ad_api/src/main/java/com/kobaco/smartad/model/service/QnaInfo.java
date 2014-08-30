package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAQna;

public class QnaInfo implements ValueObject {

	private String qnaNo;
	private String querySubject;
	private String queryContent;
	private String answerSubject;
	private String answerContent;
	private String answerManagerNo;
	private String answerManagerId;
	private String queryName;
	private String queryPhone;
	private String queryEmail;
	private String queryAttachedFile;
	private Date queryDate;
	private Date anwserDate;
	private UserInfo user;
	private ManagerInfo manager;
	
	public QnaInfo(){
		
	}
	public QnaInfo(SAQna qna){
		this.qnaNo=qna.getQNA_NO();
		this.querySubject=qna.getQ_SBJT();
		this.queryContent=qna.getQ_CNTT();
		this.queryEmail=qna.getQ_EML();
		this.queryAttachedFile=qna.getQ_ATC_FL_NM();
		this.answerSubject=qna.getA_SBJT();
		this.answerContent =qna.getA_CNTT();
		this.queryDate=qna.getQ_DTT();
		this.anwserDate=qna.getA_DTT();
	}
	
	public String getQueryAttachedFile() {
		return queryAttachedFile;
	}
	public void setQueryAttachedFile(String queryAttachedFile) {
		this.queryAttachedFile = queryAttachedFile;
	}
	public String getAnswerManagerNo() {
		return answerManagerNo;
	}
	public void setAnswerManagerNo(String answerManagerNo) {
		this.answerManagerNo = answerManagerNo;
	}
	public String getAnswerManagerId() {
		return answerManagerId;
	}
	public void setAnswerManagerId(String answerManagerId) {
		this.answerManagerId = answerManagerId;
	}
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getQuerySubject() {
		return querySubject;
	}
	public void setQuerySubject(String querySubject) {
		this.querySubject = querySubject;
	}
	public String getQueryContent() {
		return queryContent;
	}
	public void setQueryContent(String queryContent) {
		this.queryContent = queryContent;
	}
	public String getAnswerSubject() {
		return answerSubject;
	}
	public void setAnswerSubject(String answerSubject) {
		this.answerSubject = answerSubject;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnswerYn() {
		return (this.getAnwserDate()==null?"N":"Y");
	}
	public String getQueryName() {
		return queryName;
	}
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	public String getQueryPhone() {
		return queryPhone;
	}
	public void setQueryPhone(String queryPhone) {
		this.queryPhone = queryPhone;
	}
	public String getQueryEmail() {
		return queryEmail;
	}
	public void setQueryEmail(String queryEmail) {
		this.queryEmail = queryEmail;
	}
	public Date getQueryDate() {
		return queryDate;
	}
	public void setQueryDate(Date queryDate) {
		this.queryDate = queryDate;
	}
	public Date getAnwserDate() {
		return anwserDate;
	}
	public void setAnwserDate(Date anwserDate) {
		this.anwserDate = anwserDate;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public ManagerInfo getManager() {
		return manager;
	}
	public void setManager(ManagerInfo manager) {
		this.manager = manager;
	}
	

}
