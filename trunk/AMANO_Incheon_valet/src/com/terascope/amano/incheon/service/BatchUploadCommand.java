package com.terascope.amano.incheon.service;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.util.Log;

import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.HttpHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dto.IBaseDto;
import com.terascope.amano.incheon.dto.UpdDto;
import com.terascope.amano.incheon.utils.NetworkUtils;

public class BatchUploadCommand extends ACommand  {

	public BatchUploadCommand() {
		super();
	}
	
	public BatchUploadCommand(String id) {
		super(id);
	}
	
	public BatchUploadCommand(String id, IBaseDto dto) {
		super(id, dto);
	}
	
	private class AM_MM {
		public String VL_NO;
		public String FILE_NM;
		public String MM_TYPE;
		public String REG_DT;
		
		public AM_MM(String no, String nm, String type, String dt) {
			this.VL_NO = no;
			this.FILE_NM = nm;
			this.MM_TYPE = type;
			this.REG_DT = dt;
		}
	}
	
	@Override
	public CommandResult execute(DbHelper db, HttpHelper http, IvpFtpHelper ftp) {

		if(!NetworkUtils.isWifiConnected(getContext())){
			return new CommandResult(CommandResult.CD_FAIL, "WIFI Disconnected!!!");
		}
		
		List<AM_MM> upList = new ArrayList<AM_MM>();
		
		db.open();
		Cursor c = db.selectColumn("SELECT VL_NO, FILE_NM, MM_TYPE, REG_DT FROM AM_MM WHERE UPD_DT IS NULL ORDER BY REG_DT"); // 
		Log.d(this.getClass().getName(), "AM_MM COUNT = " + c.getCount());
		while(c.moveToNext()) {
			upList.add(new AM_MM(c.getString(c.getColumnIndex("VL_NO")),
								c.getString(c.getColumnIndex("FILE_NM")),
								c.getString(c.getColumnIndex("MM_TYPE")),
								c.getString(c.getColumnIndex("REG_DT"))));
		}		
		db.close();
		
		boolean isPartialError = false;
		for(int i=0; i<upList.size(); i++) {
			UpdDto upDto = new UpdDto();
			upDto.setFILE_NM(upList.get(i).FILE_NM);
			upDto.setMM_TYPE(upList.get(i).MM_TYPE);
			upDto.setVL_NO(upList.get(i).VL_NO);
			upDto.setREG_DT(upList.get(i).REG_DT);
			upDto.setMAC_ADRES(NetworkUtils.getMacAddress(getContext()));
			
			ACommand cmd = new UploadCommand();
			cmd.setDto(upDto);
			CommandResult result = cmd.execute(db, http, ftp);
			if(CommandResult.CD_SUCCESS != result.getResultCd())
				isPartialError = true;
		}
		
		if (isPartialError)
			return new CommandResult(CommandResult.CD_PENDING, "Partial SUCCESS");
		else
			return new CommandResult(CommandResult.CD_SUCCESS, "SUCCESS ALL");
	}

}
