package com.kobaco.smartad.model.data;

import java.util.HashMap;
import java.util.Map;

import com.kobaco.smartad.model.Params;

public class ParamsForLogin implements Params {

	private String userId;
	private String userPassword;
	
	public ParamsForLogin(String userId, String userPassword) {
		this.userId = userId;
		this.userPassword =  userPassword;
	}
	
	@Override
	public String getNamespace() {
		return "ForLogin";
	}

	@Override
	public Map<String, Object> getColumns() {
		return new HashMap<String, Object>(){
			{
				put("USR_ID", userId);
				put("USR_PWD", userPassword);
			}
		} ;
	}

	
}
