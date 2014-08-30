package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.EquipCheckInfo;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.utils.CommonCode;

public class SAEquipementCheck /* extends SAUser */ implements Entity {
	private String VST_NO;
	private String EQPM_CHK_NO;
	private String DEV_RNT_NO;
	private String CHK_TP_CD;
	private String CHKR;
	private Date CHK_DTT;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	private String CHK_CNTT;
	private String EQPM_NO;

	private String ENTP_NO;
	
	public SAEquipementCheck(){
		
	}
	public SAEquipementCheck(EquipInfo info,PmcMnagerInfo mg){
		this.CHK_TP_CD  = CommonCode.PmcReserveCode.Check_R02;
		this.CHKR       = mg.getManagerId();
		this.CHK_DTT    = new Date();
		this.REG_ID     = mg.getManagerId();
		this.UPD_ID     = mg.getManagerId();
		this.CHK_CNTT   = info.getCheckContent();
		this.EQPM_NO    = info.getEquipNo();
		this.DEV_RNT_NO = info.getEquipRentalNo();
	}
	
	public SAEquipementCheck(EquipCheckInfo info,PmcMnagerInfo mg){
		this.CHK_TP_CD  = CommonCode.PmcReserveCode.Check_R02;
		this.CHKR       = mg.getManagerId();
		this.CHK_DTT    = new Date();
		this.REG_ID     = mg.getManagerId();
		this.UPD_ID     = mg.getManagerId();
		this.CHK_CNTT   = info.getCheckContent();
		this.EQPM_NO    = info.getEquipNo();
	}
	
	public String getDEV_RNT_NO() {
		return DEV_RNT_NO;
	}
	public void setDEV_RNT_NO(String dEV_RNT_NO) {
		DEV_RNT_NO = dEV_RNT_NO;
	}
	public String getVST_NO() {
		return VST_NO;
	}


	public void setVST_NO(String vST_NO) {
		VST_NO = vST_NO;
	}


	public String getEQPM_CHK_NO() {
		return EQPM_CHK_NO;
	}


	public void setEQPM_CHK_NO(String eQPM_CHK_NO) {
		EQPM_CHK_NO = eQPM_CHK_NO;
	}


	public String getCHK_TP_CD() {
		return CHK_TP_CD;
	}


	public void setCHK_TP_CD(String cHK_TP_CD) {
		CHK_TP_CD = cHK_TP_CD;
	}


	public String getCHKR() {
		return CHKR;
	}


	public void setCHKR(String cHKR) {
		CHKR = cHKR;
	}


	public Date getCHK_DTT() {
		return CHK_DTT;
	}


	public void setCHK_DTT(Date cHK_DTT) {
		CHK_DTT = cHK_DTT;
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


	public String getCHK_CNTT() {
		return CHK_CNTT;
	}


	public void setCHK_CNTT(String cHK_CNTT) {
		CHK_CNTT = cHK_CNTT;
	}


	public String getEQPM_NO() {
		return EQPM_NO;
	}


	public void setEQPM_NO(String eQPM_NO) {
		EQPM_NO = eQPM_NO;
	}
	
	public String getENTP_NO() {
		return ENTP_NO;
	}
	public void setENTP_NO(String eNTP_NO) {
		ENTP_NO = eNTP_NO;
	}
	@Override
	public String getEntityName() {
		return "equipementCheck";
	}
	
}
