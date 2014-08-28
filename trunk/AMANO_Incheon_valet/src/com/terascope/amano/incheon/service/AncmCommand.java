package com.terascope.amano.incheon.service;

import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.AncmDto;
import com.terascope.amano.incheon.dto.CarInfoDto;
import com.terascope.amano.incheon.dto.IBaseDto;

public class AncmCommand extends ACommand {

	public AncmCommand() {
		super();
	}
	
	public AncmCommand(String id) {
		super(id);
	}
	
	public AncmCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {
		CommandResult result = callApi(http);
		
		if (result.getResultCd() != CommandResult.CD_SUCCESS) return result;
		
		List<AncmDto> list = new JsonToDto<AncmDto>().setJson(result.getJson()).parse((AncmDto)dto).getInstances();
		db.open();
		Iterator<AncmDto> it = list.iterator();
		while(it.hasNext()) {
			AncmDto dto = it.next();
			ContentValues values = new ContentValues();
			values.put("ANCM_SN", dto.getANCM_SN());
			values.put("ANCM_NM", dto.getANCM_NM());
			values.put("BEGIN_DE",dto.getBEGIN_DE());
			values.put("END_DE",  dto.getEND_DE());
			values.put("MAKE_USR_NM",dto.getMAKE_USR_NM());
			values.put("ANCM_CONT",dto.getANCM_CONT());
			long dbr = db.insertAndReplace("AM_ANCM", values);
			Log.i(this.getClass().getName(), "insert AM_ANCM " + String.valueOf(dbr));
		}
		db.close();
		return result;
	}

}
