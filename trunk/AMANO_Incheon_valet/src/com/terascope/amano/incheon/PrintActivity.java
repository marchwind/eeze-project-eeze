package com.terascope.amano.incheon;

import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sewoo.port.android.BluetoothPort;
import com.squareup.otto.Subscribe;
import com.terascope.amano.R;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.common.AlertView;
import com.terascope.amano.incheon.common.BluetoothPrinterHelper;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.dto.ResultDto;
import com.terascope.amano.incheon.dto.UpdDto;
import com.terascope.amano.incheon.service.CommandResult;
import com.terascope.amano.incheon.service.ResvCommand;
import com.terascope.amano.incheon.service.UploadCommand;

public class PrintActivity extends Activity implements OnClickListener {

	public static String TAG = "";
	
	private DbHelper db;
	private BluetoothPrinterHelper bp;
	public boolean isPrint = true;
	
	Button printhome, printback, printCustomer, printKepp, viewReview,
			sendData;
	private TextView print_headline_text, print_receipt_num,
			print_receipt_name, print_receipt_date, print_car_num,
			print_car_name, print_return_date, print_car_search;

	private ImageView print_cat_img;
	private Intent i;

	private RectDto dto = new RectDto();
	private UpdDto upd = new UpdDto();
	
	public Prefs myprefs;
	
