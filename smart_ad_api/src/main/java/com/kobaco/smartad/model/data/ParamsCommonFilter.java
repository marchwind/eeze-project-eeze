package com.kobaco.smartad.model.data;

import java.util.Map;

import com.kobaco.smartad.model.Params;

public class ParamsCommonFilter implements Params{

	private String ns = "Filter" ;
	private Map<String, Object> columns;
	
	@Override
	public String getNamespace() {
		return ns;
	}
	
	public void addNamespace(String ns) {
		this.ns += ns;
	}
	
	@Override
	public Map<String, Object> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, Object> columns) {
		this.columns = columns;
	}
	
}
