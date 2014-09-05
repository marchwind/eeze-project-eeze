package com.terascope.amano.incheon.common;

import java.io.File;

import com.terascope.amano.incheon.dto.ResultDto;

import android.app.Activity;
import android.os.Environment;

public class CheckAppHelper {

	Activity ac;
	private Prefs myPrefs;
	public CheckAppHelper(Activity actmp) {
		this.ac = actmp;
		myPrefs = new Prefs(ac.getApplicationContext());
	}
	
	public ResultDto checkCameraTempFile() {
		ResultDto result = new ResultDto();
		String mRootPath = Environment.getExternalStorageDirectory().getAbsolutePath()
				+ "/Test/";
		String fileChk = mRootPath + "Template.dat";
		File fTestRoot = new File(fileChk);
		if (fTestRoot.exists()) {
			result.setRTN_CD(CommonSet.RESULT_SUCCESS_CODE);
			result.setRTN_MSG("File exist Success");
		} else {
			result.setRTN_CD(CommonSet.REULST_FAIL_CODE);
			result.setRTN_MSG("File not Found");
		}
		
		return result;
	}
	
	public ResultDto checkBluetoothConnect() {
		ResultDto result = new ResultDto();
		
		if(myPrefs.getBluetoothID().equals("") || myPrefs.getBluetoothID() == null) {
			result.setRTN_CD(CommonSet.REULST_FAIL_CODE);
			result.setRTN_MSG("Bluetooth not Connect");
		} else {
			result.setRTN_CD(CommonSet.RESULT_SUCCESS_CODE);
			result.setRTN_MSG("Bluetooth is Connect");
		}
		
		return result;
	}
}
