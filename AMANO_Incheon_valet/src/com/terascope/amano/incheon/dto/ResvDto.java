package com.terascope.amano.incheon.dto;

import java.io.Serializable;

import org.json.JSONObject;

public class ResvDto implements IBaseDto,Serializable{
	private String MAC_ADRES;
	private String CAR_NO;
	private String RESV_DE;
	private Integer RESV_SN;
	private String CSTMR_NM;
	private String CAR_TY_NM;
	private String CAR_TRSF_SCHDL_TM;
	private String DPTR_SCHDL_TM;
	private String ENTRY_SCHDL_TM;
	private String ENTRY_FLIGHT;
	public String getMAC_ADRES() {
		return MAC_ADRES;
	}
	public void setMAC_ADRES(String mAC_ADRES) {
		MAC_ADRES = mAC_ADRES;
	}
	public String getCAR_NO() {
		return CAR_NO;
	}
	public void setCAR_NO(String cAR_NO) {
		CAR_NO = cAR_NO;
	}
	public String getRESV_DE() {
		return RESV_DE;
	}
	public void setRESV_DE(String rESV_DE) {
		RESV_DE = rESV_DE;
	}
	public Integer getRESV_SN() {
		return RESV_SN;
	}
	public void setRESV_SN(Integer rESV_SN) {
		RESV_SN = rESV_SN;
	}
	public String getCSTMR_NM() {
		return CSTMR_NM;
	}
	public void setCSTMR_NM(String cSTMR_NM) {
		CSTMR_NM = cSTMR_NM;
	}
	public String getCAR_TY_NM() {
		return CAR_TY_NM;
	}
	public void setCAR_TY_NM(String cAR_TY_NM) {
		CAR_TY_NM = cAR_TY_NM;
	}
	public String getCAR_TRSF_SCHDL_TM() {
		return CAR_TRSF_SCHDL_TM;
	}
	public void setCAR_TRSF_SCHDL_TM(String cAR_TRSF_SCHDL_TM) {
		CAR_TRSF_SCHDL_TM = cAR_TRSF_SCHDL_TM;
	}
	public String getDPTR_SCHDL_TM() {
		return DPTR_SCHDL_TM;
	}
	public void setDPTR_SCHDL_TM(String dPTR_SCHDL_TM) {
		DPTR_SCHDL_TM = dPTR_SCHDL_TM;
	}
	public String getENTRY_SCHDL_TM() {
		return ENTRY_SCHDL_TM;
	}
	public void setENTRY_SCHDL_TM(String eNTRY_SCHDL_TM) {
		ENTRY_SCHDL_TM = eNTRY_SCHDL_TM;
	}
	public String getENTRY_FLIGHT() {
		return ENTRY_FLIGHT;
	}
	public void setENTRY_FLIGHT(String eNTRY_FLIGHT) {
		ENTRY_FLIGHT = eNTRY_FLIGHT;
	}
	
	@Override
	public ApiRequest toRequest() {
		return new ApiRequest().setApiName("MIF_SEL_RESV").addParams(MAC_ADRES).addParams(CAR_NO);
	}
	@Override
	public IBaseDto valueOf(ApiResponse res) {
		return null;
	}

	@Override
	public IBaseDto valueOf(JSONObject json) {
		// TODO Auto-generated method stub
				ResvDto dto = new ResvDto();
				dto.setRESV_DE(json.optString("RESV_DE"));
				dto.setRESV_SN(json.optInt("RESV_SN"));
				dto.setCSTMR_NM(json.optString("CSTMR_NM"));
				dto.setCAR_NO(this.getCAR_NO());
				dto.setCAR_TY_NM(json.optString("CAR_TY_NM"));
				dto.setCAR_TRSF_SCHDL_TM(json.optString("CAR_TRSF_SCHDL_TM"));
				dto.setDPTR_SCHDL_TM(json.optString("DPTR_SCHDL_TM"));
				dto.setENTRY_SCHDL_TM(json.optString("ENTRY_SCHDL_TM"));
				dto.setENTRY_FLIGHT(json.optString("ENTRY_FLIGHT"));
				return dto;
	}
	
	
	
}
