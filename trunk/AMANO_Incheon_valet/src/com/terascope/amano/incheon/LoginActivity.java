package com.terascope.amano.incheon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.terascope.amano.R;
import com.terascope.amano.incheon.batch.ApiRequestEvent;
import com.terascope.amano.incheon.batch.ApiResponseEvent;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.batch.MainService;
import com.terascope.amano.incheon.common.AlertView;
import com.terascope.amano.incheon.common.BluetoothPrinterHelper;
import com.terascope.amano.incheon.common.CheckAppHelper;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;
import com.terascope.amano.incheon.dto.ApiRequest;
import com.terascope.amano.incheon.dto.AuthDto;
import com.terascope.amano.incheon.dto.LoginDto;
import com.terascope.amano.incheon.service.AuthCommand;
import com.terascope.amano.incheon.service.CommandResult;
import com.terascope.amano.incheon.service.JsonToDto;
import com.terascope.amano.incheon.service.LoginCommand;
import com.terascope.amano.incheon.utils.NetworkUtils;

public class LoginActivity extends Activity implements OnClickListener {

	private static String TAG = "";
	private static String AUTH = "AUTH";
	private static String LOGIN = "LOGIN";
	
	private BluetoothPrinterHelper bp;
	private BroadcastReceiver mReceiver;
	
	private boolean checkAuth = false;
	
	private EditText et_id, et_pw;
	private Button login_btn;
	private Prefs myprefs = null;

	private LoginDto logindto = new LoginDto();
	private AuthDto authdto = new AuthDto();
	private String strDate, user_id, user_pw, mac_addr;
	
	private CheckAppHelper cah;
	boolean m_close_flag = false;
	
