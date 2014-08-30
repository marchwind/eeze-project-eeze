package com.kobaco.smartad.model.data;

import java.util.HashMap;
import java.util.Map;

import com.kobaco.smartad.model.Params;

public class ParamsUserUpdateStatus implements Params {

	private String userNo;
	private String userStateCd;
	
	public ParamsUserUpdateStatus(String userNo, String userStateCd) {
		this.userNo = userNo;
		this.userStateCd =  userStateCd;
	}
	
	@Override
	public String getNamespace() {
		return "Status";
	}

	@Override
	public Map<String, Object> getColumns() {
		return new HashMap<String, Object>(){
			{
				put("USR_NO", userNo);
				put("USR_STTS_CD", userStateCd);
			}
		} ;
	}

	
}
