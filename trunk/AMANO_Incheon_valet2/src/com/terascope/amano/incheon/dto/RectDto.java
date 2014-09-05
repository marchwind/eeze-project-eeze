package com.terascope.amano.incheon.dto;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONObject;

public class RectDto implements IBaseDto,Serializable{

	private static final long serialVersionUID = 1L;
	private final int FLAG = 12345;
	
	private String MAC_ADRES;
	private String VL_NO;
	private String RECT_USR_ID;
	private String RECT_USR_NM;
	private String RECT_DE;
	private String RECT_HM;
	private String RECT_FULL_DT;
	private String CAR_NO;
	private String CARNO_AUTO_RECG_AT;
	private String CAR_SERS_CD;
	private String CAR_SERS_NM;
	private String TRVL_TERM;
	private String ENTRY_DE;
	private String ENTRY_HM;
	private String ENTRY_FULL_DT;
	private String CAR_DAMG_AT = "N";
	private String CAR_DAMG_FILE_NM;

	private String RESV_AT;
	private String CAR_TRANS_CD;
	private String CAR_TRANS_NM;
	private String CSTMR_SIGN_AT;
	private String CSTMR_SIGN_FILE_NM;
	private String LNG_SHRT_TY_CD;
	public String getMAC_ADRES() {
		return MAC_ADRES;
	}
	public void setMAC_ADRES(String mAC_ADRES) {
		MAC_ADRES = mAC_ADRES;
	}
	
	public String getVL_NO() {
		return VL_NO;
	}
	public void setVL_NO(String vL_NO) {
		VL_NO = vL_NO;
	}
	public String getRECT_USR_ID() {
		return RECT_USR_ID;
	}
	
	public String getRECT_USR_NM() {
		return RECT_USR_NM;
	}
	public void setRECT_USR_NM(String rECT_USR_NM) {
		RECT_USR_NM = rECT_USR_NM;
	}
	public void setRECT_USR_ID(String rECT_USR_ID) {
		RECT_USR_ID = rECT_USR_ID;
	}
	public String getRECT_DE() {
		return RECT_DE;
	}
	public void setRECT_DE(String rECT_DE) {
		RECT_DE = rECT_DE;
	}
	public String getRECT_HM() {
		return RECT_HM;
	}
	public void setRECT_HM(String rECT_HM) {
		RECT_HM = rECT_HM;
	}
	
	public String getCAR_NO() {
		return CAR_NO;
	}
	public void setCAR_NO(String cAR_NO) {
		CAR_NO = cAR_NO;
	}
	public String getCARNO_AUTO_RECG_AT() {
		return CARNO_AUTO_RECG_AT;
	}
	public void setCARNO_AUTO_RECG_AT(String cARNO_AUTO_RECG_AT) {
		CARNO_AUTO_RECG_AT = cARNO_AUTO_RECG_AT;
	}
	public String getCAR_SERS_CD() {
		return CAR_SERS_CD;
	}
	public void setCAR_SERS_CD(String cAR_SERS_CD) {
		CAR_SERS_CD = cAR_SERS_CD;
	}
	
	public String getTRVL_TERM() {
		return TRVL_TERM;
	}
	public void setTRVL_TERM(String tRVL_TERM) {
		TRVL_TERM = tRVL_TERM;
	}
	public String getENTRY_DE() {
		return ENTRY_DE;
	}
	public void setENTRY_DE(String eNTRY_DE) {
		ENTRY_DE = eNTRY_DE;
	}
	public String getENTRY_HM() {
		return ENTRY_HM;
	}
	public void setENTRY_HM(String eNTRY_HM) {
		ENTRY_HM = eNTRY_HM;
	}
	
	public String getCAR_DAMG_AT() {
		return CAR_DAMG_AT;
	}
	public void setCAR_DAMG_AT(String cAR_DAMG_AT) {
		CAR_DAMG_AT = cAR_DAMG_AT;
	}
	public String getCAR_DAMG_FILE_NM() {
		return CAR_DAMG_FILE_NM;
	}
	public void setCAR_DAMG_FILE_NM(String cAR_DAMG_FILE_NM) {
		CAR_DAMG_FILE_NM = cAR_DAMG_FILE_NM;
	}
	
	public String getRESV_AT() {
		return RESV_AT;
	}
	public void setRESV_AT(String rESV_AT) {
		RESV_AT = rESV_AT;
	}
	public String getCAR_TRANS_CD() {
		return CAR_TRANS_CD;
	}
	public void setCAR_TRANS_CD(String cAR_TRANS_CD) {
		CAR_TRANS_CD = cAR_TRANS_CD;
	}
	public String getCSTMR_SIGN_AT() {
		return CSTMR_SIGN_AT;
	}
	public void setCSTMR_SIGN_AT(String cSTMR_SIGN_AT) {
		CSTMR_SIGN_AT = cSTMR_SIGN_AT;
	}
	public String getCSTMR_SIGN_FILE_NM() {
		return CSTMR_SIGN_FILE_NM;
	}
	public void setCSTMR_SIGN_FILE_NM(String cSTMR_SIGN_FILE_NM) {
		CSTMR_SIGN_FILE_NM = cSTMR_SIGN_FILE_NM;
	}
	
	public String getRECT_FULL_DT() {
		return RECT_FULL_DT;
	}
	public void setRECT_FULL_DT(String rECT_FULL_DT) {
		RECT_FULL_DT = rECT_FULL_DT;
	}
	public String getCAR_SERS_NM() {
		return CAR_SERS_NM;
	}
	public void setCAR_SERS_NM(String cAR_SERS_NM) {
		CAR_SERS_NM = cAR_SERS_NM;
	}
	public String getENTRY_FULL_DT() {
		return ENTRY_FULL_DT;
	}
	public void setENTRY_FULL_DT(String eNTRY_FULL_DT) {
		ENTRY_FULL_DT = eNTRY_FULL_DT;
	}
	public String getCAR_TRANS_NM() {
		return CAR_TRANS_NM;
	}
	public void setCAR_TRANS_NM(String cAR_TRANS_NM) {
		CAR_TRANS_NM = cAR_TRANS_NM;
	}
	
	
	public String getLNG_SHRT_TY_CD() {
		return LNG_SHRT_TY_CD;
	}
	public void setLNG_SHRT_TY_CD(String lNG_SHRT_TY_CD) {
		LNG_SHRT_TY_CD = lNG_SHRT_TY_CD;
	}
	@Override
	public ApiRequest toRequest() {
		return new ApiRequest()
				.setApiName("MIF_INS_RECT")
				.addParams(MAC_ADRES)
				.addParams(VL_NO)
				.addParams(RECT_USR_ID)
				.addParams(RECT_DE)
				.addParams(RECT_HM)
				.addParams(CAR_NO)
				.addParams(CARNO_AUTO_RECG_AT)
				.addParams(CAR_SERS_CD)
				.addParams(TRVL_TERM)
				.addParams(ENTRY_DE)
				.addParams(ENTRY_HM)
				.addParams(CAR_DAMG_AT)
				.addParams(CAR_DAMG_FILE_NM)
				.addParams(RESV_AT)
				.addParams(CAR_TRANS_CD)
				.addParams(CSTMR_SIGN_AT)
				.addParams(CSTMR_SIGN_FILE_NM)
				.addParams(LNG_SHRT_TY_CD);
	}
	@Override
	public IBaseDto valueOf(ApiResponse res) {
		return null;
	}
	@Override
	public IBaseDto valueOf(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}

