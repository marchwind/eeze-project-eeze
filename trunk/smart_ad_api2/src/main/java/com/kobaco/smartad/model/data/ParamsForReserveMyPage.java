package com.kobaco.smartad.model.data;

import java.util.HashMap;
import java.util.Map;

import com.kobaco.smartad.model.Page;
import com.kobaco.smartad.model.Params;

public class ParamsForReserveMyPage implements Params {

	private String facilityiesNo;
	private String userNo;
	private Page page;
	
	


	public ParamsForReserveMyPage(String facilityiesNo, String userNo) {
		super();
		this.facilityiesNo = facilityiesNo;
		this.userNo = userNo;
	}

	public String getFacilityiesNo() {
		return facilityiesNo;
	}

	public void setFacilityiesNo(String facilityiesNo) {
		this.facilityiesNo = facilityiesNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	@Override
	public String getNamespace() {
		return "My";
	}

	@Override
	public Map<String, Object> getColumns() {
		return new HashMap<String, Object>(){
			{
				put("facilityiesNo", facilityiesNo);
				put("userNo", userNo);
			}
		} ;
	}

	
	
}
