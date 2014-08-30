package com.kobaco.smartad.model.service;

import java.util.Map;

import com.kobaco.smartad.model.ValueObject;

public class MailSend implements ValueObject{

	private String SendFrom;
	private String SendTo;
	private Map<String,Object> SendMessage;
	private String SendSubject;
	private String SendPath;	
	
	public MailSend(String sendFrom, String sendTo,
			Map<String, Object> sendMessage, String sendSubject, String sendPath) {
		super();
		SendFrom = sendFrom;
		SendTo = sendTo;
		SendMessage = sendMessage;
		SendSubject = sendSubject;
		SendPath = sendPath;
	}
	public String getSendFrom() {
		return SendFrom;
	}
	public void setSendFrom(String sendFrom) {
		SendFrom = sendFrom;
	}
	public String getSendTo() {
		return SendTo;
	}
	public void setSendTo(String sendTo) {
		SendTo = sendTo;
	}
	
	public Map<String, Object> getSendMessage() {
		return SendMessage;
	}
	public void setSendMessage(Map<String, Object> sendMessage) {
		SendMessage = sendMessage;
	}
	public String getSendSubject() {
		return SendSubject;
	}
	public void setSendSubject(String sendSubject) {
		SendSubject = sendSubject;
	}
	public String getSendPath() {
		return SendPath;
	}
	public void setSendPath(String sendPath) {
		SendPath = sendPath;
	}
	

	
}
