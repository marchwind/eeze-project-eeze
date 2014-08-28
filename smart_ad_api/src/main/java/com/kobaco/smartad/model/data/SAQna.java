package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.QnaInfo;

public class SAQna extends SAPMCManager implements Entity {
	
	private String QNA_NO;
	private String Q_USR_NO;
	private String A_MNGR_NO;
	private String Q_SBJT;
	private String Q_CNTT;
	private String Q_ATC_FL_SPTH;
	private String A_SBJT;
	private String A_CNTT;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String Q_ATC_FL_NM;
	private Date Q_DTT;
	private Date A_DTT;
	private String Q_NM;
	private String Q_PHN;
	private String Q_EML;
	
	public SAQna(){
		
	}
	public SAQna(QnaInfo qna){
		this.QNA_NO = qna.getQnaNo();
		this.A_CNTT =qna.getAnswerContent();
		this.A_SBJT=qna.getAnswerSubject();
		
	}
	public String getQ_NM() {
		return Q_NM;
	}

	public void setQ_NM(String q_NM) {
		Q_NM = q_NM;
	}

	public String getQ_PHN() {
		return Q_PHN;
	}

	public void setQ_PHN(String q_PHN) {
		Q_PHN = q_PHN;
	}

	public String getQ_EML() {
		return Q_EML;
	}

	public void setQ_EML(String q_EML) {
		Q_EML = q_EML;
	}

	public String getQNA_NO() {
		return QNA_NO;
	}

	public void setQNA_NO(String qNA_NO) {
		QNA_NO = qNA_NO;
	}

	public String getQ_USR_NO() {
		return Q_USR_NO;
	}

	public void setQ_USR_NO(String q_USR_NO) {
		Q_USR_NO = q_USR_NO;
	}

	public String getA_MNGR_NO() {
		return A_MNGR_NO;
	}

	public void setA_MNGR_NO(String a_MNGR_NO) {
		A_MNGR_NO = a_MNGR_NO;
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

	public String getQ_ATC_FL_SPTH() {
		return Q_ATC_FL_SPTH;
	}

	public void setQ_ATC_FL_SPTH(String q_ATC_FL_SPTH) {
		Q_ATC_FL_SPTH = q_ATC_FL_SPTH;
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

	public String getQ_ATC_FL_NM() {
		return Q_ATC_FL_NM;
	}

	public void setQ_ATC_FL_NM(String q_ATC_FL_NM) {
		Q_ATC_FL_NM = q_ATC_FL_NM;
	}

	public Date getQ_DTT() {
		return Q_DTT;
	}

	public void setQ_DTT(Date q_DTT) {
		Q_DTT = q_DTT;
	}

	public Date getA_DTT() {
		return A_DTT;
	}

	public void setA_DTT(Date a_DTT) {
		A_DTT = a_DTT;
	}

	@Override
	public String getEntityName() {
		return "qna";
	}

}
