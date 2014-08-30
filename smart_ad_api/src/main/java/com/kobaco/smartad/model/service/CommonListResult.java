package com.kobaco.smartad.model.service;

import java.util.List;

import com.kobaco.smartad.model.Page;
import com.kobaco.smartad.model.Result;
import com.kobaco.smartad.model.data.ParamsCommonPage;

public class CommonListResult<T> {

	private List<T> list ;
	private Result result;
	private Page page;
	
	public CommonListResult(){
		
	}
	public CommonListResult(Result r) {
		this.result = r;
	}
	public CommonListResult(Result r, List<T> lt, Page p) {
		this.result = r;
		this.list = lt;
		this.page = p;
	}
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
}
