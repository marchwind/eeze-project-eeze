package com.kobaco.smartad.model.service;

import com.kobaco.smartad.model.Page;

public class CommonPage implements Page {

	private int totalPage;
	private int currentPage;
	private int totalCount;
	private int unitPerPage;
	
	public CommonPage() {
		
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public void setUnitPerPage(int unitPerPage) {
		this.unitPerPage = unitPerPage;
	}


	@Override
	public int getTotalPage() {
		return this.totalPage;
	}

	@Override
	public int getCurrentPage() {
		return this.currentPage;
	}

	@Override
	public int getTotalCount() {
		return this.totalCount;
	}

	@Override
	public int getUnitPerPage() {
		return this.unitPerPage;
	}

}
