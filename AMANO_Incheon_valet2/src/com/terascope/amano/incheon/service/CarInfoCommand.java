package com.terascope.amano.incheon.service;

import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.CarInfoDto;
import com.terascope.amano.incheon.dto.IBaseDto;

public class CarInfoCommand extends ACommand {

	public CarInfoCommand() {
		super();
	}
	
	public CarInfoCommand(String id) {
		super(id);
	}
	
	public CarInfoCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {
		CommandResult result =callApi(http);
		
		if (result.getResultCd() != CommandResult.CD_SUCCESS) return result;
		
		List<CarInfoDto> list = new JsonToDto<CarInfoDto>().setJson(result.getJson()).parse((CarInfoDto)dto).getInstances();
		db.open();
		Iterator<CarInfoDto> it = list.iterator();
		while(it.hasNext()) {
			CarInfoDto dto = it.next();
			ContentValues values = new ContentValues();
			values.put("CAR_SERS_CD", dto.getCAR_SERS_CD());
			values.put("CAR_SERS_NM", dto.getCAR_SERS_NM());
			long dbr = db.insertAndReplace("AM_CAR_INFO", values);
			Log.i(this.getClass().getName(), "insert AM_CAR_INFO " + String.valueOf(dbr));
		}
		db.close();
		return result;
	}
}

