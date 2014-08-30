package com.kobaco.smartad.model.service;

import java.util.Date;

import com.kobaco.smartad.model.ValueObject;
import com.kobaco.smartad.model.data.SADeviceRental;
import com.kobaco.smartad.model.data.SAEquipement;
import com.kobaco.smartad.model.data.SAFacility;

public class EquipInfo implements ValueObject{
	
	private String equipNo;
	private String equipName;
	private String equipWorkingYn;
	private String equipModel;
	private String equipIp;
	private String equipState;
	private String equipPowerState;
	private String equipOs;
	private String equipMac;
	private String facilityNo;
	private String facilityName;
	private String equipSpec;
	private String equipMemo;
	private String equipExplain;
	private String equipImage1Name;
	private String equipImaage2Name;	
	private String equipRentalNo;
	private String username;
	private String userId;
	private Date rentalDate;
	private Date returnDate;
	private Date equipLastCheckDate;
	private String checkContent;
	private String enterpriseName;
	private String equipType;
	
	public EquipInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public EquipInfo(String equipNo ) {
		this.equipNo = equipNo;
	}
	
	public EquipInfo(SAEquipement eq) {
		// TODO Auto-generated constructor stub
		this.equipNo = eq.getEQPM_NO();
		this.equipRentalNo = eq.getLST_DEV_RNT_NO();
		this.equipIp = eq.getEQPM_IP();
		this.equipMac = eq.getEQPM_MAC();
		this.equipType = eq.getEQPM_TP();
		this.equipName = eq.getEQPM_NM();
		this.equipModel = eq.getMDL_NM();
		this.equipOs = eq.getEQPM_OS();
		this.equipState = eq.getEQPM_STTS_CD();
		this.facilityNo = eq.getFCLT_MGMT_NO();
		this.facilityName = eq.getFCLT_NM();
		this.equipLastCheckDate = eq.getLST_CHK_DTT();
		this.equipSpec = eq.getEQPM_SPC();
		this.equipMemo = eq.getEQPM_MEMO();
		this.equipExplain = eq.getEQPM_EXPL();
		this.equipWorkingYn = (eq.getEQPM_ALV_CNT()>0?"Y":"N");
		this.equipPowerState = eq.getEQPM_PWR_STTS_CD();
	}
	public EquipInfo(SADeviceRental renResult) {
		// TODO Auto-generated constructor stub
		this.equipNo        = renResult.getEQPM_NO();
		this.equipRentalNo  = renResult.getDEV_RNT_NO();
		this.userId         = renResult.getUSR_ID();
		this.username       = renResult.getUSR_NM();
		this.rentalDate     = renResult.getRNT_DTT();
		this.returnDate     = renResult.getRTN_DTT();
		this.checkContent   = renResult.getCHK_CNTT();
		this.enterpriseName = renResult.getENTP_NM();		
	}
	
	public EquipInfo(SAFacility sa) {
		this.equipNo     = sa.getEQPM_NO();
		this.equipName   = sa.getEQPM_NM();
		this.equipType   = sa.getEQPM_TP();
		this.equipModel  = sa.getMDL_NM();
		this.equipIp     = sa.getEQPM_IP();
		this.equipState  = sa.getEQPM_STTS_CD();
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getEquipType() {
		return equipType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public Date getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEquipRentalNo() {
		return equipRentalNo;
	}

	public void setEquipRentalNo(String equipRentalNo) {
		this.equipRentalNo = equipRentalNo;
	}

	public String getEquipMac() {
		return equipMac;
	}
	public void setEquipMac(String equipMac) {
		this.equipMac = equipMac;
	}
	public String getFacilityNo() {
		return facilityNo;
	}
	public void setFacilityNo(String facilityNo) {
		this.facilityNo = facilityNo;
	}
	public String getEquipSpec() {
		return equipSpec;
	}
	public void setEquipSpec(String equipSpec) {
		this.equipSpec = equipSpec;
	}
	public String getEquipMemo() {
		return equipMemo;
	}
	public void setEquipMemo(String equipMemo) {
		this.equipMemo = equipMemo;
	}
	public String getEquipImage1Name() {
		return equipImage1Name;
	}
	public void setEquipImage1Name(String equipImage1Name) {
		this.equipImage1Name = equipImage1Name;
	}
	public String getEquipImaage2Name() {
		return equipImaage2Name;
	}
	public void setEquipImaage2Name(String equipImaage2Name) {
		this.equipImaage2Name = equipImaage2Name;
	}
	public String getEquipOs() {
		return equipOs;
	}
	public void setEquipOs(String equipOs) {
		this.equipOs = equipOs;
	}
	public String getEquipNo() {
		return equipNo;
	}
	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}
	public String getEquipName() {
		return equipName;
	}
	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getEquipWorkingYn() {
		return equipWorkingYn;
	}
	public void setEquipWorkingYn(String equipWorkingYn) {
		this.equipWorkingYn = equipWorkingYn;
	}
	public String getEquipModel() {
		return equipModel;
	}
	public void setEquipModel(String equipModel) {
		this.equipModel = equipModel;
	}
	public String getEquipIp() {
		return equipIp;
	}
	public void setEquipIp(String equipIp) {
		this.equipIp = equipIp;
	}
	public String getEquipState() {
		return equipState;
	}
	public void setEquipState(String equipState) {
		this.equipState = equipState;
	}

	public Date getEquipLastCheckDate() {
		return equipLastCheckDate;
	}

	public void setEquipLastCheckDate(Date equipLastCheckDate) {
		this.equipLastCheckDate = equipLastCheckDate;
	}

	public String getEquipExplain() {
		return equipExplain;
	}

	public void setEquipExplain(String equipExplain) {
		this.equipExplain = equipExplain;
	}

	public String getEquipPowerState() {
		return equipPowerState;
	}

	public void setEquipPowerState(String equipPowerState) {
		this.equipPowerState = equipPowerState;
	}	

}
