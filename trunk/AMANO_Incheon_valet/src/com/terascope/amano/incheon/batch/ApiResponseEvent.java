package com.terascope.amano.incheon.batch;

import com.terascope.amano.incheon.dto.ApiResponse;

public class ApiResponseEvent {
	
	public static String INIT = "INIT";
	
	private ApiResponse res;
	
	public ApiResponseEvent(ApiResponse res) {
		this.res = res;
	}
	
	public ApiResponse get() {
		return this.res;
	}
}
