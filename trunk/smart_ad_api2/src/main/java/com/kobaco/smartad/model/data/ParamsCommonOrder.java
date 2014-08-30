package com.kobaco.smartad.model.data;

import java.util.Map;

import com.kobaco.smartad.model.Params;

public class ParamsCommonOrder implements Params {
	
	private Map<String, Object> columns;
	
	@Override
	public String getNamespace() {
		return "Order";
	}

	@Override
	public Map<String, Object> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, Object> columns) {
		this.columns = columns;
	}
	
}
