package com.terascope.amano.incheon.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import com.terascope.amano.incheon.dto.ApiRequest;

public class CommonHttp {

	private static final String TAG = "NetworkConnection";
	private byte[] is;
	
	@SuppressLint("NewApi")
	public String getServerConnect(String apiUrl, ApiRequest path) {
		// TODO Auto-generated method stub
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
	      StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	      StrictMode.setThreadPolicy(policy);
	    }
		
		String sendData = CommonSet.Net.ASP_SERVER_URL + apiUrl + path.request();
		
		Log.i(TAG, "request url >> " + sendData);
		
		is = connectToByte(sendData);

		String str = new String(is);
		
		Log.i(TAG, "response Data >> " + str);
		//JSONObject json = new JSONObject(str);
		return str;
		
	}
	
	public byte[] connectToByte(String path) {
		// TODO Auto-generated method stub
		byte[] data = null;
		InputStream istream = connect(path);

		try {
			data = new byte[istream.available()];
			istream.read(data);
		} catch (Exception e) {
		} finally {
			if (istream != null) {
				try {
					istream.close();
				} catch (IOException e) {
				}
			}
		}

		return data;
	}

	
	public InputStream connect(String path) {
		// TODO Auto-generated method stub
		InputStream istream = null;

		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpClient client = new DefaultHttpClient(params);

		HttpParams p = client.getParams();
		HttpConnectionParams.setConnectionTimeout(p, 30000);

		HttpConnectionParams.setSoTimeout(p, 30000);

		HttpProtocolParams.setContentCharset(p, "UTF-8");

	

		HttpGet httpget = new HttpGet(path);

		try {
			HttpResponse response = (HttpResponse) client.execute(httpget);

			HttpEntity entity = response.getEntity();
			BufferedHttpEntity buffer = new BufferedHttpEntity(entity);

			istream = buffer.getContent();
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		} finally {
			client.getConnectionManager().shutdown();
		}

		return istream;
	}


	/*private byte[] is;

	public ArrayList<T> getServerConnect(String path, T dto) {
		is = connectToByte(path, dto);

		String str = new String(is);

		JsonParse jsonParserList = new JsonParse();
		return jsonParserList.jsonParserList(str);
	}

	public static byte[] connectToByte(String path, T dto) {
		byte[] data = null;
		InputStream istream = connect(path, dto);

		try {
			data = new byte[istream.available()];
			istream.read(data);
		} catch (Exception e) {
		} finally {
			if (istream != null) {
				try {
					istream.close();
				} catch (IOException e) {
				}
			}
		}

		return data;
	}

	public static InputStream connect(String path, T dto) {
		InputStream istream = null;

		HttpParams params = new BasicHttpParams();
		params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpClient client = new DefaultHttpClient(params);

		HttpParams p = client.getParams();
		HttpConnectionParams.setConnectionTimeout(p, 30000);

		HttpConnectionParams.setSoTimeout(p, 30000);

		HttpProtocolParams.setContentCharset(p, "UTF-8");

		path += "?" + dto.toString();

		HttpGet httpget = new HttpGet(path);

		try {
			HttpResponse response = (HttpResponse) client.execute(httpget);

			HttpEntity entity = response.getEntity();
			BufferedHttpEntity buffer = new BufferedHttpEntity(entity);

			istream = buffer.getContent();
		} catch (ClientProtocolException e) {
			
		} catch (IOException e) {
			
		} finally {
			client.getConnectionManager().shutdown();
		}

		return istream;
	}
*/
	
	
	
}
