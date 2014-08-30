package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.FaqInfo;

public class SAFaq implements Bean, Entity {
	
	private String FAQ_NO;
	private String A_SBJT;
	private String A_CNTT;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String Q_SBJT;
	private String Q_CNTT;	

	
	public SAFaq(){
		
	}
	
	public SAFaq(FaqInfo faq){
		Q_CNTT=faq.getQuestion(); 
		A_CNTT=faq.getAnswer();
		FAQ_NO=faq.getFaqNo();
	}
	
	public String getFAQ_NO() {
		return FAQ_NO;
	}


	public void setFAQ_NO(String fAQ_NO) {
		FAQ_NO = fAQ_NO;
	}


	public String getA_SBJT() {
		return A_SBJT;
	}


	public void setA_SBJT(String a_SBJT) {
		A_SBJT = a_SBJT;
	}


	public String getA_CNTT() {
		return A_CNTT;
	}


	public void setA_CNTT(String a_CNTT) {
		A_CNTT = a_CNTT;
	}


	public Date getREG_DTT() {
		return REG_DTT;
	}


	public void setREG_DTT(Date rEG_DTT) {
		REG_DTT = rEG_DTT;
	}


	public Date getUPD_DTT() {
		return UPD_DTT;
	}


	public void setUPD_DTT(Date uPD_DTT) {
		UPD_DTT = uPD_DTT;
	}


	public String getREG_ID() {
		return REG_ID;
	}


	public void setREG_ID(String rEG_ID) {
		REG_ID = rEG_ID;
	}


	public String getUPD_ID() {
		return UPD_ID;
	}


	public void setUPD_ID(String uPD_ID) {
		UPD_ID = uPD_ID;
	}


	public String getQ_SBJT() {
		return Q_SBJT;
	}


	public void setQ_SBJT(String q_SBJT) {
		Q_SBJT = q_SBJT;
	}


	public String getQ_CNTT() {
		return Q_CNTT;
	}


	public void setQ_CNTT(String q_CNTT) {
		Q_CNTT = q_CNTT;
	}


	@Override
	public String getEntityName() {
		return "faq";
	}
	
}
