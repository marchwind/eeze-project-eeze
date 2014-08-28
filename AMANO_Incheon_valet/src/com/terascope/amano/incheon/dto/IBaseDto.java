package com.terascope.amano.incheon.dto;

import java.util.ArrayList;

import org.json.JSONObject;

public interface IBaseDto {
	public ApiRequest toRequest();
	public IBaseDto valueOf(ApiResponse res);
	public IBaseDto valueOf(JSONObject json);
}
