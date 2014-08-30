package com.kobaco.smartab.dao;

import java.util.Date;

import com.kobaco.smartad.model.data.SACMCode;
import com.kobaco.smartad.model.data.SACMCodeClass;
import com.kobaco.smartad.model.data.SADevice;
import com.kobaco.smartad.model.data.SADeviceRental;
import com.kobaco.smartad.model.data.SADeviceReturn;
import com.kobaco.smartad.model.data.SAEnterprise;
import com.kobaco.smartad.model.data.SAEquipement;
import com.kobaco.smartad.model.data.SAEquipementCheck;
import com.kobaco.smartad.model.data.SAEquipementConstitute;
import com.kobaco.smartad.model.data.SAEquipementProcessHistory;
import com.kobaco.smartad.model.data.SAEquipementSoftware;
import com.kobaco.smartad.model.data.SAEquipementStateHistory;
import com.kobaco.smartad.model.data.SAEquipementStateLog;
import com.kobaco.smartad.model.data.SAFacility;
import com.kobaco.smartad.model.data.SAFacilityCheck;
import com.kobaco.smartad.model.data.SAFacilityIssue;
import com.kobaco.smartad.model.data.SAFacilityReserve;
import com.kobaco.smartad.model.data.SAFacilityReserveDate;
import com.kobaco.smartad.model.data.SAFacilityVisitHistory;
import com.kobaco.smartad.model.data.SAFacilityVisitor;
import com.kobaco.smartad.model.data.SAFaq;
import com.kobaco.smartad.model.data.SAImage;
import com.kobaco.smartad.model.data.SANfcTag;
import com.kobaco.smartad.model.data.SANotification;
import com.kobaco.smartad.model.data.SANotificationConfirm;
import com.kobaco.smartad.model.data.SAOperationTscs;
import com.kobaco.smartad.model.data.SAPMCManager;
import com.kobaco.smartad.model.data.SAPMCPermission;
import com.kobaco.smartad.model.data.SAPMCScreenManagement;
import com.kobaco.smartad.model.data.SAPMCScreenPermission;
import com.kobaco.smartad.model.data.SAQna;
import com.kobaco.smartad.model.data.SASoftware;
import com.kobaco.smartad.model.data.SAUser;
import com.kobaco.smartad.model.data.SAUserEmailCertification;
import com.kobaco.smartad.model.data.SAUserStateHistory;
import com.kobaco.smartad.model.data.SAUserTscsConsent;


public class TestUtils {
	
	static int i =1;

	public static SAEnterprise initEnterprise() {
		return new SAEnterprise() {
			{
				setBRN("bRN");
				setBSTP_CD("bSTP");
				setENTP_ADDR("eNTP_ADDR");
				setENTP_EML("eNTP_EML");
				setENTP_NM("eNTP_NM");
				setENTP_NM("eNTP_NM");
				setFAX("fAX");
				setPHN("pHN");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
				
				
				
			}
		};
	}

	
	public static SAUser initUser(final String entNo) {
		return new SAUser() {
			{
				setCPHN("cPHN");
				setENTP_NO(entNo);
				setJOB_CD("jOBCD");
				setPHN("pHN");
				setPST_CD("pST_CD");
				setREG_ID("rEG_ID");
				setUPD_ID("uPD_ID");
				setUSR_EML("uSR_EML");
				setUSR_ID("uSR_ID");
				setUSR_NM("uSR_NM");
				setUSR_PWD("uSR_PWD");
				setUSR_STTS_CD("uSCD");
			}
			
		};
	}
	/*
	public static SANotification initNotification(){
		return new SANotification(){
			{
				setNotificationSubject("notificationSubject");
				setNotificationContent("notificationContent");
				setRegisterId("suiiiii");
				setUpdaterId("bhna");
			}
		};
	}
	*/
	public static SAFaq initFaq(){
		return new SAFaq(){
			{
				setA_CNTT("a_CNTT");
				setA_SBJT("a_SBJT");
				setQ_CNTT("q_CNTT");
				setQ_SBJT("q_SBJT");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
				
			}
		};
	}

