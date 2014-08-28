package com.terascope.amano.incheon.dto;

import org.json.JSONObject;

public class ApiResponse {

	private String response="";
	private JSONObject json;
	public static final String delimiter = ",";
	public static String INIT = "INIT";
	
	public ApiResponse() {
		
	}
	
	public ApiResponse(String param) {
		set(param);
	}

	public void set(String param) {
		response = param;
//		JSONTokener jsonTok = new JSONTokener(param);
//		try {
//			JSONObject jsonObj = (JSONObject)jsonTok.nextValue();
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public String toString() {
		return this.response;
	}

}
