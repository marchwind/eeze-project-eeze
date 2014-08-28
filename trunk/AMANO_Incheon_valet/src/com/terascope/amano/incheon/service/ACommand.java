package com.terascope.amano.incheon.service;

import android.content.Context;

import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.IBaseDto;

public abstract class ACommand {

	private String id;
	protected IBaseDto dto;
	protected Context mContext;
	
	public ACommand() {
		
	}
	public ACommand(String id){
		this.id = id;
	}
	public ACommand(String id,IBaseDto dto) {
		this.id = id;
		this.dto = dto;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getId(){
		return id;
	}
	public void setDto(IBaseDto dto) {
		this.dto = dto;
	}
	
	public Context getContext() {
		return mContext;
	}
	public void setContext(Context mContext) {
		this.mContext = mContext;
	}
	
	public abstract CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp);
	
	protected CommandResult callApi(HttpHelper http) {
		String result = http.httpGet(this.dto.toRequest().request());
		CommandResult cResult = new CommandResult();
		cResult.setRefId(getId());
		cResult.setJson(result);
		return cResult ;
	}
}
