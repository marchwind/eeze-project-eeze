package com.terascope.amano.incheon.setting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.terascope.amano.R;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dbadapter.ReceiptDataAdapter;
import com.terascope.amano.incheon.service.BatchUploadCommand;
import com.terascope.amano.incheon.service.CommandResult;

public class Setting_Send extends Activity implements OnClickListener{
	
	public TextView receipt_data_btn,set_send_home,set_send_back;
	private Intent i;
	private RelativeLayout media_data_layout;
	private ListView media_data_list;
	private DbHelper db;
	private Button all_data_btn;
	private Context c;
	   
	public ProgressDialog dialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_data_send);
	
		startMainService();
		
		set_send_home = (TextView)findViewById(R.id.set_send_home);
		set_send_back = (TextView)findViewById(R.id.set_send_back);
		set_send_home.setOnClickListener(this);
		set_send_back.setOnClickListener(this);
		all_data_btn = (Button)findViewById(R.id.all_data_btn);
		all_data_btn.setOnClickListener(this);
		media_data_layout = (RelativeLayout)findViewById(R.id.media_data_layout);
	   
		media_data_list = (ListView)findViewById(R.id.media_data_list);
		db = new DbHelper(getApplicationContext());
		selectDB();
	      
	}
	
    @Override
    public void onClick(View v) {
    	if(v.getId()==R.id.set_send_home){
    		setResult(CommonSet.MAIN);
	        finish();
    	} else if(v.getId()==R.id.set_send_back){
	    	onBackPressed();
	    	finish();
	    	
	    } else if(v.getId() == R.id.all_data_btn){
	    	dialog = new ProgressDialog(this);
	    	dialog.setTitle("업로드중");
	    	dialog.setMessage("접수데이터를 업로드 중입니다.\n잠시만 기다려 주세요.");
	    	dialog.setIndeterminate(true);
	    	dialog.setCancelable(true);
	    	dialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
	    	dialog.show();
	 
	    	BusBuilder.getInstance().post(new BatchUploadCommand("BAthchUpload", null));
	     }
	    	
	 } 
	  
	@SuppressWarnings("deprecation")
	private void selectDB() {
		
		db.open();
		Cursor cursor = db.selectColumn("SELECT AM_RECT.VL_NO as _id, ifnull(AM_MM.REG_DT, date()) as REG_DT ,AM_RECT.CAR_NO as CAR_NO, AM_MM.FILE_NM as FILE_NM, AM_MM.MM_TYPE as MM_TYPE, AM_MM.UPD_DT as UPD_DT"
				+ " FROM AM_MM INNER JOIN AM_RECT ON AM_RECT.VL_NO= AM_MM.VL_NO AND AM_MM.MM_TYPE = 'V'");
	//	cursor.moveToNext();
		if (cursor.getCount() > 0) {
			Log.i("countddd", cursor.getCount()+"");
			ReceiptDataAdapter receiptdata = new ReceiptDataAdapter(this, cursor);			
			media_data_list.setAdapter(receiptdata);
			
		}else{
			
		}
		
		db.close();
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
	public void onBackPressed() {
		finish();
	    super.onBackPressed();
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
	
	@Subscribe
	public void onCommandResult(CommandResult cr) {
		dialog.dismiss();
		if (cr.getResultCd() == CommandResult.CD_SUCCESS) {
			Log.i(this.getClass().getName(), cr.getResultCd()+"/"+cr.getResultMsg());
			selectDB();
		}else{
			Log.i("통신거절","거절");
		}
	}
		
}
