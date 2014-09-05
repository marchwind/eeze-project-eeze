package com.terascope.amano.incheon.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class cmsHTTP {

	public WebView webview;
	public String mimeType = "text/html";
	public String encoding = "UTF-8";
//	public String encoding = "EUC-KR";
	public int REGISTRATION_TIMEOUT = 5 * 1000;
	public String TAG = "cmsHTTP";
	public Activity act;
	public String noData = "무선인터넷 상태를 점검하십시요.\n 또는 웹서버상태확인 요망.";
	public HttpClient httpClient;

	public cmsHTTP() {

	}

	public cmsHTTP(WebView webviewTmp) {
		webview = webviewTmp;
	}

	public String sendGet(String url) {

		String result = null;
		HttpResponse resp;

		// if (act != null)
		// ((owllab) act.getApplication()).startLoading(act);

		HttpGet httpGet = new HttpGet(url);

		HttpClient httpClient = new DefaultHttpClient();
		// httpClient = ((owllab) act.getApplication()).httpClient;
		HttpParams tmpparms = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(tmpparms,
				REGISTRATION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(tmpparms, REGISTRATION_TIMEOUT);
		ConnManagerParams.setTimeout(tmpparms, REGISTRATION_TIMEOUT);

		try {
			resp = httpClient.execute(httpGet);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (Log.isLoggable(TAG, Log.VERBOSE))
					Log.v(TAG, "Successful authentication");
				HttpEntity respEntity = resp.getEntity();
				if (respEntity != null) {
					InputStream instream = respEntity.getContent();
					result = convertStreamToString(instream);
					instream.close();
				}
			} else {
				if (Log.isLoggable(TAG, Log.VERBOSE))
					Log.v(TAG, "Error Process" + resp.getStatusLine());
			}
		} catch (final IOException e) {
			if (Log.isLoggable(TAG, Log.VERBOSE))
				Log.v(TAG, "IOException when getting authtoken", e);
		} finally {
			if (Log.isLoggable(TAG, Log.VERBOSE))
				Log.v(TAG, "completing");
		}

		// if (act != null)
		// ((owllab) act.getApplication()).endLoading();

		if (result == null) {
			noData = noData + url;
			Toast.makeText(act, noData, Toast.LENGTH_SHORT).show();
		}

		return result;
	}

	// ArrayList<NameValuePair> httpParams = new ArrayList<NameValuePair>();
	// httpParams.add(new BasicNameValuePair("a", "占싼깍옙 "));
	// httpParams.add(new BasicNameValuePair("b", "占싣울옙占쏙옙 OWLCOM"));

	public String sendPostOnly(String url, ArrayList<NameValuePair> params) {

		String result = null;
		HttpResponse resp;
		HttpEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, encoding);
		} catch (final UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader(entity.getContentType());
		httpPost.setEntity(entity);

		Log.v(TAG, entity.getContentType().toString());

		HttpClient httpClient = new DefaultHttpClient();
		// httpClient = ((owllab) act.getApplication()).httpClient;

		HttpParams tmpparms = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(tmpparms,
				REGISTRATION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(tmpparms, REGISTRATION_TIMEOUT);
		ConnManagerParams.setTimeout(tmpparms, REGISTRATION_TIMEOUT);

		try {
			resp = httpClient.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				if (Log.isLoggable(TAG, Log.VERBOSE))
					Log.v(TAG, "Successful authentication");
				HttpEntity respEntity = resp.getEntity();
				if (respEntity != null) {
					InputStream instream = respEntity.getContent();
					result = convertStreamToString(instream);
					instream.close();
				}
			} else {
				if (Log.isLoggable(TAG, Log.VERBOSE))
					Log.v(TAG, "Error Process" + resp.getStatusLine());
			}
		} catch (final IOException e) {
			if (Log.isLoggable(TAG, Log.VERBOSE))
				Log.v(TAG, "IOException when getting authtoken", e);
		} finally {
			if (Log.isLoggable(TAG, Log.VERBOSE))
				Log.v(TAG, "completing");
		}

		if (result == null) {
			noData = noData + url;
			Toast.makeText(act, noData, Toast.LENGTH_SHORT).show();
		}
		return result;
	}

	public String sendPost(String url, ArrayList<NameValuePair> params) {
		String result = null;
		// if (act != null)
		// ((owllab) act.getApplication()).startLoading(act);
		result = sendPostOnly(url, params);
		// if (act != null)
		// ((owllab) act.getApplication()).endLoading();
		return result;
	}

	public void getPost(String url, ArrayList<NameValuePair> params) {

		final String furl = url;
		final ArrayList<NameValuePair> fparams = params;
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					String result = sendPostOnly(furl, fparams);

					webview.getSettings().setJavaScriptEnabled(true);
					webview.getSettings()
							.setJavaScriptCanOpenWindowsAutomatically(true);
//					webview.getSettings().setPluginsEnabled(true);
					// webview.getSettings().setPluginState(
					// WebSettings.PluginState.ON_DEMAND);
					webview.getSettings().setSupportMultipleWindows(true);
					webview.getSettings().setSupportZoom(true);
					webview.getSettings().setBuiltInZoomControls(true);
					webview.getSettings().setBlockNetworkImage(false);
					webview.getSettings().setLoadsImagesAutomatically(true);
					webview.getSettings().setUseWideViewPort(true);
					webview.getSettings().setCacheMode(
							WebSettings.LOAD_NO_CACHE);
					webview.setWebChromeClient(new WebChromeClient());
					webview.clearCache(true);

					String baseUrl = "http://www.parkingmanager.co.kr/android/";
					// String baseUrl = "about:none";
					webview.loadDataWithBaseURL(baseUrl, result, mimeType,
							encoding, baseUrl);
					// webview.loadData(result, mimeType, encoding);

				} catch (Exception e) {
					Log.d("::Exception::", e.toString());
				}
			}
		};

		t.start();
	}

	public String convertStreamToString(InputStream is) {

		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, encoding), 8);
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public void postFile(String url, String filename)
			throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		//httpClient = ((owllab) act.getApplication()).httpClient;
		httpClient.getParams().setParameter(
				CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

		HttpPost httppost = new HttpPost(url);
		File file = new File(filename);

		MultipartEntity mpEntity = new MultipartEntity();
		ContentBody cbFile = new FileBody(file, "image/jpeg");
		mpEntity.addPart("userfile", cbFile);

		// httppost.setEntity(mpEntity);
		System.out.println("executing request " + httppost.getRequestLine());
		HttpResponse response = httpClient.execute(httppost);
		HttpEntity resEntity = response.getEntity();

		if (resEntity != null) {
			System.out.println(EntityUtils.toString(resEntity));
		}
		if (resEntity != null) {
			resEntity.consumeContent();
		}

		// httpClient.getConnectionManager().shutdown();
	}

}