	public static SANotificationConfirm initNotiConfirm(final String userNo,final String notificationNo) {
		return new SANotificationConfirm() {
			{
				setNTFC_NO(notificationNo);		
				setUSR_NO(userNo);
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
				
						
			}
			
		};
	}
	

	public static SAFacility initFacility(final String nffTag) {
		return new SAFacility() {
			{
				setFCLT_EXPL("fCLT_EXPL");
				setFCLT_LOC("fCLT_LOC");
				setFCLT_NM("fCLT_NM");
				setFCLT_STTS_CD("fCD");
				setNFC_TAG_ID(nffTag);
				setSTTN("123");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
			}
		};
	}
	
	public static SAFacilityIssue initFacilityIssue(final String facilitymgmntNo) {
		return new SAFacilityIssue() {
			{
				setED_DTT(new Date());
				setFCLT_MGMT_NO(facilitymgmntNo);
				setFCLT_OPRT_YN("Y");
				setISS_CNTT("iSS_CNTT");
				setST_DTT(new Date());
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
			}
		};
	}
	public static SAFacilityCheck initFacilityCheck(final String facilitymgmntNo,final String vstNo) {
		return new SAFacilityCheck() {
			{
				
				setFCLT_MGMT_NO(facilitymgmntNo);
				setCHK_CNTT("cHK_CNTT");
//				setCHK_DTT("cHK_DTT");
				setCHK_TP("cHK_TP");
				setCHKR("cHKR");
//				setVST_NO(vstNo);
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
			}
		};
	}
	public static SAFacilityReserve initReservation(final String facilitymgmntNo,final String userNo) {
		return new SAFacilityReserve() {
			{
				setCNCL_DTT(new Date());
				setENTP_NM("eNTP_NM");
				setCNCL_YN("Y");
				setFCLT_MGMT_NO(facilitymgmntNo);
				setRSRV_VSTN(10);
				setUSR_NO(userNo);
				setWRK_CNTT("wRK_CNTT");
				setWRKR_NM("wRKR_NM");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
				
			}
		};
	}
	public static SAQna initQna(final String mngrNo,final String userNo) {
		return new SAQna() {
			{
				setA_CNTT("a_CNTT");
				setA_DTT(new Date());
				setA_MNGR_NO(mngrNo);
				setA_SBJT("a_SBJT");
				setQ_ATC_FL_NM("q_ATC_FL_NM");
				setQ_ATC_FL_SPTH("q_ATC_FL_SPTH");
				setQ_CNTT("q_CNTT");
				setQ_DTT(new Date());
				setQ_SBJT("q_SBJT");
				setQ_USR_NO(userNo);
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
				
			}
		};
	}
	
	public static SAFacilityVisitor initVisitor(final String reservationNo) {
		return new SAFacilityVisitor() {
			{
				setVST_NO(reservationNo);
				setVSTR_CNT(2);
				setVSTR_NM("vSTR_NM");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
			}
		};
	}
	
	public static SAPMCPermission initPMCPermission(){
		return new SAPMCPermission(){
			{
				setMNGR_PRMS_NM("mNGR_PRMS_NM");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
			}
		};
	}

	public static SAPMCManager initPMCManager(final String mNGR_PRMS_NO) {
		return new SAPMCManager() {
			{
				setBLT("bLT");
				setCNFM_DTT(new Date());
				setCNFM_YN("Y");
				setCNFR_ID("cNFR_ID");
				setCPHN("cPHN");
				setMNGR_EML("mNGR_EML");
				setMNGR_ID("mNGR_ID");
				setMNGR_NM("mNGR_NM");
				setMNGR_PRMS_NO(mNGR_PRMS_NO);
				setPHN("pHN");
				setPST_CD("pSTCD");
				setPWD("pWD");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
			}
		};
	}
	
	public static SAPMCScreenManagement initPMCScreenManagement(){
		return new SAPMCScreenManagement(){
			{
				setSCRN_NM("sCRN_NM");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
			}
		};
	}
	
