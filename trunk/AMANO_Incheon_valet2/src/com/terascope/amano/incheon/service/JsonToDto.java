package com.terascope.amano.incheon.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.terascope.amano.incheon.dto.IBaseDto;

public class JsonToDto<T extends IBaseDto> {

	private String json;
	private List<T> listT;
	
	public JsonToDto<T> setJson(String json) {
		this.json = json;
		return this;
	}
	
	public JsonToDto<T> parse(T t) {
		
		listT = new ArrayList<T>();
		try {
			Object obj = new JSONTokener(json).nextValue();
			if (obj instanceof JSONObject) {
				JSONObject jsonObj = (JSONObject)obj;
				listT.add((T) t.valueOf(jsonObj));
			} else if (obj instanceof JSONArray) {
				JSONArray jsonArr = (JSONArray)obj;
				for(int i=0; i<jsonArr.length(); i++) {
					JSONObject jsonObj = jsonArr.getJSONObject(i);
					listT.add((T) t.valueOf(jsonObj));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		};

		return this;
	}

	public List<T> getInstances() {
		return listT;
	}
	
}
