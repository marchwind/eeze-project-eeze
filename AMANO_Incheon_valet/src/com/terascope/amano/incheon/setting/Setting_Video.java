package com.terascope.amano.incheon.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.terascope.amano.R;
import com.terascope.amano.incheon.MainActivity;
import com.terascope.amano.incheon.Receipt;
import com.terascope.amano.incheon.common.CommonSet;

public class Setting_Video extends Activity implements OnClickListener{


	private TextView v_home, v_back;
	private CheckBox check_video, check_camera;
	private SharedPreferences pref = null;
	private SharedPreferences.Editor ed;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_video);
	    
	    pref = getSharedPreferences("pref", MODE_PRIVATE);
	    ed = pref.edit();
	    check_video = (CheckBox)findViewById(R.id.ch_video);
	    check_camera = (CheckBox)findViewById(R.id.ch_camera);
	    
	    check_video.setOnClickListener(this);
	    check_camera.setOnClickListener(this);
	    
	   v_home = (TextView)findViewById(R.id.set_video_home);
	   v_back = (TextView)findViewById(R.id.set_video_back);
	   v_home.setOnClickListener(this);
	   v_back.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_video_home:
			setResult(CommonSet.MAIN);
			finish();
			break;
			
		case R.id.set_video_back:
			onBackPressed();
			finish();
			break;
		case R.id.ch_video:
			check_video.setChecked(true);
			check_camera.setChecked(false);
			
			break;
		case R.id.ch_camera:
			
				new AlertDialog.Builder(Setting_Video.this)
				.setTitle(getString(R.string.alert_title_text))
				.setMessage(getResources().getString(R.string.setCamera_need_alert))
				.setPositiveButton(getString(R.string.alert_Ok_text), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {}
				})
				.show();	
			
			check_video.setChecked(true);
			check_camera.setChecked(false);
		
			break;

		default:
			break;
		}
		
	}
	@Override
	   public void onBackPressed() {
	      // TODO Auto-generated method stub
	      finish();
	      super.onBackPressed();
	   }


}
