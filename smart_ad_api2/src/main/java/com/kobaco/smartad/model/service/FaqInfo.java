package com.kobaco.smartad.model.service;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SAFaq;

public class FaqInfo implements ValueObject {
	private String faqNo;
	private String question;
	private String answer;
	public FaqInfo(){
		
	}
	public FaqInfo(SAFaq faq){
		this.faqNo=faq.getFAQ_NO();
		this.question= faq.getQ_CNTT();
		this.answer = faq.getA_CNTT();
	}
	
	public String getFaqNo() {
		return faqNo;
	}
	public void setFaqNo(String faqNo) {
		this.faqNo = faqNo;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}
