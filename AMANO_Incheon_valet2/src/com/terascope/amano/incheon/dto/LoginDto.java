package com.terascope.amano.incheon.dto;

import java.io.Serializable;

import org.json.JSONObject;

public class LoginDto implements IBaseDto ,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String MAC_ADRES;
	private String USR_ID;
	private String USR_PWD;
	private String USR_NM;
	private String USR_TY_CD;
	private String LOGIN_DT;

	
	public String getMAC_ADRES() {
		return MAC_ADRES;
	}
	public void setMAC_ADRES(String mAC_ADRES) {
		MAC_ADRES = mAC_ADRES;
	}
	public String getUSR_ID() {
		return USR_ID;
	}
	public void setUSR_ID(String uSR_ID) {
		USR_ID = uSR_ID;
	}
	public String getUSR_PWD() {
		return USR_PWD;
	}
	public void setUSR_PWD(String uSR_PWD) {
		USR_PWD = uSR_PWD;
	}
	public String getUSR_NM() {
		return USR_NM;
	}
	public void setUSR_NM(String uSR_NM) {
		USR_NM = uSR_NM;
	}
	public String getUSR_TY_CD() {
		return USR_TY_CD;
	}
	public void setUSR_TY_CD(String uSR_TY_CD) {
		USR_TY_CD = uSR_TY_CD;
	}	
	public String getLOGIN_DT() {
		return LOGIN_DT;
	}
	public void setLOGIN_DT(String lOGIN_DT) {
		LOGIN_DT = lOGIN_DT;
	}
	
	@Override
	public ApiRequest toRequest() {
		return new ApiRequest().setApiName("MIF_LOGIN").addParams(MAC_ADRES).addParams(USR_ID).addParams(USR_PWD);
	}
	@Override
	public IBaseDto valueOf(ApiResponse res) {
		return null;
	}
	@Override
	public IBaseDto valueOf(JSONObject json) {
		LoginDto dto = new LoginDto();
		dto.setUSR_ID(this.getUSR_ID());
		dto.setUSR_NM(json.optString("USR_NM"));
		dto.setUSR_TY_CD(json.optString("USR_TY_CD"));
		return dto;
	}
	
	
	
	
	
}