	@SuppressLint({ "SimpleDateFormat", "ShowToast" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		this.myprefs = new Prefs(getApplicationContext());
		
		TAG = this.getClass().getName();
		
		if(myprefs.getMacAdress().equals("") || myprefs.getMacAdress().equals(null)){
			mac_addr = NetworkUtils.getMacAddress(this);
			myprefs.setMacAdress(mac_addr);
			myprefs.save();
		} else {
			mac_addr = myprefs.getMacAdress();
		}
		
		startMainService();
		
		cah = new CheckAppHelper(this);
		
		if(cah.checkCameraTempFile().getRTN_CD() != CommonSet.RESULT_SUCCESS_CODE) {
			AlertView.showError("카메라 인식용 템플릿트 파일이 없습니다.\n관리자에게 문의하세요.", this);
		}
		
		SimpleDateFormat sdfdate = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		
		long now = System.currentTimeMillis();
		Date date = new Date(now);

		strDate = sdfdate.format(date);
		
		et_id = (EditText) findViewById(R.id.et_id);
		et_pw = (EditText) findViewById(R.id.et_pw);
		login_btn = (Button) findViewById(R.id.login_btn);
		
		login_btn.setOnClickListener(this);		
		
	}

	public void getAuth() {
		
		if(myprefs.getAuth().equals("") || myprefs.getAuth().equals(null)) {
			Log.i(TAG,"Auth connect and Send data");
			
			authdto.setMAC_ADRES(mac_addr);
			authdto.setPRG_VER("1");
			BusBuilder.getInstance().post(new AuthCommand(AUTH, authdto));
			checkAuth = true;
		} else {
			checkAuth = true;
			authdto.setAUTH_NO(myprefs.getAuth());
			authdto.setDEVC_NO(myprefs.getDevNo());
		}
	}	      
	
	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
		mReceiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				String msg = intent.getStringExtra("bind");
				Log.i(TAG,"bind >> " + msg);
				getAuth();
			}
		};
		this.registerReceiver(mReceiver, intentFilter);
		
		BusBuilder.getInstance().register(this);
		
	};
	
	
	@Override
	protected void onPause() {
		super.onPause();
		this.unregisterReceiver(this.mReceiver);
		BusBuilder.getInstance().unregister(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//stopMainService();
	}
	
	@Override
	public void onClick(View v) {
		
		if(checkAuth){
			user_id = et_id.getText().toString();
			user_pw = et_pw.getText().toString();
	
			logindto.setMAC_ADRES(mac_addr);
			logindto.setUSR_ID(user_id);
			logindto.setUSR_PWD(user_pw);
			logindto.setLOGIN_DT(strDate);
			
			Log.i(TAG, "MAC ADDRESS >>> " + myprefs.getMacAdress());
	
			BusBuilder.getInstance().post(new LoginCommand("LOGIN", logindto));
			
		} else {
			new AlertView().showError("인증되지 않은 기기 입니다. 관리자에게 문의해 주세요.", this);
		}
		
	}
	
	@Produce
	public ApiRequestEvent produceRequestEvent() {
		return new ApiRequestEvent(new ApiRequest(ApiRequest.INIT));
	}
	
	@Subscribe
	public void onResponseEvent(ApiResponseEvent re) {
		Log.i(this.getClass().getName(), re.get().toString());
		if (ApiRequest.INIT.equals(re.get().toString())) {
			;
		} else {
			
		}
	}

	@SuppressWarnings("static-access")
	@Subscribe
	public void onCommandResult(CommandResult cr) {
		
		Log.i(TAG, cr.getRefId() + " : result json >>> " + cr.getJson());
		
		if(cr.getRefId().equals(AUTH)){
			if(cr.getResultCd() == 0){
				List<AuthDto> list = new JsonToDto<AuthDto>().setJson(cr.getJson()).parse((AuthDto)authdto).getInstances();
				authdto.setAUTH_NO(list.get(0).getAUTH_NO());
				authdto.setDEVC_NO(list.get(0).getDEVC_NO());
				checkAuth = true;
				
				myprefs.setAuth(authdto.getAUTH_NO());
				myprefs.setDevNo(authdto.getDEVC_NO());
				myprefs.save();
			} else {
				checkAuth = false;
				new AlertView().showError("인증되지 않은 기기 입니다. 관리자에게 문의해 주세요.", this);
			}
		} else if(cr.getRefId().equals(LOGIN)){
			if(cr.getResultCd() == 0){
				List<LoginDto> list = new JsonToDto<LoginDto>().setJson(cr.getJson()).parse((LoginDto)logindto).getInstances();
				logindto.setUSR_NM(list.get(0).getUSR_NM());
				
				Bundle bundle = new Bundle();
				bundle.putSerializable(CommonSet.LOGIN_DATA_NAME, logindto);
				bundle.putSerializable(CommonSet.AUTH_DATA_NAME, authdto);
				
				Intent i = new Intent(LoginActivity.this, MainActivity.class);
				i.putExtras(bundle);
				startActivity(i);
				
				stopMainService();
				finish();
				
			} else {
				new AlertView().showError("로그인에 실패하였습니다. 확인 후 다시 시도해 주세요.", this);
			}
		}
		
	}
	
	protected void startMainService() {
		Intent intent = new Intent(getApplicationContext(), MainService.class);
		startService(intent);
		//startService(new Intent("com.hsit.iqsc.IQSClientService"));
	}
	
	protected void stopMainService() {
		stopService(new Intent(getApplicationContext(), MainService.class));
		//stopService(new Intent("com.hsit.iqsc.IQSClientService"));
	}
	
	@SuppressLint("HandlerLeak")
	Handler m_close_handler = new Handler() {
		public void handleMessage(Message msg) {
			m_close_flag = false;
		}
	};
	
	@Override
	public void onBackPressed() {
		

		if (m_close_flag == false) { // Back 키가 첫번째로 눌린 경우

			// 안내 메세지를 토스트로 출력한다.
			new AlertView().showAlert("Back 버튼을 한번 더 터치하시면 앱이 종료됩니다.", this);
			// 상태값 변경
			m_close_flag = true;

			m_close_handler.sendEmptyMessageDelayed(0, 3000);
		//	stopMainService();

		} else { 
			stopMainService();
			super.onBackPressed();
		}

	}

}
