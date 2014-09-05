package com.terascope.amano.incheon.common;

import android.os.Environment;

public class CommonSet {
	public static final String RECEIPT_DATA_NAME = "RectDto";
	public static final String RESV_DATA_NAME = "ResvDto";
	public static final String LOGIN_DATA_NAME = "LoginDto";
	public static final String AUTH_DATA_NAME = "AuthDto";
	
	public static final String MEDIA_PATH_NAME ="mediaPath";
	public static final int SETTING_VIEW = 3;
	public static final int BLUETOOTH_VIEW = 31;
	public static final int LOGOUT = 10;
	public static final int MAIN = 2;
	
	public static final String RESULT_SUCCESS_CODE = "0000";
	public static final String REULST_FAIL_CODE = "0100";
	public static final String BLUETHOOTH_ERROR_CODE = "0900";
	
	public static final String PRINT_CUSTOMER = "customer";
	public static final String PRINT_OFFER = "offer";
	public static final String PRINT_ALL = "all";
	
	public static final int IMAGE_WIDTH = 350;
	
	public static final String SAVE_PATH = Environment.getExternalStorageDirectory() + "/Amano/";
	
	public static final class Resolution {
		public static final String FHD = "1920_1080";
		public static final String HD = "1280_720";
		public static final String SD = "720_480";
		public static final String MMS = "320_240";	
	}
	
	public static final class Net {
		public static final String ASP_SERVER_URL = "http://61.41.4.6/ivpwsj/ivpservice.svc";
		public static final String MOBILE_AUTH = "/MIF_MOBILE_USE_AUTH/";
		public static final String LOGIN_USR = "/MIF_LOGIN/";
		public static final String NOTICE_LIST = "/MIF_SEL_ANCM/";
		public static final String RESV_INFO = "/MIF_SEL_RESV/";
		public static final String CAR_INFO = "/MIF_REQ_CARINFO/";
		public static final String INSERT_RECT = "/MIF_INS_RECT/";
		public static final String UPD_VIDEO = "/MIF_UPD_VIDEO/";
		public static final String UPD_IMAGE = "/MIF_UPD_PICS/";
	}
	
	public static final class SettingType {
		public static final String FLASH_ALL = "all";
		public static final String FLASH_CUSTOM = "custom";
		public static final String FLASH_TIME = "time";
	}
}
