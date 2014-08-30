package com.kobaco.smartad.model.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kobaco.smartad.model.Params;
import com.kobaco.smartad.service.PmcEquipmentServiceImpl;

public class ParamsCommonAggregator implements Params {

	private Params page;
	private Params filter;
	private Params order;
	Map<String, Object> paramValues;
	List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
	
	private static final Logger logger = LoggerFactory.getLogger(ParamsCommonAggregator.class);
	
	@Override
	public String getNamespace() {
		return nsOf(filter) + nsOf(order) + nsOf(page);
	}

	@Override
	public Map<String, Object> getColumns() {
		if (paramValues == null) {
			paramValues = new HashMap<String, Object>();
		}
		
		for(Map<String, Object> param : params) {
			for (String key: param.keySet()) {
				logger.debug("**** DB Parameters [key,value] = " + "[" + key +","+ param.get(key) + "]");
				paramValues.put(key, param.get(key));
			}
		}
		return paramValues;
	}

	public ParamsCommonAggregator page(Params page) {
		this.page = page;
		this.params.add(page.getColumns());
		return this;
	}
	
	public ParamsCommonAggregator order(Params order) {
		this.order = order;
		this.params.add(order.getColumns());
		return this;
	}
	
	public ParamsCommonAggregator filter(Params filter) {
		this.filter = filter;
		this.params.add(filter.getColumns());
		return this;
	}
	
	private String nsOf(Params param) {
		return ((param==null)?"":param.getNamespace());
	}
}
