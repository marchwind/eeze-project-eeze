package com.kobaco.smartad.model.service;

import com.kobaco.smartad.model.Result;


public class CommonResult implements Result{
	private String resultCode;
	private String resultMsg;
	
	public CommonResult() {
		;
	}
	public CommonResult(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}
	
	@Override
	public String getResultCode() {
		return this.resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@Override
	public String getResultMsg() {
		return this.resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	
}
