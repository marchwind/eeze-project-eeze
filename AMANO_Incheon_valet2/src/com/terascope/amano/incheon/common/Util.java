/**
 * 
 */
package com.terascope.amano.incheon.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 유틸 클래스
 * 
 * @(#)Util.java
 * @ Copyright 2011 GRLab Corporation. All rights reserved.
 * 
 * @since        : 2011. 6. 8.
 * @author       : kim.sh
 */
public class Util {
	
	
	public Activity act;
	public static final String TAG = "AMANO_UTIL";
	Prefs myprefs = null;
	
	public Util() {
		//mContext = c;
		//myprefs = new Prefs(mContext.getApplicationContext());
		//posPtr = new ESCPOSPrinter("EUC-KR");
	}
	
	public Util(Activity tmpact) {
		act = tmpact;
		myprefs = new Prefs(act.getApplicationContext());
	}
	
	public int getBarcordCheckSum(CharSequence s) {
        int length = s.length();
        int sum = 0;
        for (int i = length - 1; i >= 0; i -= 2) {

            int digit = (int) s.charAt(i) - (int) '0';
            if (digit < 0 || digit > 9) {
               // Throw exception
            }
            sum += digit;
        }
        sum *= 3;
        for (int i = length - 2; i >= 0; i -= 2) {
            int digit = (int) s.charAt(i) - (int) '0';
            if (digit < 0 || digit > 9) {
               // Throw exception
            }
            sum += digit;
        }
        int chkdig = 10 - sum % 10;
        return chkdig;
    }
	
	/**
	 * 이미지 오버레이 변환
	 * @param bmp1 원본 이미지
	 * @param bmp2 오버레이 이미지
	 * @param left 왼쪽에서 위치
	 * @param top 위에서 위치
	 * @return 오버레이된 bitmap 이미지
	 */
	public static Bitmap getBitmapOverlay(ImageView iv, Bitmap bmp1, Bitmap bmp2, int left, int top) {
		
		float l = ((float)bmp1.getWidth()/iv.getWidth()) * left;
		float t = ((float)bmp1.getHeight()/iv.getHeight()) * top;
		
		Log.i(TAG,"image touch position x : " + left + ", y : " + top + "and ratio x : " + l + ", y : " + t);
		
	    Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(),  bmp1.getConfig());
	    Canvas canvas = new Canvas(bmOverlay);    
	    canvas.drawBitmap(bmp1, 0, 0, null);
	    canvas.drawBitmap(bmp2, l, t, null);
	    return bmOverlay;
	}
	
	/**
	 * 이미지 사이즈 변환
	 *
	 * @param Context context 컨텍스트
	 * @param Bitmap inBtm 원본사이즈
	 * @param int resizeWith 리사이즈 가로넓이
	 * @param int resizeHight 리사이즈 세로넓이
	 * 
	 * @return Bitmap 변환 후 이미지 비트맵
	 */
	public static Bitmap resizeImage(Bitmap inBtm, int resizeWith, int resizeHight) {
		
		int width = inBtm.getWidth();
		int height = inBtm.getHeight();
		int newWidth = resizeWith;
		int newHeight = resizeHight;
		
		float scaleWidth = ((float) newWidth) / width; 
		float scaleHeight = ((float) newHeight) / height; 
		
		Matrix matrix = new Matrix(); 
		matrix.postScale(scaleWidth, scaleHeight); 
		
		return Bitmap.createBitmap(inBtm, 0, 0, width,  height, matrix, true); 
	}
	
	/**
	 * Uri를 비트맵 이미지로 변환
	 *
	 * @param Context context 컨텍스트
	 * @param Uri inUri
	 * 
	 * @return Bitmap 변환 후 이미지 비트맵
	 */
	public Bitmap convertBitmap(Context context, Uri inUri) {
		Bitmap bm = null;
		try {
			bm = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(inUri), null, new BitmapFactory.Options());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bm;
	}
	
	/**
	 * file를 비트맵 이미지로 변환
	 *
	 * @param Context context 컨텍스트
	 * @param Uri inUri
	 * 
	 * @return Bitmap 변환 후 이미지 비트맵
	 */
	public Bitmap convertBitmap(String Path) throws Exception {
		Bitmap bm = null;
		File f = new File(Path);
		if(!isFileExist(f)){
			throw new FileNotFoundException();
		} else {
			bm = BitmapFactory.decodeFile(Path);
		}
		return bm;
	}
	
	public Bitmap convertBitmap(Context context, int id) throws Exception {
		Bitmap bm = null;
		bm = BitmapFactory.decodeResource(context.getResources(), id);
		
		return bm;
	}
	
	/**
	 * 이미지 정 사이즈 변환
	 *
	 * @param Context context 컨텍스트
	 * @param Bitmap inBtm 원본사이즈
	 * @param int resizeWith 리사이즈 가로넓이
	 * @param int resizeHight 리사이즈 세로넓이
	 * 
	 * @return Bitmap 변환 후 이미지 비트맵
	 */
	public static Bitmap resizeScaledImage(Bitmap inBtm, int resizeWidth, int resizeHeight) {
		
		Bitmap reBitmap = null;
		
		if (inBtm.getWidth() < inBtm.getHeight()) {
		
			Matrix matrix = new Matrix();
			matrix.postRotate(90);
			reBitmap = Bitmap.createBitmap(inBtm, 0, 0, inBtm.getWidth(), inBtm.getHeight(), matrix, true); 
		
		} else {
			reBitmap = inBtm;
		} 
		
		double currentWidth = reBitmap.getWidth();
		double currentHeight = reBitmap.getHeight();
		
		
		int dstWidth = 0;
		int dstHeight = 0;
		
		Bitmap outBtm = null;
	
		if (currentWidth > resizeWidth && currentHeight > resizeHeight) {
			if (currentWidth >= currentHeight) {
				dstWidth = resizeWidth;
				dstHeight = (int)(resizeWidth * (currentHeight / currentWidth));
			} else {
				dstHeight = resizeHeight;
				dstWidth = (int)(resizeHeight * (currentWidth / currentHeight));
			}
			outBtm = Bitmap.createScaledBitmap(reBitmap, dstWidth, dstHeight, true);
		} else {
			outBtm = reBitmap;
		}
		
		return outBtm; 
	}
	
	public static Bitmap getRoundedCornerBitmap(Context context, Bitmap bitmap , int roundLevel) { 
	    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888); 
	    Canvas canvas = new Canvas(output); 
	 
	    final int color = 0xff424242; 
	    final Paint paint = new Paint(); 
	    final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); 
	    final RectF rectF = new RectF(rect); 
	    final float roundPx = convertDipsToPixels(context, roundLevel); 
	 
	    paint.setAntiAlias(true); 
	    canvas.drawARGB(0, 0, 0, 0); 
	    paint.setColor(color); 
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint); 
	 
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
	    canvas.drawBitmap(bitmap, rect, rect, paint); 
	 
	    return output; 
	} 
	 
	public static int convertDipsToPixels(Context context, int dips) { 
	    final float scale = context.getResources().getDisplayMetrics().density; 
	    return (int) (dips * scale + 0.5f); 
	}
	
	
	public HashMap<String, String> calcPrice(HashMap<String, String> hmCart) {
		HashMap<String, String> hm = new HashMap<String, String>();
		int totalPrice = 0;
		int orgPrice = 0;
		int deliveryPrice = 0;

		String deliveryPriceLabel = "※ 배송료 : ";
		String totalPriceLabel = "※ 합계금액 : ";
		String priceEnd = "원";

		for (int i = 0; i < str2int(hmCart.get("count"), 0); i++) {
			int priceTmp = str2int(hmCart.get("price[" + i + "]"), 0);
			int eaTmp = str2int(hmCart.get("ea[" + i + "]"), 0);
			totalPrice += priceTmp * eaTmp;
		}
		orgPrice = totalPrice;
		if (totalPrice < 50000 && totalPrice > 0)
			deliveryPrice = 4000;
		if (totalPrice > 0)
			totalPrice += deliveryPrice;

		String deliveryPriceText = deliveryPriceLabel
				+ number_format(deliveryPrice) + priceEnd;
		String totalPriceText = totalPriceLabel + number_format(totalPrice)
				+ priceEnd;
		String payPriceText = number_format(totalPrice) + priceEnd;

		hm.put("deliveryPriceText", deliveryPriceText);
		hm.put("totalPriceText", totalPriceText);
		hm.put("deliveryPrice", Integer.toString(deliveryPrice));
		hm.put("totalPrice", Integer.toString(totalPrice));
		hm.put("payPriceText", payPriceText);
		hm.put("orgPrice", Integer.toString(orgPrice));
		return hm;
	}

	public HashMap<String, String> cursor2HashMap(Cursor cursor) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("count", "0");
		cursor.getColumnNames();
		try {
			int i = 0;
			while (cursor.moveToNext()) {
				for (int j = 0; j < cursor.getColumnCount(); j++) {
					String fieldName = cursor.getColumnName(j);
					String fieldValue = cursor.getString(j);
					hm.put(fieldName + "[" + i + "]", fieldValue);
				}// for j
				i++;
			}// while
			hm.put("count", Integer.toString(i));
		} catch (Exception e) {
			Log.e("cursor2HashMap", e.getMessage());
		}
		return hm;
	}

	public void sendSMS(String destinationAddress, String text) {
		Log.v(TAG, "sendSMS:" + destinationAddress);
		if (!PhoneNumberUtils.isWellFormedSmsAddress(destinationAddress)) {
			return;
		}
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(destinationAddress, null, text, null, null);
	}

	public void sendSMSWithState(String destinationAddress, String text) {
		Log.v(TAG, "sendSMSWithState:" + destinationAddress);
		Log.v(TAG, "text:" + text);
		if (act == null)
			return;
		if (!PhoneNumberUtils.isWellFormedSmsAddress(destinationAddress)) {
			Toast.makeText(act.getBaseContext(), "휴대전화번호가 올바르지 않습니다.",
					Toast.LENGTH_LONG).show();
			return;
		}

		String SENT = "SMS_SENT";
		String DELIVERED = "SMS_DELIVERED";

		PendingIntent sentIntents = PendingIntent.getBroadcast(act, 0,
				new Intent(SENT), 0);

		PendingIntent deliveryIntents = PendingIntent.getBroadcast(act, 0,
				new Intent(DELIVERED), 0);

		act.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(act.getBaseContext(), "SMS를 발송했습니다.",
							Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(act.getBaseContext(), "오류: 일반오류가 발생했습니다.",
							Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(act.getBaseContext(), "오류: 서비스 오류입니다.",
							Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(act.getBaseContext(),
							"오류: PDU가 제공되지 않았습니다.", Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(act.getBaseContext(), "오류: Radio off상태입니다.",
							Toast.LENGTH_LONG).show();
					break;
				}
			}
		}, new IntentFilter(SENT));

		act.registerReceiver(new BroadcastReceiver() {
			@Override
			public void onReceive(Context arg0, Intent arg1) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(act.getBaseContext(), "SMS를 발송했습니다.",
							Toast.LENGTH_LONG).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(act.getBaseContext(), "SMS가 발송되지 않았습니다.",
							Toast.LENGTH_LONG).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED));

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(destinationAddress, null, text, sentIntents,
				deliveryIntents);
	}

	public void sendSMSForm(String destinationAddress, String text) {
		Log.v(TAG, "sendSMSForm:" + destinationAddress);
		gotoSMS(destinationAddress, text);
	}

	public void gotoSMS(String destinationAddress, String text) {
		Log.v(TAG, "gotoSMS:" + destinationAddress);
		if (act == null)
			return;
		Uri uri = Uri.parse("smsto:" + destinationAddress);
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra("sms_body", text);
		act.startActivity(intent);
	}

	public void gotoTel(String destinationAddress) {
		Log.v(TAG, "gotoTel:" + destinationAddress);
		if (act == null)
			return;
		Uri uri = Uri.parse("tel:" + destinationAddress);
		Intent intent = new Intent(Intent.ACTION_DIAL, uri);
		act.startActivity(intent);
	}

	public void gotoEmail(String destinationAddress) {
		Log.v(TAG, "gotoEmail:" + destinationAddress);
		if (act == null)
			return;
		Uri uri = Uri.parse("mailto:" + destinationAddress);
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		act.startActivity(intent);
	}

	public void gotoEmail(String destinationAddress, String subject) {
		Log.v(TAG, "gotoEmail:" + destinationAddress);
		if (act == null)
			return;
		Uri uri = Uri.parse("mailto:" + destinationAddress);
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		intent.putExtra(Intent.EXTRA_EMAIL, destinationAddress);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		// intent.putExtra(Intent.EXTRA_TEXT, text);
		intent.setType("text/plain");
		act.startActivity(Intent.createChooser(intent, "이메일을 선택하세요."));
	}

	public void gotoWeb(String destinationAddress) {
		Log.v(TAG, "gotoWeb:" + destinationAddress);
		if (act == null)
			return;
		Uri uri = Uri.parse("http://" + destinationAddress);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		act.startActivity(intent);
	}

	public void gotoMap(String destinationAddress) {
		Log.v(TAG, "gotoMap:" + destinationAddress);
		if (act == null)
			return;
		HashMap<String, String> hm = new HashMap<String, String>();
		hm = searchGPS(destinationAddress);
		Uri uri = Uri.parse("geo:" + hm.get("latitude[0]") + ","
				+ hm.get("longitude[0]"));
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		act.startActivity(intent);
	}

	public void gotoWay(String fromAddress, String toAddress) {
		Log.v(TAG, "gotoWay:" + fromAddress + " -> " + toAddress);
		if (act == null)
			return;
		HashMap<String, String> hmFrom = new HashMap<String, String>();
		HashMap<String, String> hmTo = new HashMap<String, String>();
		hmFrom = searchGPS(fromAddress);
		hmTo = searchGPS(toAddress);
		Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr="
				+ hmFrom.get("latitude[0]") + "%20"
				+ hmFrom.get("longitude[0]") + "&daddr="
				+ hmTo.get("latitude[0]") + "%20" + hmTo.get("longitude[0]")
				+ "&hl=ko");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		act.startActivity(intent);
	}

	public HashMap<String, String> findMyLocation() {
		Log.v(TAG, "findMyLocation:");
		HashMap<String, String> hm = new HashMap<String, String>();
		if (act == null)
			return hm;

		LocationManager LocMan = (LocationManager) act
				.getSystemService(Context.LOCATION_SERVICE);
		List<String> arProvider = LocMan.getProviders(false);
		for (int i = 0; i < arProvider.size(); i++) {
			Log.v(TAG, "Provider " + i + " : " + arProvider.get(i));
		}

		Criteria crit = new Criteria();
		crit.setAccuracy(Criteria.NO_REQUIREMENT);
		crit.setPowerRequirement(Criteria.NO_REQUIREMENT);
		crit.setAltitudeRequired(false);
		crit.setCostAllowed(false);
		String best = LocMan.getBestProvider(crit, true);
		Log.v(TAG, "best provider : " + best);

		Location loc = LocMan.getLastKnownLocation(best);
		if (loc != null) {
			Log.v(TAG, "latitude:" + Double.toString(loc.getLatitude()));
			Log.v(TAG, "longitude:" + Double.toString(loc.getLongitude()));

			hm.put("latitude", Double.toString(loc.getLatitude()));
			hm.put("longitude", Double.toString(loc.getLongitude()));
		}
		return hm;
	}

	public HashMap<String, String> hmMyLocation = new HashMap<String, String>();

	public void gotoWayFromMe(String toAddress) {
		Log.v(TAG, "gotoWay:" + toAddress);
		if (act == null)
			return;
		HashMap<String, String> hmFrom = new HashMap<String, String>();
		HashMap<String, String> hmTo = new HashMap<String, String>();

		hmFrom = hmMyLocation;
		hmTo = searchGPS(toAddress);
		Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr="
				+ hmFrom.get("latitude") + "%20" + hmFrom.get("longitude")
				+ "&daddr=" + hmTo.get("latitude") + "%20"
				+ hmTo.get("longitude") + "&hl=ko");
		Log.v(TAG, "uri:" + uri.toString());
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		act.startActivity(intent);
	}

	// http://maps.google.com/maps?f=d&saddr=37.566535%20126.977969&daddr=37.5237762%20127.0224608&hl=ko
	public HashMap<String, String> searchGPS(String query) {
		Log.v(TAG, "searchGPS:" + query);
		HashMap<String, String> hm = new HashMap<String, String>();
		if (act == null)
			return hm;
		String latitude = "37.566535";
		String longitude = "126.977969";
		String queryEnc = "";
		cmsHTTP cmsHttp = new cmsHTTP();
		// cmsHttp.encoding = encoding;
		cmsHttp.act = act;
		try {
			queryEnc = URLEncoder.encode(query, cmsHttp.encoding);
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, e.getMessage());
		}
		String theUrl = "http://maps.google.co.kr/maps/api/geocode/json?sensor=true&address="
				+ queryEnc;
		// String theUrl =
		// "http://maps.google.co.kr/maps/api/geocode/xml?sensor=true&address="+queryEnc;
		Log.i(TAG, theUrl);
		String tmpData = cmsHttp.sendGet(theUrl);
		if (tmpData == null)
			return hm;
		Log.i(TAG, tmpData);
		try {
			JSONObject jObj = new JSONObject(tmpData);
			Log.i(TAG, jObj.toString());
			if (jObj != null) {
				latitude = jObj.getJSONArray("results").getJSONObject(0)
						.getJSONObject("geometry").getJSONObject("location")
						.getString("lat");
				longitude = jObj.getJSONArray("results").getJSONObject(0)
						.getJSONObject("geometry").getJSONObject("location")
						.getString("lng");
				Log.i(TAG, "latitude : " + latitude);
				Log.i(TAG, "longitude : " + longitude);
			}
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
		}
		hm.put("latitude", latitude);
		hm.put("longitude", longitude);
		return hm;
	}

	public HashMap<String, String> getAdminInfo() {
		Log.v(TAG, "getAdminInfo:");
		HashMap<String, String> hmAdmin = new HashMap<String, String>();
		if (act == null)
			return hmAdmin;

		String theUrl = "http://www.owllab.com/android/admin_info.php";
		Log.i(act.getLocalClassName(), theUrl);
		ArrayList<NameValuePair> httpParams = new ArrayList<NameValuePair>();
		httpParams.add(new BasicNameValuePair("mode", "admin_tel"));
		cmsHTTP cmsHttp = new cmsHTTP();
		// cmsHttp.encoding = encoding;
		cmsHttp.act = act;
		String tmpData = cmsHttp.sendPost(theUrl, httpParams);
		if (tmpData == null)
			return hmAdmin;
		hmAdmin = xml2HashMap(tmpData, cmsHttp.encoding);
		Log.v(act.getLocalClassName(), tmpData);
		return hmAdmin;
	}
