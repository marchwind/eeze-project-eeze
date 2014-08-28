package com.terascope.amano.incheon.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.IBaseDto;
import com.terascope.amano.incheon.dto.LoginDto;

public class LoginCommand extends ACommand {

	public LoginCommand() {
		super();
	}
	
	public LoginCommand(String id) {
		super(id);
	}
	
	public LoginCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {
		CommandResult result =callApi(http);
		
		if (result.getResultCd() != CommandResult.CD_SUCCESS) return result;
		
		List<LoginDto> list = new JsonToDto<LoginDto>().setJson(result.getJson()).parse((LoginDto)dto).getInstances();
		
		Log.i(this.getClass().getName(),"USR_NM " + list.get(0).getUSR_NM());
		Log.i(this.getClass().getName(),"USR_TY_CD " + list.get(0).getUSR_TY_CD());
		
		db.open();
		ContentValues values = new ContentValues();
		values.put("USR_ID", list.get(0).getUSR_ID());
		values.put("USR_NM", list.get(0).getUSR_NM());
		values.put("USR_TY_CD", list.get(0).getUSR_TY_CD());
		
		long dbr = db.insertAndReplace("AM_USR", values);
		Log.i(this.getClass().getName(),"insert AM_USR : " + String.valueOf(dbr));
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		values.clear();
		values.put("USR_ID", list.get(0).getUSR_ID());
		values.put("LOGIN_DT", format.format(new Date()));
		
		dbr = db.insertColumn("AM_LOGIN", values);
		Log.i(this.getClass().getName(),"insert AM_LOGIN : " + String.valueOf(dbr));
		db.close();
		
		return result;
	}
}

