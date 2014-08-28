package com.terascope.amano.incheon.batch;

import com.terascope.amano.incheon.dto.ApiRequest;
import com.terascope.amano.incheon.dto.ApiResponse;

public class ApiRequestEvent {

	public static String INIT = "INIT";
	
	private ApiRequest req; 
	
	public ApiRequestEvent(ApiRequest req) {
		this.req = req;
	}
	
	public ApiRequest get() {
		return this.req;
	}
}
