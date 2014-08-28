package com.terascope.amano.incheon.dao.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpHelper {
	
	public String httpGet(String url) {
		Log.i(this.getClass().getName(), url);
		HttpGet get = new HttpGet(url);
		DefaultHttpClient client = new DefaultHttpClient();
		String result = "";
		try {
			result = responseToString(client.execute(get)); //.execute(get, mResHandler);
		} catch (Exception e) {
			
		}
		return result;
	}
	
//	private String mRes;	
//	ResponseHandler<String> mResHandler  = new ResponseHandler<String>() {
//		public String handleResponse(HttpResponse res) {
//			return responseToString(res);
//		}
//	};
	
	private String responseToString(HttpResponse res) {
		StringBuffer json = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
			for(;;) {
				String line = br.readLine();
				if(line == null) break;
				json.append(line);
			}
			br.close();
			
		} catch (Exception e) {
			
		}
		return json.toString();
	}
}
