package com.terascope.amano.incheon.dto;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

//import com.terascope.amano.incheon.common.CommonHttp;

public class CarInfoDto implements IBaseDto{

	//CommonHttp carDao;
	
	
	private String MAC_ADRES;
	private String CAR_SERS_CD;
	private String CAR_SERS_NM;
	
	public String getMAC_ADRES() {
		return MAC_ADRES;
	}
	public void setMAC_ADRES(String mAC_ADRES) {
		MAC_ADRES = mAC_ADRES;
	}
	public String getCAR_SERS_CD() {
		return CAR_SERS_CD;
	}
	public void setCAR_SERS_CD(String cAR_SERS_CD) {
		CAR_SERS_CD = cAR_SERS_CD;
	}
	public String getCAR_SERS_NM() {
		return CAR_SERS_NM;
	}
	public void setCAR_SERS_NM(String cAR_SERS_NM) {
		CAR_SERS_NM = cAR_SERS_NM;
	}


	@Override
	public IBaseDto valueOf(ApiResponse res) {
		
	      Log.d("통신",getMAC_ADRES());
	      return null;
	}

	@Override
	public ApiRequest toRequest() {
		return new ApiRequest().setApiName("MIF_REQ_CARINFO").addParams(MAC_ADRES);
	}
	
	@Override
	public IBaseDto valueOf(JSONObject json) {

		CarInfoDto dto = new CarInfoDto();
		dto.setCAR_SERS_CD(json.optString("CAR_SERS_CD"));
		dto.setCAR_SERS_NM(json.optString("CAR_SERS_NM"));
		return dto;
	}

}
