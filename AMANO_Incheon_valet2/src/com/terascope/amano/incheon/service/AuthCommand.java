package com.terascope.amano.incheon.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.AuthDto;
import com.terascope.amano.incheon.dto.IBaseDto;
import com.terascope.amano.incheon.dto.LoginDto;

public class AuthCommand extends ACommand {


	public AuthCommand() {
		super();
	}
	
	public AuthCommand(String id) {
		super(id);
	}
	
	public AuthCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {
		CommandResult result = callApi(http);
		
		if (result.getResultCd() != CommandResult.CD_SUCCESS) return result;
		
		List<AuthDto> list = new JsonToDto<AuthDto>().setJson(result.getJson()).parse((AuthDto)dto).getInstances();
		
		Log.i(this.getClass().getName(),"AUTH_NO " + list.get(0).getAUTH_NO());
		
		db.open();
		ContentValues values = new ContentValues();
		values.put("AUTH_NO ", list.get(0).getAUTH_NO());
		values.put("DEV_NO",  list.get(0).getDEVC_NO());
		values.put("MAC_ADRES",list.get(0).getMAC_ADRES());
		values.put("PRG_VER",  list.get(0).getPRG_VER());
		values.put("VIDEO_UP_BEGIN_HM", list.get(0).getVIDEO_UP_BEGIN_HM());
		
		long dbr = db.insertAndReplace("AM_DEVICE", values);
		Log.i(this.getClass().getName(),"insert AM_DEVICE : " + String.valueOf(dbr));
		db.close();
		return callApi(http);
	}

}