	public static SAPMCScreenPermission initPMCScreenPermission(final String pMC_SCRN_NO,final String mNGR_PRMS_NO) {
		return new SAPMCScreenPermission() {
			{
				setPMC_SCRN_NO(pMC_SCRN_NO);
				setMNGR_PRMS_NO(mNGR_PRMS_NO);
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
			}
			
		};
	}
		
	public static SAUserEmailCertification initUserEmailCrtf(final String userNo) {
		return new SAUserEmailCertification() {
			{
				setEML_CRTF_DTT(new Date());
				setEML_CRTF_YN("Y");
				setUSR_NO(userNo);
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SAUserStateHistory initStateHistory(final String userNo) {
		return new SAUserStateHistory() {
			{
				setMDF_CNTT("mDF_CNTT");
				setMDF_TP_CD("CD");
				setUSR_NO(userNo);
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SAFacilityVisitHistory initVisitorHistory(final String reserveNo,final String reserveDtNo) {
		return new SAFacilityVisitHistory() {
			{
				setCHKIN_CNFMR("cHKIN_CNFMR");
				setCHKIN_DTT(new Date());
				setCHKOUT_CNFMR("cHKOUT_CNFMR");
				setCHKOUT_DTT(new Date());
				setRSRV_NO(reserveNo);
				setRSRV_DTL_NO(reserveDtNo);
				setVST_DTT(new Date());
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SAFacilityReserveDate initReserveDate(final String reserveNo) {
		return new SAFacilityReserveDate() {
			{
				setRSRV_DT(new Date());
				setTZ_TP_CD("CD");
				setRSRV_NO(reserveNo);			
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SAEquipementSoftware initESoft(final String eqpmNo ,final String softwareNo) {
		return new SAEquipementSoftware() {
			{
				setEQPM_NO(eqpmNo);
				setSW_MGMT_NO(softwareNo);		
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SAEquipementProcessHistory initEPHistory(final String eqpmNo ,final String softwareNo) {
		return new SAEquipementProcessHistory() {
			{
				setEQPM_NO(eqpmNo);
				setSW_MGMT_NO(softwareNo);		
				setGTH_DTT(new Date());
				setHIST_CNTT("hIST_CNTT");
				
			}
		};
	}
	public static SAEquipementStateHistory initESHistory(final String eqpmNo ,final String eqpmCnstNo) {
		return new SAEquipementStateHistory() {
			{
				setEQPM_NO(eqpmNo);
				setEQPM_CNST_NO(eqpmCnstNo);
				setGTH_DTT(new Date());
//				setHIST_CNTT("hIST_CNTT");
				
			}
		};
	}
	public static SAEquipementCheck initECheck(final String eqpmNo ,final String vstNo) {
		return new SAEquipementCheck() {
			{
				setEQPM_NO(eqpmNo);
				setVST_NO(vstNo);
				setCHK_DTT(new Date());
				setCHK_CNTT("cHK_CNTT");
				setCHK_TP_CD("00");
				setCHKR("cHKR");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SAEquipementStateLog initEStateLog(final String eqpNo) {
		return new SAEquipementStateLog() {
			{
				setEQPM_NO(eqpNo);
				setLOG_CNTT("lOG_CNTT");
				setLOG_GTH_DTT(new Date());
				setLOG_TP_CD("lOGCD");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SAEquipementConstitute initEConstitute(final String eqpNo) {
		return new SAEquipementConstitute() {
			{
				setEQPM_NO(eqpNo);
				setMFTR("mFTR");
				setPART_NM("pART_NM");
				setPART_SPC("pART_SPC");
				setPART_TP("000");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");	
				
			}
		};
	}
	public static SACMCodeClass initCMCodeClass() {
		return new SACMCodeClass() {
			{
				setCMCD_CLS_EXPL("This is ..");
				setCMCD_CLS_NM("name");
				setREG_ID("registerId");				
				setUPD_ID("updaterId");
				
			}
		};
	}
	public static SACMCode initCMCode(final String cmcdCls) {
		return new SACMCode() {
			{
				setCMCD_EXPL("this is ex.");
				setCMCD_NM("cmNAme");
				setREG_ID("system");
				setUPD_ID("system");
				setCMCD_CLS(cmcdCls);
			}
		};
	}
	public static SADevice initDevice() {
		return new SADevice() {
			{
				setDEV_NM("dEV_NM");
				setDEV_STTS_CD("dEV_S");				
				setHGT_RESO(i);
				setMDL_NM("mDL_NM");
				setMFTR("mFTR");
				setOS("oS");
				setWDT_RESO(i);
				setREG_ID("system");
				setUPD_ID("system");
			}
		};
	}
	public static SADeviceReturn initDeviceRental(final String devRetNo) {
		return new SADeviceReturn() {
			{
				setDEV_RNT_NO(devRetNo);
				setRTN_DTT(new Date());
				setRTN_STTS_EXPL("rTN_STTS_EXPL");
				setREG_ID("system");
				setUPD_ID("system");
			}
		};
	}
	public static SADeviceRental initDeviceRental(final String facilityVstrNo , final String devNo,final String vstNo) {
		return new SADeviceRental() {
			{
//				setDEV_MGMT_NO(devNo);
//				setFCLT_VSTR_NO(facilityVstrNo);
				setRNT_DTT(new Date());
//				setVST_NO(vstNo);				
				setREG_ID("system");
				setUPD_ID("system");
			}
		};
	}
	public static SANotification initNtfc() {
		return new SANotification() {
			{
				setNTFC_CNTT("nTFC_CNTT");
				setNTFC_SBJT("nTFC_SBJT");
				setREG_ID("system");
				setUPD_ID("system");
			}
		};
	}
	public static SANfcTag initNfcTag() {
		return new SANfcTag() {
			{		
				setREG_ID("system");
				setUPD_ID("system");			
			}
		};
	}
	public static SAOperationTscs initOperationTscs() {
		return new SAOperationTscs() {
			{	
				setTSCS_CNTT("tSCS_CNTT");
				setTSCS_TP_CD("TP_CD");
				setREG_ID("system");
				setUPD_ID("system");			
			}
		};
	}
	public static SASoftware initSoftware() {
		return new SASoftware() {
			{	
				setSW_EXPL("sW_EXPL");
				setSW_MDL_NM("sW_MDL_NM");
				setSW_MDL_NM("sW_MDL_NM");
				setSW_NM("sW_NM");
				setSW_PRCS_NM("sW_PRCS_NM");
				setSW_TP_CD("CD");
				setREG_ID("system");
				setUPD_ID("system");			
			}
		};
	}
	
	public static SAUserTscsConsent initTscsConsent(final String TSCS_TP_CD, final String uSR_NO, final String CNST_YN) {
		return new SAUserTscsConsent() {
			{	
				 setMDF_DTT(new Date());
				 setCNST_DTT(new Date());
				 setCNST_YN(CNST_YN);
				 setUSR_NO(uSR_NO);
			}
		};
	}
	
	public static SAImage initImage(final String facilitymgmntNo,final String equipmentmgmtNo) {
		return new SAImage() {
			{			
				setEQPM_NO(equipmentmgmtNo);
				setFCLT_MGMT_NO(facilitymgmntNo);
				setIMG_NM("iMG_NM");
				setIMG_SPTH("iMG_SPTH");
				setIMG_URL("iMG_URL");
				setREG_ID("system");
				setUPD_ID("system");
						
			}
			
		};
	}
	
	public static SAEquipement initEquipmen(final String facilitymgmntNo, final String nffTag) {
		return new SAEquipement() {
			{
				setEQPM_EXPL("eQPM_EXPL");
				setEQPM_IP("eQPM_IP");
				setEQPM_NM("eQPM_NM");
				setEQPM_STTS_CD("_CD");
//				setFCLT_MGMT_NO(facilitymgmntNo);
				setMDL_NM("mDL_NM");
				setMFTR("mFTR");
				setNFC_TAG_ID(nffTag);
				setREG_ID("system");
				setUPD_ID("system");
			}
			
		};
	}
}
