package com.terascope.amano.incheon.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

import android.util.Log;

public class AuthDto implements IBaseDto, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String AUTH_NO;
	private String MAC_ADRES;
	private String DEVC_NO;
	private String PRG_VER;
	private String VIDEO_UP_BEGIN_HM;
	public String getAUTH_NO() {
		return AUTH_NO;
	}
	public void setAUTH_NO(String aUTH_NO) {
		AUTH_NO = aUTH_NO;
	}
	public String getMAC_ADRES() {
		return MAC_ADRES;
	}
	public void setMAC_ADRES(String mAC_ADRES) {
		MAC_ADRES = mAC_ADRES;
	}
	public String getDEVC_NO() {
		return DEVC_NO;
	}
	public void setDEVC_NO(String dEVC_NO) {
		DEVC_NO = dEVC_NO;
	}
	
	public String getPRG_VER() {
		return PRG_VER;
	}
	public void setPRG_VER(String pRG_VER) {
		PRG_VER = pRG_VER;
	}
	
	public String getVIDEO_UP_BEGIN_HM() {
		return VIDEO_UP_BEGIN_HM;
	}
	public void setVIDEO_UP_BEGIN_HM(String vIDEO_UP_BEGIN_HM) {
		VIDEO_UP_BEGIN_HM = vIDEO_UP_BEGIN_HM;
	}
	public String newAUTH_NO() {
		
		SimpleDateFormat fm = new SimpleDateFormat("ddMMyy");
		String nw = fm.format(new Date());
		
		int key = 0;
		for(int i=0; i<6; i++) key += (nw.charAt(i)-48);
		
//		Log.i(this.getClass().getName(), nw.charAt(0) + " --> " + String.valueOf(nw.charAt(0)-48));
//		Log.i(this.getClass().getName(), nw.charAt(1) + " --> " + String.valueOf(nw.charAt(1)-48));
//		Log.i(this.getClass().getName(), nw.charAt(2) + " --> " + String.valueOf(Integer.valueOf(nw.charAt(2))));
//		Log.i(this.getClass().getName(), nw.charAt(3) + " --> " + String.valueOf(Integer.valueOf(nw.charAt(3))));
//		Log.i(this.getClass().getName(), nw.charAt(4) + " --> " + String.valueOf(Integer.valueOf(nw.charAt(4))));
//		Log.i(this.getClass().getName(), nw.charAt(5) + " --> " + String.valueOf(Integer.valueOf(nw.charAt(5))));
//		Log.i(this.getClass().getName(), "--------- sumed key --> " + String.valueOf(key));
		
		if (key < 10) {
			setAUTH_NO(nw+"AMANO0"+String.valueOf(key));
		} else { 
			setAUTH_NO(nw+"AMANO"+String.valueOf(key));	
		}
		return getAUTH_NO();
	}

	@Override
	public ApiRequest toRequest() {
		newAUTH_NO();
		return new ApiRequest().setApiName("MIF_MOBILE_USE_AUTH").addParams(AUTH_NO).addParams(MAC_ADRES).addParams(PRG_VER);
	}
	
	@Override
	public AuthDto valueOf(ApiResponse res) {
//		Object = JSON.parres.toString();
//		this.setDEVC_NO(Object.get?);
		return this;
	}

	public IBaseDto valueOf(JSONObject json) {
		AuthDto dto = new AuthDto();
		dto.setAUTH_NO(this.getAUTH_NO());
		dto.setMAC_ADRES(this.getMAC_ADRES());
		dto.setPRG_VER(this.getPRG_VER());
		dto.setDEVC_NO(json.optString("DEVC_NO"));
		dto.setVIDEO_UP_BEGIN_HM(json.optString("VIDEO_UP_BEGIN_HM"));
		return dto;
	}
}