/*
	public String getAuthID(Activity act) {
		String tmp = "";
		HashMap<String, String> hm = ((owllab) act.getApplication()).authHM;
		tmp = null2empty(hm.get("id[0]"));
		return tmp;
	}

	public boolean getLoginState(Activity act) {
		boolean tmp = false;
		if (getAuthID(act).length() > 0)
			tmp = true;
		return tmp;
	}

	public int getAuthLevel(Activity act) {
		int tmp = -1;
		HashMap<String, String> hm = ((owllab) act.getApplication()).authHM;
		tmp = str2int(hm.get("level[0]"), -1);
		return tmp;
	}

	public HashMap<String, String> getAuthHM(Activity act) {
		HashMap<String, String> hm = ((owllab) act.getApplication()).authHM;
		return hm;
	}

	public void setAuthHM(Activity act, HashMap<String, String> hm) {
		((owllab) act.getApplication()).authHM = hm;
	}
*/
	public InputFilter filterAlphaNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			Pattern ps = Pattern.compile("^[a-zA-Z0-9]+$");
			if (!ps.matcher(source).matches()) {
				return "";
			}
			return null;
		}
	};

	public InputFilter filterJavaLetterOrDigit = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			for (int i = start; i < end; i++) {
				if (!Character.isJavaLetterOrDigit(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};

	public InputFilter filterLetterNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {

			// Pattern ps=Pattern.compile("[a-zA-Z0-9가-R]*");
			// if (!ps.matcher(source).matches()) {
			// return "";
			// }
			for (int i = start; i < end; i++) {
				if (!Character.isLetterOrDigit(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};

	public InputFilter filterLetter = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			for (int i = start; i < end; i++) {
				if (!Character.isLetter(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};

	public InputFilter filterNum = new InputFilter() {
		public CharSequence filter(CharSequence source, int start, int end,
				Spanned dest, int dstart, int dend) {
			for (int i = start; i < end; i++) {
				if (!Character.isDigit(source.charAt(i))) {
					return "";
				}
			}
			return null;
		}
	};

	public String getMyPhoneNumber(Activity act) {
		TelephonyManager mTelephonyMgr;
		mTelephonyMgr = (TelephonyManager) act
				.getSystemService(Context.TELEPHONY_SERVICE);
		String tmp = mTelephonyMgr.getLine1Number();
		if (tmp == null)
			tmp = "";
		return tmp;
	}

	public String getEditTextVal(Activity act, int RItem) {
		String tmp = ((EditText) act.findViewById(RItem)).getText().toString();
		if (tmp == null)
			tmp = "";
		return tmp;
	}

	public EditText getEditText(Activity act, int RItem) {
		return ((EditText) act.findViewById(RItem));
	}

	public void goActivity(Context mContext, String menuClass) {
		String findClass = "";
		Class<?> cls;
		Activity actCategory;
		try {
			cls = Class.forName(menuClass);
			actCategory = (Activity) cls.newInstance();
			Intent intent = new Intent(mContext, actCategory.getClass());
			mContext.startActivity(intent);
			findClass = cls.getName();
		} catch (ClassNotFoundException e) {
			findClass = "";
			AlertDialog dialog = new AlertDialog.Builder(mContext).create();
			dialog.setTitle("안내");
			dialog.setMessage("죄송합니다.\n페이지를 찾을 수 없습니다.");
			dialog.setButton("확인", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			dialog.show();
		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
	}

	public Activity findAct(Context mContext, String menuClass) {
		String foundClass = "";
		Class<?> cls;
		Activity actCategory = null;
		try {
			cls = Class.forName(menuClass);
			actCategory = (Activity) cls.newInstance();
			foundClass = cls.getName();
		} catch (ClassNotFoundException e) {
			foundClass = "";
			AlertDialog dialog = new AlertDialog.Builder(mContext).create();
			dialog.setTitle("안내");
			dialog.setMessage("죄송합니다.\n페이지를 찾을 수 없습니다.");
			dialog.setButton("확인", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
			dialog.show();
		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		}
		return actCategory;
	}

	public HashMap<String, String> xml2HashMap(String tmpData, String encoding) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("count", "0");
		try {
			DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = docBF.newDocumentBuilder();
			InputStream is = new ByteArrayInputStream(
					tmpData.getBytes(encoding));
			Document doc = docB.parse(is);
			Element lists = doc.getDocumentElement();
			NodeList dataList = lists.getElementsByTagName("data");
			int c = 0;
			for (int i = 0; i < dataList.getLength(); i++) {
				NodeList dataNodeList = dataList.item(i).getChildNodes();
				for (int j = 0; j < dataNodeList.getLength(); j++) {
					;
					Node itemNode = dataNodeList.item(j);
					if (itemNode.getFirstChild() != null) {
						String nodeName = itemNode.getNodeName();
						String nodeValue = itemNode.getFirstChild()
								.getNodeValue();
						hm.put(nodeName + "[" + i + "]", nodeValue);
					}
				}// for j
				c++;
			}// for i
			hm.put("count", Integer.toString(c));
		} catch (Exception e) {
			Log.e("com.cms.app.util.xml2HashMap", e.getMessage());
		}
		return hm;
	}

	public int str2int(String txt, int mydefault) {
		int num = 0;
		if (txt == null || "".equals(txt)) {
			num = mydefault;
		} else {
			try {
				num = Integer.parseInt(txt);
			} catch (NumberFormatException e) {
				Log.e(TAG, e.toString());
			}
		}
		return num;
	}

	public int str2int(String txt) {
		int num = 0;
		if (txt == null || "".equals(txt)) {

		} else {
			num = double2int(txt);
		}
		return num;
	}

	public int double2int(double val) {
		int tmp = 0;
		Double d = new Double(val);
		tmp = d.intValue();
		return tmp;
	}

	public int double2int(String val) {
		int tmp = 0;
		Double d = new Double(val);
		tmp = d.intValue();
		return tmp;
	}

	public double str2double(String txt) {
		double num = 0;
		if (txt == null || "".equals(txt)) {
			num = 0.0;
		} else {
			num = Double.valueOf(txt).doubleValue();
		}
		return num;
	}

	public long str2long(String txt) {
		long num = 0;
		if (txt == null || "".equals(txt)) {
			num = 0;
		} else {
			num = Long.valueOf(txt).longValue();
		}
		return num;
	}

	public String str_replace(String src, String des, String org) {
		int fromindex = 0;
		int toindex = 0;
		String replaced = "";
		int i = 0;
		if ("".equals(src) || src == null) {
			replaced = org;
		} else {
			while (fromindex >= 0) {
				if (i == 0) {
					toindex = org.indexOf(src, 0);
					if (toindex < 0) {
						replaced = org.substring(0, org.length());
						break;
					} else {
						replaced = org.substring(0, toindex);
						replaced += des;
					}
				} else {
					toindex = org.indexOf(src, fromindex + src.length());
					if (toindex < 0) {
						replaced += org.substring(fromindex + src.length(),
								org.length());
						break;
					} else {
						replaced += org.substring(fromindex + src.length(),
								toindex);
						replaced += des;
					}
				}
				fromindex = toindex;
				i++;
			}
		}// if
		return replaced;
	}

	public String str_replace_i(String src, String des, String org) {

		String org_upper = org.toUpperCase();
		String src_upper = src.toUpperCase();
		int fromindex = 0;
		int toindex = 0;
		String replaced = "";
		int i = 0;
		if ("".equals(src) || src == null) {
			replaced = org;
		} else {
			while (fromindex >= 0) {
				if (i == 0) {
					toindex = org_upper.indexOf(src_upper, 0);
					if (toindex < 0) {
						replaced = org.substring(0, org_upper.length());
						break;
					} else {
						replaced = org.substring(0, toindex);
						replaced += des;
					}
				} else {
					toindex = org_upper.indexOf(src_upper,
							fromindex + src.length());
					if (toindex < 0) {
						replaced += org.substring(fromindex + src.length(),
								org.length());
						break;
					} else {
						replaced += org.substring(fromindex + src.length(),
								toindex);
						replaced += des;
					}
				}
				fromindex = toindex;
				i++;
			}
		}
		return replaced;

	}

	public String null2empty(String str) {
		if (str == null)
			str = "";
		return str;
	}

	public String[] explode_trim(String src, String org) {

		String[] tmpa = explode(src, org);
		String[] tmpb;
		if (tmpa != null) {
			tmpb = new String[tmpa.length];
			for (int i = 0; i < tmpb.length; i++) {
				tmpb[i] = tmpa[i].trim();
			}// for i
			return tmpb;
		}
		return tmpa;
	}

	public String[] explode_trim(String src, String org, int limit) {

		String[] tmpa = explode(src, org);
		String[] tmpb = new String[limit];
		for (int i = 0; i < limit; i++) {
			if (i >= tmpa.length) {
				tmpb[i] = "";
			} else {
				tmpb[i] = tmpa[i].trim();
			}// if
		}// for i
		return tmpb;
	}

	public String[] explode_trim(String src, String org, int limit, int didx) {

		String[] tmpa = explode(src, org);
		String[] tmpb = new String[limit];
		for (int i = 0; i < limit; i++) {
			if (i >= tmpa.length) {
				tmpb[i] = tmpa[didx];
			} else {
				tmpb[i] = tmpa[i].trim();
			}// if
		}// for i
		return tmpb;
	}

	public String[] explode(String src, String org, int limit) {

		String[] tmpa = explode(src, org);
		String[] tmpb = new String[limit];
		for (int i = 0; i < limit; i++) {
			if (i >= tmpa.length) {
				tmpb[i] = "";
			} else {
				tmpb[i] = tmpa[i];
			}// if
		}// for i
		return tmpb;
	}

	public String[] explode(String src, String org, int limit, int didx) {

		String[] tmpa = explode(src, org);
		String[] tmpb = new String[limit];
		for (int i = 0; i < limit; i++) {
			if (i >= tmpa.length) {
				tmpb[i] = tmpa[didx];
			} else {
				tmpb[i] = tmpa[i];
			}// if
		}// for i
		return tmpb;
	}

	public String[] explode(String src, String org) {
		int fromindex = 0;
		int toindex = 0;
		int i = 0;
		Vector<String> v = new Vector<String>();
		if ("".equals(src) || src == null) {
			v.addElement(new String(org));
		} else {
			while (fromindex >= 0) {
				if (i == 0) {
					toindex = org.indexOf(src, 0);
					if (toindex < 0) {
						v.addElement(new String(org.substring(0, org.length())));
						break;
					} else {
						v.addElement(new String(org.substring(0, toindex)));
					}
				} else {
					toindex = org.indexOf(src, fromindex + src.length());
					if (toindex < 0) {
						v.addElement(new String(org.substring(
								fromindex + src.length(), org.length())));
						break;
					} else {
						v.addElement(new String(org.substring(
								fromindex + src.length(), toindex)));
					}
				}
				fromindex = toindex;
				i++;
			}
		}
		// Object[] myarray = v.toArray();
		String[] myarray = new String[v.size()];
		for (i = 0; i < myarray.length; i++) {
			myarray[i] = (String) v.elementAt(i);
		}// for i
		return myarray;
	}

	public String number_format(int mynum) {
		String tmp = "";
		int orgnum = mynum;
		if (orgnum < 0)
			mynum = mynum * (-1);
		tmp = Integer.toString(mynum);

		StringBuffer bufnum = new StringBuffer(tmp);
		bufnum.reverse();
		int n = bufnum.length();
		int a = 0;
		for (int i = 3; i < n; i = i + 3) {
			if (n > i) {
				bufnum.insert(i + a, ",");
			}// if
			a++;
		}// for
		bufnum.reverse();
		String rtn = "";
		if (orgnum < 0)
			rtn += "-";
		rtn += bufnum + "";
		return rtn;
	}

	public String number_format(String mynum) {
		return number_format(str2int(mynum));
	}

	public String number_format(String mynum, int period) {
		String rownum = "";
		String extnum = "";
		double dnum = Double.parseDouble(mynum);
		if (mynum.compareTo(".") < 0) {
			rownum = mynum;
		} else {
			if (period <= 0) {
				dnum = Math.round(dnum);
				String strnum = Double.toString(dnum);
				String lastnum = "";
				if (strnum.indexOf("E") >= 0) {
					int e = Integer.parseInt(strnum.substring(strnum
							.indexOf("E") + 1));
					lastnum = strnum.substring(0, strnum.indexOf("."))
							+ strnum.substring(strnum.indexOf(".") + 1,
									strnum.indexOf("E"));
				} else {
					lastnum = strnum;
				}
				rownum = lastnum;
			} else {
				for (int i = 0; i < period; i++)
					dnum = dnum * 10;
				dnum = Math.round(dnum);
				for (int i = 0; i < period; i++)
					dnum = dnum * 0.1;
				String strnum = Double.toString(dnum);
				String lastnum = "";
				if (strnum.indexOf("E") >= 0) {
					int e = Integer.parseInt(strnum.substring(strnum
							.indexOf("E") + 1));
					lastnum = strnum.substring(0, strnum.indexOf("."))
							+ strnum.substring(strnum.indexOf(".") + 1, e + 2)
							+ "."
							+ strnum.substring(e + 2, strnum.indexOf("E"));
				} else {
					lastnum = strnum;
				}
				lastnum = lastnum.substring(0, lastnum.indexOf(".") + period
						+ 1);

				String[] mynuma = explode(".", lastnum);
				rownum = mynuma[0];
				extnum = "." + mynuma[1];
			}

		}
		StringBuffer bufnum = new StringBuffer(rownum);
		bufnum.reverse();
		int n = bufnum.length();
		int a = 0;
		for (int i = 3; i < n; i = i + 3) {
			if (n > i) {
				bufnum.insert(i + a, ",");
			}// if
			a++;
		}// for
		bufnum.reverse();
		return bufnum + extnum;
	}
	
	public Long dateStringToLong(String value) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try {
			d = df.parse(value); // value = "2011-09-12 13:32:53"
//			Log.e("maluchi", "Load Date :"+d.getTime()+", "+d.toLocaleString());
		}
		catch (ParseException e) {
			d = new Date();
		}
		Long time = d.getTime();
		return time;
	}
	
	
	 /**
     * 디렉토리 생성 
     * @return dir
     */
	public File makeDirectory(String dir_path){
        File dir = new File(dir_path);
        if (!dir.exists())
        {
            dir.mkdirs();
            Log.i( TAG , "!dir.exists" );
        }else{
            Log.i( TAG , "dir.exists" );
        }
 
        return dir;
    }
 
    /**
     * 파일 생성
     * @param dir
     * @return file 
     */
	public File makeFile(File dir , String file_path){
        File file = null;
        boolean isSuccess = false;
        if(dir.isDirectory()){
            file = new File(file_path);
            if(file!=null&&!file.exists()){
                Log.i( TAG , "!file.exists" );
                try {
                    isSuccess = file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally{
                    Log.i(TAG, "파일생성 여부 = " + isSuccess);
                }
            }else{
                Log.i( TAG , "file.exists" );
            }
        }
        return file;
    }
 
    /**
     * (dir/file) 절대 경로 얻어오기
     * @param file
     * @return String
     */
	public String getAbsolutePath(File file){
        return ""+file.getAbsolutePath();
    }
 
    /**
     * (dir/file) 삭제 하기
     * @param file
     */
	public boolean deleteFile(File file){
        boolean result;
        if(file!=null&&file.exists()){
            file.delete();
            result = true;
        }else{
            result = false;
        }
        return result;
    }
 
 
	public boolean deleteStringFile(String PathFileName){
        boolean result;
        File file = new File(PathFileName);
        if(file!=null&&file.exists()){
            file.delete();
            result = true;
        }else{
            result = false;
        }
        return result;
    }
 
	
	
    /**
     * 파일여부 체크 하기
     * @param file
     * @return
     */
	public boolean isFile(File file){
        boolean result;
        if(file!=null&&file.exists()&&file.isFile()){
            result=true;
        }else{
            result=false;
        }
        return result;
    }
 
    /**
     * 디렉토리 여부 체크 하기
     * @param dir
     * @return
     */
	public boolean isDirectory(File dir){
        boolean result;
        if(dir!=null&&dir.isDirectory()){
            result=true;
        }else{
            result=false;
        }
        return result;
    }
 
    /**
     * 파일 존재 여부 확인 하기
     * @param file
     * @return
     */
	public boolean isFileExist(File file){
        boolean result;
        if(file!=null&&file.exists()){
            result=true;
        }else{
            result=false;
        }
        return result;
    }
     
    /**
     * 파일 이름 바꾸기
     * @param file
     */
	public boolean reNameFile(File file , File new_name){
        boolean result;
        if(file!=null&&file.exists()&&file.renameTo(new_name)){
            result=true;
        }else{
            result=false;
        }
        return result;
    }
     
    /**
     * 디렉토리에 안에 내용을 보여 준다.
     * @param file
     * @return
     */
	public String[] getList(File dir){
        if(dir!=null&&dir.exists())
            return dir.list();
        return null;
    }
 
    /**
     * 파일에 내용 쓰기
     * @param file
     * @param file_content
     * @return
     */
	public boolean writeFile(File file , byte[] file_content){
        boolean result;
        FileOutputStream fos;
        if(file!=null&&file.exists()&&file_content!=null){
            try {
                fos = new FileOutputStream(file);
                try {
                    fos.write(file_content);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            result = true;
        }else{
            result = false;
        }
        return result;
    }
 
    /**
     * 파일 읽어 오기 
     * @param file
     */
	public void readFile(File file){
        int readcount=0;
        if(file!=null&&file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                readcount = (int)file.length();
                byte[] buffer = new byte[readcount];
                fis.read(buffer);
                for(int i=0 ; i<file.length();i++){
                    Log.d(TAG, ""+buffer[i]);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
     
    /**
     * 파일 복사
     * @param file
     * @param save_file
     * @return
     */
	public boolean copyFile(File file , String save_file){
        boolean result;
        if(file!=null&&file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                FileOutputStream newfos = new FileOutputStream(save_file);
                int readcount=0;
                byte[] buffer = new byte[1024];
                while((readcount = fis.read(buffer,0,1024))!= -1){
                    newfos.write(buffer,0,readcount);
                }
                newfos.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = true;
        }else{
            result = false;
        }
        return result;
    }	
	
	
}
