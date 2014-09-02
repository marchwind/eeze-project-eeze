package com.kobaco.smartad.model.data;

import java.util.Date;

import com.kobaco.smartad.model.Bean;
import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.service.NotificationInfo;

public class SAArchive implements Bean , Entity{

	private String ARCV_NO;
	private String ARCV_SBJT;
	private String ARCV_CNTT;
	private int VW_CNT;
	private String ATT_FL_PTH;
	private String ATT_FL_NM;
	private Date REG_DTT;
	private Date UPD_DTT;
	private String REG_ID;
	private String UPD_ID;
	
	public SAArchive(){
		
	}

	
	public String getARCV_NO() {
		return ARCV_NO;
	}


	public void setARCV_NO(String aRCV_NO) {
		ARCV_NO = aRCV_NO;
	}


	public String getARCV_SBJT() {
		return ARCV_SBJT;
	}


	public void setARCV_SBJT(String aRCV_SBJT) {
		ARCV_SBJT = aRCV_SBJT;
	}


	public String getARCV_CNTT() {
		return ARCV_CNTT;
	}


	public void setARCV_CNTT(String aRCV_CNTT) {
		ARCV_CNTT = aRCV_CNTT;
	}


	public int getVW_CNT() {
		return VW_CNT;
	}


	public void setVW_CNT(int vW_CNT) {
		VW_CNT = vW_CNT;
	}


	public String getATT_FL_PTH() {
		return ATT_FL_PTH;
	}


	public void setATT_FL_PTH(String aTT_FL_PATH) {
		ATT_FL_PTH = aTT_FL_PATH;
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


	public String getATT_FL_NM() {
		return ATT_FL_NM;
	}


	public void setATT_FL_NM(String aTT_FL_NM) {
		ATT_FL_NM = aTT_FL_NM;
	}


	@Override
	public String getEntityName() {
		return "archive";
	}	
}
