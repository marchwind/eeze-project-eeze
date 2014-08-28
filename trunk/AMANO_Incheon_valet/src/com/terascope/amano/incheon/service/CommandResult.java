package com.terascope.amano.incheon.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

public class CommandResult {

	/*
	  0 성공
	  1 PENDING (보류)
	  2
	  3
	  4
	  5
	 -1 실퍠
	 -2 JSON Parsing 오류
	 */
	public static final int CD_SUCCESS = 0;
	public static final int CD_FAIL = -1;
	public static final int CD_JSON_FAIL = -2;
	public static final int CD_PENDING = 1;
	
	private String refId;
	private String json;
	private int resultCd;
	private String resultMsg;
	
	public CommandResult() {
		;
	}
	
	public CommandResult(int cd, String msg) {
		this.resultCd = cd;
		this.resultMsg = msg;
	}
	
	public String getRefId() {
		return refId;
	}	
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
		this.setJsonAndParse(json);
	}
	public int getResultCd() {
		return resultCd;
	}
	public void setResultCd(int resultCd) {
		this.resultCd = resultCd;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
	public void setJsonAndParse(String json) {
		Log.i(this.getClass().getName(), json);
		try {
			Object obj = new JSONTokener(json).nextValue();
			if (obj instanceof JSONObject) {
				JSONObject jsonObj = (JSONObject)obj;
				String rtnCd = jsonObj.getString("RTN_CD");
				String rtnMsg = jsonObj.getString("RTN_MSG");
				if(!rtnCd.equals("")) setResultCd(Integer.valueOf(rtnCd));
				setResultMsg(rtnMsg);
			} else if (obj instanceof JSONArray) {
				setResultCd(0);
				setResultMsg("");
			} else {
				setResultCd(-2);
				setResultMsg("JSON Parsing error!!");
			}
		} catch (JSONException e) {
			setResultCd(-2);
			setResultMsg("JSON Parsing error!!");
			e.printStackTrace();
		}
		
	}
}
