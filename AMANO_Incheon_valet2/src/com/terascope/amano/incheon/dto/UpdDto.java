package com.terascope.amano.incheon.dto;

import java.util.ArrayList;

import org.json.JSONObject;

public class UpdDto implements IBaseDto{

	private String MAC_ADRES;
	private String VL_NO;
	private String SEQ;
	private String UPD_DE;
	private String UPD_HM;
	private String SVR_IP;
	private String FILE_NM;
	private String FILE_PATH;
	private String FLAG; // V:video , P:picture, S:sign
	private String MM_TYPE;
	private String REG_DT;
	
	public String getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getMM_TYPE() {
		return MM_TYPE;
	}
	public void setMM_TYPE(String mM_TYPE) {
		MM_TYPE = mM_TYPE;
		FLAG = mM_TYPE;
	}
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
	public String getSEQ() {
		return SEQ;
	}
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	public String getUPD_DE() {
		return UPD_DE;
	}
	public void setUPD_DE(String uPD_DE) {
		UPD_DE = uPD_DE;
	}
	public String getUPD_HM() {
		return UPD_HM;
	}
	public void setUPD_HM(String uPD_HM) {
		UPD_HM = uPD_HM;
	}
	public String getSVR_IP() {
		return SVR_IP;
	}
	public void setSVR_IP(String sVR_IP) {
		SVR_IP = sVR_IP;
	}
	public String getFILE_NM() {
		return FILE_NM;
	}
	public void setFILE_NM(String fILE_NM) {
		FILE_NM = fILE_NM;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
		MM_TYPE = fLAG;
	}
	@Override
	public ApiRequest toRequest() {
		
		ApiRequest apiRequest = new ApiRequest();
		if ("V".equals(MM_TYPE)) {
			apiRequest.setApiName("MIF_UPD_VIDEO");
		} else {
			apiRequest.setApiName("MIF_UPD_PICS");
		}
		return apiRequest.addParams(MAC_ADRES)
						.addParams(VL_NO)
						.addParams(UPD_DE)
						.addParams(UPD_HM)
						.addParams(SVR_IP)
						.addParams(FILE_NM);
	}
	@Override
	public IBaseDto valueOf(ApiResponse res) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseDto valueOf(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
}
