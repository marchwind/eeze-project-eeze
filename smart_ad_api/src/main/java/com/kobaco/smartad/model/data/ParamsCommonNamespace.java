package com.kobaco.smartad.model.data;

import java.util.Map;

import com.kobaco.smartad.model.Params;

public class ParamsCommonNamespace implements Params {
	
	private String namespace;
	private Map<String, Object> columns;
	
	@Override
	public String getNamespace() {
		return namespace;
	}

	@Override
	public Map<String, Object> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, Object> columns, String ns) {
		this.namespace = ns;
		this.columns = columns;
	}
}
