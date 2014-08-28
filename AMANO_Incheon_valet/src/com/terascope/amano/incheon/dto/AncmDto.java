package com.terascope.amano.incheon.dto;

import java.util.ArrayList;

import org.json.JSONObject;

public class AncmDto implements IBaseDto {
	private String MAC_ADRES;
	private String USR_ID;
	private String ANCM_SN;
	private String ANCM_NM;
	private String BEGIN_DE;
	private String END_DE;
	private String MAKE_USR_NM;
	private String ANCM_CONT;
	
	
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
	public String getANCM_SN() {
		return ANCM_SN;
	}
	public void setANCM_SN(String aNCM_SN) {
		ANCM_SN = aNCM_SN;
	}
	public String getANCM_NM() {
		return ANCM_NM;
	}
	public void setANCM_NM(String aNCM_NM) {
		ANCM_NM = aNCM_NM;
	}
	public String getBEGIN_DE() {
		return BEGIN_DE;
	}
	public void setBEGIN_DE(String bEGIN_DE) {
		BEGIN_DE = bEGIN_DE;
	}
	public String getEND_DE() {
		return END_DE;
	}
	public void setEND_DE(String eND_DE) {
		END_DE = eND_DE;
	}
	public String getMAKE_USR_NM() {
		return MAKE_USR_NM;
	}
	public void setMAKE_USR_NM(String mAKE_USR_NM) {
		MAKE_USR_NM = mAKE_USR_NM;
	}
	public String getANCM_CONT() {
		return ANCM_CONT;
	}
	public void setANCM_CONT(String aNCM_CONT) {
		ANCM_CONT = aNCM_CONT;
	}

	@Override
	public ApiRequest toRequest() {
		return new ApiRequest().setApiName("MIF_SEL_ANCM").addParams(MAC_ADRES).addParams(USR_ID);
	}
	@Override
	public IBaseDto valueOf(ApiResponse res) {
		return null;
	}
	@Override
	public IBaseDto valueOf(JSONObject json) {
		AncmDto dto = new AncmDto();
		dto.setANCM_SN(json.optString("ANCM_SN"));
		dto.setANCM_NM(json.optString("ANCM_NM"));
		dto.setBEGIN_DE(json.optString("BEGIN_DE"));
		dto.setEND_DE(json.optString("END_DE"));
		dto.setMAKE_USR_NM(json.optString("MAKE_USR_NM"));
		dto.setANCM_CONT(json.optString("ANCM_CONT"));
		return dto;
	}

	
}
