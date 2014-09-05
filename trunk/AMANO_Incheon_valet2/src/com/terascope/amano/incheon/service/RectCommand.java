package com.terascope.amano.incheon.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.util.Log;

import com.terascope.amano.incheon.batch.MainService;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.IBaseDto;
import com.terascope.amano.incheon.dto.RectDto;

public class RectCommand extends ACommand {


	public RectCommand() {
		super();
	}
	
	public RectCommand(String id) {
		super(id);
	}
	
	public RectCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {
		RectDto rectDto = (RectDto)this.dto;
		
		if(ftp.ftpConnect(MainService.FTP_SERVER, MainService.FTP_USER_ID, MainService.FTP_USER_PWD, MainService.FTP_PORT)){
			if ("Y".equals(rectDto.getCSTMR_SIGN_AT())) 
				uploadAndUpdate(db, ftp, rectDto.getVL_NO(), rectDto.getCSTMR_SIGN_FILE_NM(), rectDto.getRECT_FULL_DT(), "S");
			if ("Y".equals(rectDto.getCAR_DAMG_AT())) 
				uploadAndUpdate(db, ftp, rectDto.getVL_NO(), rectDto.getCAR_DAMG_FILE_NM(), rectDto.getRECT_FULL_DT(), "D");
		} else {
			Log.d(this.getClass().getName(),"ftp connection fail");
			if ("Y".equals(rectDto.getCSTMR_SIGN_AT())) 
				uploadAndUpdate(db, null, rectDto.getVL_NO(), rectDto.getCSTMR_SIGN_FILE_NM(), rectDto.getRECT_FULL_DT(), "S");
			if ("Y".equals(rectDto.getCAR_DAMG_AT())) 
				uploadAndUpdate(db, null, rectDto.getVL_NO(), rectDto.getCAR_DAMG_FILE_NM(), rectDto.getRECT_FULL_DT(), "D");
		}
		
		return callApi(http);
	}

	private void uploadAndUpdate(DbHelper db, IvpFtpHelper ftp, String vlNo, String fname, String dt, String flag) {
		String srcFilePath = MainService.FTP_LOCAL_PATH + java.io.File.separator + fname;
		String updDt = null;
		
		boolean ftpResult = false;
		
		if(ftp != null) {
			if("S".equals(flag)) {
				ftpResult = ftp.ftpSignUpload(srcFilePath, fname, vlNo.substring(0,3));
			} else if("D".equals(flag)) {
				ftpResult = ftp.ftpDamageUpload(srcFilePath, fname, vlNo.substring(0,3));
			}
			if (ftpResult) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				updDt = format.format(new Date());
				Log.d(this.getClass().getName(),"ftp upload : " + srcFilePath + " success");
			} else {
				Log.d(this.getClass().getName(),"ftp upload : " + srcFilePath + " fail");
			}
		}
		
		ContentValues values = new ContentValues();
		values.put("VL_NO", vlNo);
		values.put("UPD_DT", updDt);
		values.put("FILE_NM", fname);
		values.put("SVR_IP", MainService.FTP_SERVER);
		values.put("MM_TYPE", flag);
		values.put("REG_DT", dt);
		
		db.open();
		long dbr = db.insertAndReplace("AM_MM", values);
		Log.d(this.getClass().getName(),"insert AM_MM : " + String.valueOf(dbr));
		db.close();		
	}
}

