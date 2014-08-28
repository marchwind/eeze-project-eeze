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
import com.terascope.amano.incheon.dto.UpdDto;

public class UploadCommand extends ACommand {


	public UploadCommand() {
		super();
	}
	
	public UploadCommand(String id) {
		super(id);
	}
	
	public UploadCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {
		
		UpdDto updDto = (UpdDto)super.dto;
		
//		updDto.getFILE_NM();
//		updDto.getVL_NO();
//		updDto.getMM_TYPE();
//		updDto.getMAC_ADRES();
		
		String srcFilePath = MainService.FTP_LOCAL_PATH + java.io.File.separator + updDto.getFILE_NM();
		String updDt = null;
		
		if(ftp.ftpConnect(MainService.FTP_SERVER, MainService.FTP_USER_ID, MainService.FTP_USER_PWD, MainService.FTP_PORT)){
			Log.d(this.getClass().getName(),"ftp connection : " + srcFilePath + " success");
			
			boolean ftpResult;
			if("S".equals(updDto.getMM_TYPE())) {
				ftpResult = ftp.ftpSignUpload(srcFilePath, updDto.getFILE_NM(), updDto.getVL_NO().substring(0,3));
			} else if ("V".equals(updDto.getMM_TYPE())) {
				ftpResult = ftp.ftpVideoUpload(srcFilePath, updDto.getFILE_NM(), updDto.getVL_NO().substring(0,3));
			} else if ("P".equals(updDto.getMM_TYPE())) {
				ftpResult = ftp.ftpImageUpload(srcFilePath, updDto.getFILE_NM(), updDto.getVL_NO().substring(0,3));
			} else {
				ftpResult = false;
			}
			
			if (ftpResult) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				updDt = format.format(date);
				Log.d(this.getClass().getName(),"ftp upload : " + srcFilePath + " success");
				
				ContentValues values = new ContentValues();
				values.put("VL_NO", updDto.getVL_NO());
				values.put("UPD_DT", updDt);
				values.put("FILE_NM", updDto.getFILE_NM());
				values.put("SVR_IP", MainService.FTP_SERVER);;
				values.put("MM_TYPE", updDto.getMM_TYPE());
				values.put("REG_DT", updDto.getREG_DT());
				
				db.open();
				long dbr = db.insertAndReplace("AM_MM", values);
				Log.d(this.getClass().getName(),"insert AM_MM : " + String.valueOf(dbr));
				db.close();		
				
				updDto.setSVR_IP(MainService.FTP_SERVER);
				String de = updDt.substring(0, updDt.indexOf(" "));
				String hm = updDt.substring(updDt.indexOf(" ")+1);
				updDto.setUPD_DE(de.replace("-", ""));
				updDto.setUPD_HM(hm.replace(":", ""));
				
				return callApi(http);
			
			} else {
				Log.d(this.getClass().getName(),"ftp upload : " + srcFilePath + " fail");
			}
			ftp.ftpDisconnect();
		} else {
			Log.d(this.getClass().getName(),"ftp connection : " + srcFilePath + " fail");
		}
		
//		
//		db.open();
////		Cursor c = db.selectColumn("SELECT * FROM AM_MM WHERE VL_NO = '"+updDto.getVL_NO()+"' AND FILE_NAME='"+updDto.getFILE_NM()+"'");
////		if (c.moveToFirst()) {
////			c.getString(c.getColumnIndex("MM_TYPE"));
////		}		
//		db.close();

		return new CommandResult(CommandResult.CD_FAIL, "FTP Fail");
	}

}
