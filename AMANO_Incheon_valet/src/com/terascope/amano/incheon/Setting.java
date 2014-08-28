package com.terascope.amano.incheon;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.FrameLayout;

import com.terascope.amano.R;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.setting.Setting_Bluetooth_Print;
import com.terascope.amano.incheon.setting.Setting_DeviceIp;
import com.terascope.amano.incheon.setting.Setting_Flash;
import com.terascope.amano.incheon.setting.Setting_Resolution;
import com.terascope.amano.incheon.setting.Setting_Send;
import com.terascope.amano.incheon.setting.Setting_Use_Status;
import com.terascope.amano.incheon.setting.Setting_Version;
import com.terascope.amano.incheon.setting.Setting_Video;

public class Setting extends Activity implements OnClickListener, OnTouchListener{

	private FrameLayout set_btooth_btn, set_flash_btn, set_video_btn, set_send_btn, 
						set_status_btn, set_version_btn,set_deviceip_btn, set_logout_btn, set_resolution_btn;
	private Intent i;
	private Button home;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting);
	    
	    home = (Button)findViewById(R.id.set_bhome_btn);
	    set_btooth_btn = (FrameLayout)findViewById(R.id.set_btoothbtn);
	    set_flash_btn = (FrameLayout)findViewById(R.id.set_flashbtn);
	    set_video_btn = (FrameLayout)findViewById(R.id.set_videobtn);
	    set_send_btn = (FrameLayout)findViewById(R.id.set_sendbtn);
	    set_status_btn = (FrameLayout)findViewById(R.id.set_statusbtn);
	    set_version_btn = (FrameLayout)findViewById(R.id.set_versionbtn);
	    set_deviceip_btn = (FrameLayout)findViewById(R.id.set_deviceip_btn);
	    set_logout_btn = (FrameLayout)findViewById(R.id.set_logout_btn);
	    set_resolution_btn = (FrameLayout)findViewById(R.id.set_resolutionbtn);
	    
	    home.setOnClickListener(this);
	    set_btooth_btn.setOnClickListener(this);
	    set_flash_btn.setOnClickListener(this);
	    set_video_btn.setOnClickListener(this);
	    set_send_btn.setOnClickListener(this);
	    set_status_btn.setOnClickListener(this);
	    set_version_btn.setOnClickListener(this);
	    set_deviceip_btn.setOnClickListener(this);
	    set_logout_btn.setOnClickListener(this);
	    set_resolution_btn.setOnClickListener(this);
	    
	    set_btooth_btn.setOnTouchListener(this);
	    set_flash_btn.setOnTouchListener(this);
	    set_video_btn.setOnTouchListener(this);
	    set_send_btn.setOnTouchListener(this);
	    set_status_btn.setOnTouchListener(this);
	    set_version_btn.setOnTouchListener(this);
	    set_deviceip_btn.setOnTouchListener(this);
	    set_resolution_btn.setOnTouchListener(this);
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN :
			v.setBackgroundColor(getResources().getColor(R.color.set_list_touch_down_color));
			break;
		case MotionEvent.ACTION_MOVE :
		case MotionEvent.ACTION_CANCEL :
		case MotionEvent.ACTION_UP :
			v.setBackgroundColor(getResources().getColor(R.color.set_list_touch_up_color));
			break;
		}
		
		return false;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_bhome_btn:			
			setResult(0, null);
			finish();
			break;
		case R.id.set_btoothbtn:
			i = new Intent(this, Setting_Bluetooth_Print.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_flashbtn:
			i = new Intent(this, Setting_Flash.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_resolutionbtn:
			i = new Intent(this, Setting_Resolution.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_videobtn:
			i = new Intent(this, Setting_Video.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_sendbtn:
			i = new Intent(this, Setting_Send.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_statusbtn:
			i = new Intent(this, Setting_Use_Status.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_versionbtn:
			i = new Intent(this, Setting_Version.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_deviceip_btn:
			i = new Intent(this, Setting_DeviceIp.class);
			startActivityForResult(i, 0);
			break;
		case R.id.set_logout_btn :
			setResult(CommonSet.LOGOUT);
			finish();
		default:
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == CommonSet.MAIN) {
			setResult(0);
			finish();
		}
	}

	@Override
	   public void onBackPressed() {
	      // TODO Auto-generated method stub
	      finish();
	      super.onBackPressed();
	   }

	
}
