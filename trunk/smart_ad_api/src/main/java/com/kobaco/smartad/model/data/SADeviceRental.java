package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.EquipInfo;
import com.kobaco.smartad.model.service.PmcMnagerInfo;
import com.kobaco.smartad.model.service.UserInfo;

public class SADeviceRental /* extends SAEquipementCheck */ implements Entity ,Bean {

	private String EQPM_NO;
	private String USR_NO;
	private String DEV_RNT_NO;
	private Date RTN_DTT;
	private Date RNT_DTT;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	public String UPD_ID;
	
	private String USR_ID;
	private String USR_NM;
	private String CHK_CNTT;
	private String ENTP_NM;
	
	public SADeviceRental() {
		// TODO Auto-generated constructor stub
	}
	
	public SADeviceRental(EquipInfo equipInfo, PmcMnagerInfo info, UserInfo user) {
		// TODO Auto-generated constructor stub
		this.EQPM_NO=equipInfo.getEquipNo();
		this.REG_ID = info.getManagerId();
		this.UPD_ID = info.getManagerId();
		this.USR_NO= user.getUserNo();
		this.RNT_DTT=new Date();
		this.DEV_RNT_NO = equipInfo.getEquipRentalNo();
		this.RTN_DTT = new Date();		
	}

	public Date getRTN_DTT() {
		return RTN_DTT;
	}

	public void setRTN_DTT(Date rTN_DTT) {
		RTN_DTT = rTN_DTT;
	}

	public String getEQPM_NO() {
		return EQPM_NO;
	}

	public void setEQPM_NO(String eQPM_NO) {
		EQPM_NO = eQPM_NO;
	}

	public String getUSR_NO() {
		return USR_NO;
	}

	public void setUSR_NO(String uSR_NO) {
		USR_NO = uSR_NO;
	}

	public Date getRNT_DTT() {
		return RNT_DTT;
	}

	public void setRNT_DTT(Date rNT_DTT) {
		RNT_DTT = rNT_DTT;
	}

	public String getDEV_RNT_NO() {
		return DEV_RNT_NO;
	}

	public void setDEV_RNT_NO(String dEV_RNT_NO) {
		DEV_RNT_NO = dEV_RNT_NO;
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

	public String getUSR_ID() {
		return USR_ID;
	}

	public void setUSR_ID(String uSR_ID) {
		USR_ID = uSR_ID;
	}

	public String getUSR_NM() {
		return USR_NM;
	}

	public void setUSR_NM(String uSR_NM) {
		USR_NM = uSR_NM;
	}

	public String getCHK_CNTT() {
		return CHK_CNTT;
	}

	public void setCHK_CNTT(String cHK_CNTT) {
		CHK_CNTT = cHK_CNTT;
	}

	public String getENTP_NM() {
		return ENTP_NM;
	}

	public void setENTP_NM(String eNTP_NM) {
		ENTP_NM = eNTP_NM;
	}

	@Override
	public String getEntityName() {
		return "deviceRental";
	}

}
