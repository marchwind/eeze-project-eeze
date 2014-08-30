package com.kobaco.smartad.model.service;

import com.kobaco.smartad.model.Result;

public class CommonSingleResult<T> {
	
	private T info;
	private Result result;
	
	public CommonSingleResult() {
		
	}
	
	public CommonSingleResult(Result r) {
		this.result = r;
	}
	
	public CommonSingleResult(Result r, T t) {
		this.result = r;
		this.info = t;
	}
	
	public T getInfo() {
		return info;
	}
	public void setInfo(T info) {
		this.info = info;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
}