	public ProgressDialog dialog;
	public boolean isUpload = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.print);

		TAG = this.getClass().getName();
		
		bp = new BluetoothPrinterHelper(this);
		
		startMainService();

		Intent intent = getIntent();
		String vl_no = intent.getStringExtra("VL_NO");
		
		if(vl_no != null) {
			dto.setVL_NO(vl_no);	
		} else {
			new AlertDialog.Builder(this)
			.setTitle("경고")
			.setMessage("해당하는 접수 데이터가 없습니다.\n관리자에게 문의하세요.")
			.setPositiveButton(getString(R.string.alert_Ok_text),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							finish();
						}
					}).show();
			
		}
		
		myprefs = new Prefs(getApplicationContext());
		
		if(!BluetoothPort.getInstance().isConnected()){
			isPrint = false;
			if(myprefs.getBluetoothID() != null && myprefs.getBluetoothID() != ""){
				bp.bluetoothIsConnected();
				if(bp.bluetoothConnected){
					isPrint = true;
				}
			} else {
				Toast.makeText(this, "프린트가 설정되어 있지 않습니다. 프린트를 설정해 주세요.", Toast.LENGTH_LONG).show();
			}
		} 
		
		db = new DbHelper(getApplicationContext());

		upd.setVL_NO(dto.getVL_NO());
		
		dto = selectDB(dto);
		upd = movieSelectDb(upd);
		
		print_headline_text = (TextView) findViewById(R.id.print_headline_text);
		print_receipt_num = (TextView) findViewById(R.id.print_receipt_num);
		print_receipt_name = (TextView) findViewById(R.id.print_receipt_name);
		print_receipt_date = (TextView) findViewById(R.id.print_receipt_date);
		print_car_num = (TextView) findViewById(R.id.print_car_num);
		print_car_name = (TextView) findViewById(R.id.print_car_name);
		print_return_date = (TextView) findViewById(R.id.print_return_date);
		print_car_search = (TextView) findViewById(R.id.print_car_search);
		printhome = (Button) findViewById(R.id.print_home_btn);
		printback = (Button) findViewById(R.id.print_back_btn);
		print_cat_img = (ImageView) findViewById(R.id.print_car_img);
				
		StringBuffer sb = new StringBuffer(dto.getENTRY_DE());
		sb.insert(4, "/");
		sb.insert(7, "/");
		sb.insert(10, " ");
		sb.insert(14, ":");
		sb.insert(17, ":");
		
		print_receipt_name.setText(dto.getRECT_USR_NM());
		print_headline_text.setText(dto.getCAR_NO());
		print_car_num.setText(dto.getCAR_NO());
		print_car_name.setText(dto.getCAR_SERS_NM());
		print_receipt_num.setText(dto.getVL_NO());
		print_receipt_date.setText(dto.getRECT_DE());
		print_return_date.setText(sb.toString());
		print_car_search.setText(dto.getCAR_TRANS_NM());
		
		Bitmap bmImg = BitmapFactory.decodeFile(CommonSet.SAVE_PATH + dto.getCAR_DAMG_FILE_NM());
		print_cat_img.setImageBitmap(bmImg);
		
		printhome.setOnClickListener(this);
		printback.setOnClickListener(this);

		printCustomer = (Button) findViewById(R.id.print_customer_btn);
		printKepp = (Button) findViewById(R.id.print_keep_btn);
		printCustomer.setOnClickListener(this);
		printKepp.setOnClickListener(this);

		viewReview = (Button) findViewById(R.id.video_view_btn);
		sendData = (Button) findViewById(R.id.video_resend_btn);
		viewReview.setOnClickListener(this);
		sendData.setOnClickListener(this);
		if(isUpload){
			sendData.setVisibility(View.GONE);
		}
		
	}

	public RectDto selectDB(RectDto rec) {
		db.open();
		
		Cursor cursor = db.selectColumn("SELECT * FROM AM_RECT WHERE VL_NO = '" + rec.getVL_NO() + "'");
		cursor.moveToFirst();
		
		if (cursor.getCount() > 0) {
			Log.d("값", cursor.getString(cursor.getColumnIndex("CAR_NO")));
		
			StringBuffer sb = new StringBuffer(cursor.getString(cursor.getColumnIndex("RECT_DT")));
			sb.insert(4, "/");
			sb.insert(7, "/");
			sb.insert(10, " ");
			sb.insert(13, ":");
			sb.insert(16, ":");
			
			rec.setRECT_USR_ID(cursor.getString(cursor.getColumnIndex("RECT_USR_ID")));
			rec.setRECT_USR_NM(cursor.getString(cursor.getColumnIndex("RECT_USR_NM")));
			rec.setCAR_NO(cursor.getString(cursor.getColumnIndex("CAR_NO")));
			rec.setCAR_SERS_NM(cursor.getString(cursor.getColumnIndex("CAR_SERS_NM")));
			rec.setTRVL_TERM(cursor.getString(cursor.getColumnIndex("TRVL_TERM")));
			rec.setRECT_DE(sb.toString());
			rec.setRECT_FULL_DT(sb.toString());
			rec.setENTRY_DE(cursor.getString(cursor.getColumnIndex("ENTRY_DT")));
			
			StringBuffer sb2 = new StringBuffer(cursor.getString(cursor.getColumnIndex("ENTRY_DT")));
			sb2.insert(4, "/");
			sb2.insert(7, "/");
			sb2.insert(10, " ");
			sb2.insert(13, ":");
			sb2.insert(16, ":");
			
			rec.setENTRY_FULL_DT(sb2.toString());
			Log.e("entry",rec.getENTRY_DE());
			rec.setCAR_DAMG_FILE_NM(cursor.getString(cursor.getColumnIndex("CAR_DAMG_FILE_NM")));
			rec.setCSTMR_SIGN_FILE_NM(cursor.getString(cursor.getColumnIndex("CSTMR_SIGN_FILE_NM")));
			
			String transNM = "";
			if (cursor.getString(cursor.getColumnIndex("CAR_TRANS_CD")) == "01") {
				transNM = "지하 3층 A32(동측)";
			} else {
				transNM = "지하 3층 H38(서측)";
			}
			
			rec.setCAR_TRANS_NM(transNM);
			
		}
		cursor.close();
		db.close();
		
		return rec;
	}
	
	public UpdDto movieSelectDb (UpdDto upd) {
		db.open();
		Cursor cursor = db.selectColumn("SELECT * FROM AM_MM WHERE VL_NO = '" + upd.getVL_NO() + "' AND MM_TYPE = 'V'");
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
			Log.i(TAG,"Movie File Nm >> " + cursor.getString(cursor.getColumnIndex("FILE_NM")));

			upd.setFLAG("V");
			upd.setFILE_NM(cursor.getString(cursor.getColumnIndex("FILE_NM")));

			if(cursor.getString(cursor.getColumnIndex("UPD_DT")) == null || cursor.getString(cursor.getColumnIndex("UPD_DT")).equals("")){
				isUpload = false;
			} else {
				isUpload = true;
			}
			
		}
		cursor.close();
		db.close();
		
		return upd;
	}

	@Override
	public void onClick(View v) {
		ResultDto res = null;
		switch (v.getId()) {
		case R.id.print_home_btn:
			setResult(CommonSet.MAIN);
			finish();
			break;
		case R.id.print_back_btn:
			finish();
			break;
		case R.id.print_customer_btn:

			res = bp.valetParkPrint(dto, CommonSet.PRINT_CUSTOMER);
			
			if(res.getRTN_CD().equals(CommonSet.BLUETHOOTH_ERROR_CODE)){
				Toast.makeText(this, res.getRTN_MSG(), Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.print_keep_btn:
			res = bp.valetParkPrint(dto, CommonSet.PRINT_OFFER);
			
			if(res.getRTN_CD().equals(CommonSet.BLUETHOOTH_ERROR_CODE)){
				Toast.makeText(this, res.getRTN_MSG(), Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.video_view_btn:
			Intent i = new Intent(this, MediaView.class);
			i.putExtra(CommonSet.MEDIA_PATH_NAME, CommonSet.SAVE_PATH + upd.getFILE_NM());
			startActivity(i);
			
			break;
		case R.id.video_resend_btn :
			sendData();
			break;
		default:
			break;
		}

	}
	
	private void sendData(){
		dialog = new ProgressDialog(this);
		dialog.setTitle("업로드중");
		dialog.setMessage("접수데이터를 업로드 중입니다.\n잠시만 기다려 주세요.");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
		dialog.show();
		
		upd.setMAC_ADRES(this.myprefs.getMacAdress());
		upd.setREG_DT(dto.getRECT_DE());
		
		Log.i(TAG, "updto : " + upd.getVL_NO() + ", flag : " + upd.getFLAG() + ", mac : " + upd.getMAC_ADRES());
		
		BusBuilder.getInstance().post(new UploadCommand("SENDDATA", upd));
	}
	
	@Override
	protected void onResume() {
		BusBuilder.getInstance().register(this);
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		BusBuilder.getInstance().unregister(this);
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		stopMainService();
		bp.btDisconn();
		super.onDestroy();
		
	}
	
	@Subscribe
	public void onCommandResult(CommandResult cr) {
		dialog.dismiss();
		if (cr.getResultCd() == CommandResult.CD_SUCCESS){
			Toast.makeText(this, "접수 데이터를 업로드 하였습니다.", Toast.LENGTH_SHORT).show();
			sendData.setVisibility(View.GONE);
		} else {
			new AlertDialog.Builder(this)
			.setTitle("경고")
			.setMessage("업로드에 실패하였습니다.\n다시 시도하시겠습니까?")
			.setPositiveButton(getString(R.string.alert_Ok_text),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sendData();
						}
					})
			.setNegativeButton(getString(R.string.alert_Cancel_text),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog,int which) {}
					}).show();
		}
	}
	
	protected void startMainService() {
		Intent intent = new Intent(getApplicationContext(), com.terascope.amano.incheon.batch.MainService.class);
		startService(intent);
		// startService(new Intent("com.hsit.iqsc.IQSClientService"));
	}

	protected void stopMainService() {
		stopService(new Intent(getApplicationContext(), com.terascope.amano.incheon.batch.MainService.class));
		// stopService(new Intent("com.hsit.iqsc.IQSClientService"));
	}
	
	@Override
	   public void onBackPressed() {
	      finish();
	      super.onBackPressed();
	   }

}
