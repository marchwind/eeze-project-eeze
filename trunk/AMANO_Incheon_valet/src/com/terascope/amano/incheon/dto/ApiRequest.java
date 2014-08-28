package com.terascope.amano.incheon.dto;

public class ApiRequest {

	private String request = "";
	private String apiName = "";
	private String params="";
	public static final String delimiter = ",";
	public static String INIT = "INIT";
	
	public ApiRequest() {
		
	}
	
	public ApiRequest(String url) {
		this.request = url;
	}
	
	public ApiRequest(String api, String params) {
		this.apiName = api;
		this.params = params;
		this.request = "/" + this.apiName + "/" + this.params;
	}
	
	public ApiRequest setApiName(String api) {
		this.apiName = api;
		return this;
	}
	
	public ApiRequest addParams(String param) {
		if (params.equals("")) {
			params = param;
		} else {
			params += delimiter + param;
		}
		return this;
	}

	public String request() {
		this.request = "/" + this.apiName + "/" + this.params;
		return request;
	}
	
	@Override
	public String toString() {
		return this.request;
	}
}
