package com.terascope.amano.incheon.batch;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.squareup.otto.Subscribe;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dao.helper.IvpFtpHelper;
import com.terascope.amano.incheon.dao.helper.IvpHttpHelper;
import com.terascope.amano.incheon.service.ACommand;

public class MainService extends Service {

	private static final String TAG = "MainService";
	private final Handler handler = new Handler();
	private IvpHttpHelper mHttp;
	private IvpFtpHelper mFtp;
	
	public static String HTTP_URL = "http://61.41.4.6/ivpwsj/ivpservice.svc";
	public static String FTP_SERVER = "61.41.4.6" ;// "192.168.0.6"; //
	public static int FTP_PORT = 21 ;
	public static String FTP_USER_ID = "ivpws_user";
	public static String FTP_USER_PWD = "ivpsys_00!@";
	public static String FTP_LOCAL_PATH = Environment.getExternalStorageDirectory() + "/Amano";
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, "onCreate()");
	}
	
	@Override
	public int onStartCommand(Intent aIntent, int aFlags, int aStartId) {
		super.onStartCommand(aIntent, aFlags, aStartId);
		Log.i(TAG, "onStartCommand()");
		BusBuilder.getInstance().register(this);
		mHttp = new IvpHttpHelper(HTTP_URL);
		mFtp = new IvpFtpHelper();
		
		Intent i = new Intent("android.intent.action.MAIN").putExtra("bind", "onBind");
		this.sendBroadcast(i);
		
		return START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy()");
		BusBuilder.getInstance().unregister(this);
	
	}

	@Override
	public IBinder onBind(Intent arg0) {		
		Log.i(TAG, "onBind()");
		return null;
	}

//	@Produce
//	public ApiResponseEvent produceResponseEvent() {
//		return new ApiResponseEvent(new ApiResponse(ApiResponse.INIT));
//	}
	
//	@Subscribe
//	public void onRequestEvent(final ApiRequestEvent re) {
//		Log.i(this.getClass().getName(), re.get().toString());
//		if (ApiRequest.INIT.equals(re.get().toString())) {
//			;
//		} else {
//			{
//				new Thread() {
//					@Override
//					public void run() {
//						String res = mHttp.httpGet(HTTP_URL + re.get().request());
////						mFtp.ftpConnect(FTP_SERVER, FTP_USER_ID, FTP_USER_PWD, FTP_PORT);
////						mFtp.ftpVideoUpload("srcFilePath", "desFileName", "001");
////						mFtp.ftpImageUpload("srcFilePath", "desFileName", "001");
//						BusBuilder.getInstance().post(new ApiResponseEvent(new ApiResponse("Response of ==> " + res)));
//					}
//				}.start();					
//			}
//		}
//	}
	
//	@Produce
//	public ApiResponseEvent produceResponseEvent() {
//		return new ApiResponseEvent(new ApiResponse(ApiResponse.INIT));
//	}
//	
	@Subscribe
	public void onCommandEvent(final ACommand com) {
		Log.i(this.getClass().getName(), com.getId());
		new Thread() {
					@Override
					public void run() {
						//String res = mHttp.httpGet(HTTP_URL + re.get().request());
//						mFtp.ftpConnect(FTP_SERVER, FTP_USER_ID, FTP_USER_PWD, FTP_PORT);
//						mFtp.ftpVideoUpload("srcFilePath", "desFileName", "001");
//						mFtp.ftpImageUpload("srcFilePath", "desFileName", "001");
						//BusBuilder.getInstance().post(new ApiResponseEvent(new ApiResponse("Response of ==> " + res)));
						com.setContext(MainService.this);
						BusBuilder.getInstance().post(com.execute(new DbHelper(MainService.this), mHttp, mFtp));
					}
				}.start();					

	}
	
}