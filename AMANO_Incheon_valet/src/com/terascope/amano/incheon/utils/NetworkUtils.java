package com.terascope.amano.incheon.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class NetworkUtils {
	static ProgressDialog dialog;
	public static String getMacAddress(Context context) {
		ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		final WifiManager mng;
		WifiInfo info;
		boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
		if(isWifi){
			mng = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
			info = mng.getConnectionInfo();
		}
		else{
			mng = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
			
				dialog = new ProgressDialog(context);
				dialog.setTitle("로드중");
				dialog.setMessage("와이파이 통신을 로드중입니다...");
				dialog.setIndeterminate(true);
				dialog.setCancelable(true);
				dialog.show();
				
				new Thread(new Runnable() {
					public void run() {

						try {
							mng.setWifiEnabled(true);
							Thread.sleep(8000);
						} catch (Throwable ex) {
							ex.printStackTrace();
						}
						dialog.dismiss();
					}
				}).start();
				info = mng.getConnectionInfo();
			
		}
		
		return info.getMacAddress().replace(":", "");
	}

	
	public static boolean isWifiConnected(Context context) {
		
		if (context==null) return false;
		
		ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connMgr != null)
		{
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			if ( (networkInfo != null) && (networkInfo.isAvailable() == true) )
			{
	    		if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
	    		{
	    			if ( (networkInfo.getState() == State.CONNECTED) 
	    					|| (networkInfo.getState() == State.CONNECTING) ) 
	    			{
	    				return true;
	    			}
	    		}
			}
		}
		return false;
	}
	
}
