package com.terascope.amano.incheon;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import makemachine.android.examples.ParkInProcess_CameraLPR;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.sewoo.port.android.BluetoothPort;
import com.squareup.otto.Subscribe;
import com.terascope.amano.R;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.batch.MainService;
import com.terascope.amano.incheon.common.AlertView;
import com.terascope.amano.incheon.common.BluetoothPrinterHelper;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dto.AncmDto;
import com.terascope.amano.incheon.dto.AuthDto;
import com.terascope.amano.incheon.dto.CarInfoDto;
import com.terascope.amano.incheon.dto.LoginDto;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.service.AncmCommand;
import com.terascope.amano.incheon.service.CarInfoCommand;
import com.terascope.amano.incheon.service.CommandResult;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity implements OnClickListener {

	public static String TAG = "";
	public static String NOTICE = "NOTICE";
	public static String CARINFO = "CARINFO";

	private DbHelper db;
	private Cursor cursor;

	private BroadcastReceiver mReceiver;
	private BluetoothPrinterHelper bp;
	private ImageView notice_new_icon;
	private Button receipt_btn, search_btn, setting_btn, notice_btn;
	private Intent i;
	private String vl_no;
	int count = 0;
	// View popupView;
	int seqint;
	LinearLayout layout;
	// PopupWindow popupWindow;
	private PopupWindow mPopupWindow;

	ArrayList<AncmDto> ancmlist = new ArrayList<AncmDto>();

	private LoginDto login;
	private AuthDto authDto;

	boolean m_close_flag = false;
	private Prefs myprefs;
	
	ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TAG = this.getClass().getName();

		startMainService();
		
		this.myprefs = new Prefs(getApplicationContext());
		bp = new BluetoothPrinterHelper(this);

		Bundle bundle = getIntent().getExtras();

		if (bundle != null) {
			login = (LoginDto) bundle.getSerializable(CommonSet.LOGIN_DATA_NAME);

			myprefs.setMacAdress(login.getMAC_ADRES());
			myprefs.setUserID(login.getUSR_ID());
			myprefs.save();

			authDto = (AuthDto) bundle.getSerializable(CommonSet.AUTH_DATA_NAME);

		}

		db = new DbHelper(getApplicationContext());

		notice_btn = (Button) findViewById(R.id.notice_btn);
		receipt_btn = (Button) findViewById(R.id.main_receipt_btn);
		search_btn = (Button) findViewById(R.id.main_search_btn);
		setting_btn = (Button) findViewById(R.id.main_setting_btn);
		notice_new_icon = (ImageView) findViewById(R.id.notice_new_icon);
		layout = (LinearLayout) findViewById(R.id.layout);
		receipt_btn.setOnClickListener(this);
		search_btn.setOnClickListener(this);
		setting_btn.setOnClickListener(this);
		notice_btn.setOnClickListener(this);

	}

	private String makeValetNo() {

		String seq;
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
		String strCurrentDate = dateFormat.format(now);
		myprefs.setToday(strCurrentDate);
		String valetNo = authDto.getDEVC_NO() + strCurrentDate;

		db.open();
		Cursor cursor = db.selectColumn("SELECT MAX(VL_NO) as VL_NO FROM AM_RECT WHERE VL_NO LIKE '" + valetNo + "%'");

		Log.i(TAG, "vl number count >> " + cursor.getCount());

		cursor.moveToFirst();
		if (cursor.getString(cursor.getColumnIndex("VL_NO")) == null || cursor.getCount() == 0) {
			seq = "001";
		} else {
			seq = cursor.getString(cursor.getColumnIndex("VL_NO"));
			seq = seq.substring(3, 12);
			seqint = Integer.parseInt(seq) + 1;
			seq = String.format("%3s", String.valueOf(seqint)).replace(' ', '0');
			seq = seq.substring(6);

		}

		valetNo = valetNo + seq;
		Log.i(TAG, "vl_no is >>>> " + valetNo);

		db.close();

		return valetNo;
	}

	protected void onResume() {

		super.onResume();
		IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
		mReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String msg = intent.getStringExtra("bind");
				Log.i(TAG, "bind >> " + msg);
				noticeCall();
			}
		};
		this.registerReceiver(mReceiver, intentFilter);
		BusBuilder.getInstance().register(this);
		
		if (!BluetoothPort.getInstance().isConnected()) {
			if (myprefs.getBluetoothID() != null && myprefs.getBluetoothID() != "") {
				bp.bluetoothIsConnected();
			} else {
				Toast.makeText(this, "프린트가 설정되어 있지 않습니다. 프린트를 설정해 주세요.", Toast.LENGTH_LONG).show();

			}
		}
		
	};

	@Override
	protected void onPause() {
		this.unregisterReceiver(this.mReceiver);
		BusBuilder.getInstance().unregister(this);
		super.onPause();
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	public void dialogLoadingView() {
		dialog = new ProgressDialog(this);
		dialog.setTitle("로드중");
		dialog.setMessage("필요한 데이터를 불러오는 중입니다.");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
		dialog.show();
	}
	
	public void noticeCall() {
		dialogLoadingView();
		
		AncmDto ancm = new AncmDto();
		ancm.setMAC_ADRES(login.getMAC_ADRES());
		ancm.setUSR_ID(login.getUSR_ID());
		BusBuilder.getInstance().post(new AncmCommand(NOTICE, ancm));

	}

	public void carInfoCall() {
		CarInfoDto dto = new CarInfoDto();
		dto.setMAC_ADRES(login.getMAC_ADRES());
		BusBuilder.getInstance().post(new CarInfoCommand(CARINFO, dto));
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_receipt_btn:

			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
			SimpleDateFormat allFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

			String strCurrentTime = timeFormat.format(now);
			String strCurrentDate = dateFormat.format(now);
			String strCurrentAll = allFormat.format(now);

			i = new Intent(this, ParkInProcess_CameraLPR.class);

			RectDto rec = new RectDto();

			rec.setVL_NO(makeValetNo());
			rec.setMAC_ADRES(login.getMAC_ADRES());
			rec.setRECT_USR_NM(login.getUSR_NM());
			rec.setRECT_USR_ID(login.getUSR_ID());
			rec.setRECT_DE(strCurrentDate);
			rec.setRECT_HM(strCurrentTime);
			rec.setRECT_FULL_DT(strCurrentAll);

			Log.i(TAG, "receipt user Name >>> " + rec.getRECT_USR_NM());

			Bundle bundle = new Bundle();
			bundle.putSerializable(CommonSet.RECEIPT_DATA_NAME, rec);

			i.putExtras(bundle);

			startActivity(i);

			break;
		case R.id.main_search_btn:
			i = new Intent(this, Search.class);
			startActivity(i);
			break;
		case R.id.main_setting_btn:
			i = new Intent(this, Setting.class);
			startActivityForResult(i, CommonSet.SETTING_VIEW);
			break;
		case R.id.notice_btn:

			Button noti_back,
			noti_next,
			noti_close;
			final TextView notice;
			final TextView noti_date;

			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			int width = size.x;
			int height = size.y;

			LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
					.getSystemService(LAYOUT_INFLATER_SERVICE);
			View popupView = layoutInflater.inflate(R.layout.notice_popup,
					null, false);
			// popupView.measure(View.MeasureSpec.UNSPECIFIED,
			// View.MeasureSpec.UNSPECIFIED);

			// mPopupWindow = new
			// PopupWindow(layoutInflater.inflate(R.layout.notice_popup, null,
			// false), LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
			// true);
			mPopupWindow = new PopupWindow(popupView, (width - 60),
					(height - 60), true);

			mPopupWindow.setFocusable(true);
			mPopupWindow.setTouchable(true);
			mPopupWindow.setOutsideTouchable(true);
			mPopupWindow.getContentView().setFocusableInTouchMode(true);

			// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
			noti_back = (Button) popupView.findViewById(R.id.noti_back);
			noti_next = (Button) popupView.findViewById(R.id.noti_next);
			noti_close = (Button) popupView.findViewById(R.id.notice_close);
			noti_date = (TextView) popupView.findViewById(R.id.notice_date);
			notice = (TextView) popupView.findViewById(R.id.notice_data);
			// mPopupWindow.showAsDropDown(layout,100,290);
			mPopupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
			noti_close.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View popupView) {
					mPopupWindow.dismiss();
				}
			});
			db.open();

			cursor = db
					.selectColumn("SELECT ANCM_SN as _id,ANCM_NM,BEGIN_DE,END_DE,MAKE_USR_NM,ANCM_CONT FROM AM_ANCM");
			cursor.moveToNext();
			if (cursor.getCount() > 0) {
				if (cursor.moveToFirst()) {

					do {

						AncmDto ancmdto = new AncmDto();
						ancmdto.setBEGIN_DE(cursor.getString(cursor
								.getColumnIndex("BEGIN_DE")));
						ancmdto.setANCM_CONT(cursor.getString(cursor
								.getColumnIndex("ANCM_CONT")));

						ancmlist.add(ancmdto);

					} while (cursor.moveToNext());
				} else if (cursor.equals(null) || cursor.equals("")) {
					ancmlist.clear();

				}
				if (ancmlist != null || ancmlist.size() > 0) {
					final int maxCount = ancmlist.size() - 1;
					count = ancmlist.size() - 1;

					StringBuffer sb = new StringBuffer(ancmlist.get(count)
							.getBEGIN_DE());
					sb.insert(4, "/");
					sb.insert(7, "/");
					// noti_date.setText(ancmlist.get(count).getBEGIN_DE());
					noti_date.setText(sb.toString());
					notice.setText(ancmlist.get(count).getANCM_CONT());

					/*
					 * if (cursor.moveToFirst())
					 * 
					 * do {
					 * 
					 * AncmDto ancmdto = new AncmDto();
					 * ancmdto.setBEGIN_DE(cursor.getString
					 * (cursor.getColumnIndex("BEGIN_DE")));
					 * ancmdto.setANCM_CONT(cursor.getString(cursor
					 * .getColumnIndex("CAR_SERS_NM")));
					 * 
					 * r.add(ancmdto); } while (cursor.moveToNext());
					 */

					noti_back.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (count > 0) {
								count--;
								Log.i("count", count + "");
								StringBuffer sb = new StringBuffer(ancmlist
										.get(count).getBEGIN_DE());
								sb.insert(4, "/");
								sb.insert(7, "/");
								noti_date.setText(sb.toString());
								notice.setText(ancmlist.get(count)
										.getANCM_CONT());
							}
						}
					});

					noti_next.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							if (count < maxCount) {
								Log.i("count", count + "");
								count++;
								StringBuffer sb = new StringBuffer(ancmlist
										.get(count).getBEGIN_DE());
								sb.insert(4, "/");
								sb.insert(7, "/");
								noti_date.setText(sb.toString());
								notice.setText(ancmlist.get(count)
										.getANCM_CONT());
							}
						}
					});
				}
			}

			db.close();
			break;

		default:
			break;
		}

	}

	Handler m_close_handler = new Handler() {
		public void handleMessage(Message msg) {
			m_close_flag = false;
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == CommonSet.SETTING_VIEW) {
			if (resultCode == CommonSet.LOGOUT) {
				stopMainService();
				startActivity(new Intent(MainActivity.this, LoginActivity.class));

				finish();
			}
		}
	}

	@Override
	public void onBackPressed() {
		if (mPopupWindow != null) {
			if (mPopupWindow.isShowing()) {
				mPopupWindow.dismiss();
			}
		} else if (m_close_flag == false) {

			new AlertView().showAlert("Back 버튼을 한번 더 터치하시면 앱이 종료됩니다.", this);
			// 상태값 변경
			m_close_flag = true;

			m_close_handler.sendEmptyMessageDelayed(0, 3000);
			// stopMainService();

		} else {
			super.onBackPressed();
			stopMainService();
			startActivity(new Intent(MainActivity.this, LoginActivity.class));
			finish();
		}

	}

	protected void onStop() {
		super.onStop();

		// 핸드러에 등록된 0번 메세지를 모두 지운다.
		m_close_handler.removeMessages(0);
	}

	protected void startMainService() {
		Intent intent = new Intent(getApplicationContext(), MainService.class);
		startService(intent);
		// startService(new Intent("com.hsit.iqsc.IQSClientService"));
	}

	protected void stopMainService() {
		stopService(new Intent(getApplicationContext(), MainService.class));
		// stopService(new Intent("com.hsit.iqsc.IQSClientService"));
	}

	@Subscribe
	public void onCommandResult(CommandResult cr) {
		Log.i(TAG, cr.getRefId() + " : result json >>> " + cr.getJson());

		if (cr.getRefId().equals(NOTICE)) {
			if (cr.getResultCd() == 0) {

			} else {
				//Toast.makeText(this, "공지사항을 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
				notice_btn.setVisibility(View.GONE);
				notice_new_icon.setVisibility(View.GONE);
			}
			carInfoCall();
		} else if (cr.getRefId().equals(CARINFO)) {
			dialog.dismiss();
			
			if (cr.getResultCd() == 0) {

			} else {
				Toast.makeText(this, "차량정보을 불러오는데 실패하였습니다.", Toast.LENGTH_LONG).show();
			}
		}

	}
}
