package com.kobaco.smartad.model.data;

import java.util.HashMap;
import java.util.Map;

import com.kobaco.smartad.model.Page;
import com.kobaco.smartad.model.Params;

public class ParamsCommonPage implements Params, Page {

	private Map<String, Object> columns ;
	
	
	public ParamsCommonPage() {
		 columns  = new HashMap<String, Object>() {
			 {
				 put("totalPage",  0);
				 put("currentPage",0);
				 put("unitPerPage",0);
				 put("totalCount", 0);
			 }
		 };
	}
	
	@Override
	public String getNamespace() {
		return "Page";
	}

	@Override
	public Map<String, Object> getColumns() {
		columns.put("startRow", getStartRow());
		columns.put("endRow", getEndRow());
		return columns;
	}

	@Override
	public int getTotalPage() {
		if (getUnitPerPage() > 0) {
			return (int) Math.ceil( (double)getTotalCount() / (double) getUnitPerPage() );
		}
		return 0;
	}

	@Override
	public int getCurrentPage() {
		return (Integer)this.columns.get("currentPage");
	}

	@Override
	public int getTotalCount() {
		return (Integer)this.columns.get("totalCount");
	}

	@Override
	public int getUnitPerPage() {
		return (Integer)this.columns.get("unitPerPage");
	}
	
	public int getStartRow() {
		return (getCurrentPage()-1)*getUnitPerPage();
	}
	
	public int getEndRow() {
		return getUnitPerPage();
	}
	
	public void setTotalPage(int tp) {
		this.columns.put("totalPage", tp);
	}

	public void setCurrentPage(int cp) {
		this.columns.put("currentPage",cp);
	}

	public void setTotalCount(int tc) {
		this.columns.put("totalCount", tc);
	}

	public void setUnitPerPage(int upp) {
		this.columns.put("unitPerPage",upp);
	}

}
