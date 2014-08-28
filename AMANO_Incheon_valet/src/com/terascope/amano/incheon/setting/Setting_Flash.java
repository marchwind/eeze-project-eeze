package com.terascope.amano.incheon.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.terascope.amano.R;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;

public class Setting_Flash extends Activity implements OnClickListener{

	private Button setflash_home, setflash_back;
	private CheckBox flash_always_btn,flash_custom_btn,flash_time_btn;
	private Spinner time1,time2;
	private String t1, t2;
	//private Boolean alway_index = true,custom_index = false,time_index = false;
	private Boolean index = true;
	
	Prefs myprefs;
	ArrayAdapter<String> list;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_flash);
	   
	    this.myprefs = new Prefs(getApplicationContext());
	    
	    setflash_home = (Button) findViewById(R.id.flash_home_btn);
	    setflash_back = (Button) findViewById(R.id.flash_back_btn);
	    setflash_home.setOnClickListener(this);
	    setflash_back.setOnClickListener(this);
	    time1 = (Spinner)findViewById(R.id.time1);
	    time2 = (Spinner)findViewById(R.id.time2);
	    flash_always_btn = (CheckBox)findViewById(R.id.always_check);
	    flash_custom_btn = (CheckBox)findViewById(R.id.customer_check);
	    flash_time_btn = (CheckBox)findViewById(R.id.time_check);
	    flash_always_btn.setOnClickListener(this);
	    flash_custom_btn.setOnClickListener(this);
	    flash_time_btn.setOnClickListener(this);
	    time1.setPrompt(myprefs.getFlashFromTime());
	    time2.setPrompt(myprefs.getFlashToTime());

	    time1.setOnItemSelectedListener(new OnItemSelectedListener() {
	    	@Override
	    	public void onItemSelected(AdapterView<?> parent, View view,
	    			int position, long id) {
	    		t1 = (String) parent.getItemAtPosition(position);
	    		myprefs.setFlashFromTime(t1);
				myprefs.save();
	    		Log.d("time1",t1);
	    	}
	    	@Override
	    	public void onNothingSelected(AdapterView<?> parent) {
	    		// TODO Auto-generated method stub
	    	}
		});
	    
	    time2.setOnItemSelectedListener(new OnItemSelectedListener() {
	    	@Override
	    	public void onItemSelected(AdapterView<?> parent, View view,
	    			int position, long id) {
	    		t2 = (String) parent.getItemAtPosition(position);
	    		myprefs.setFlashToTime(t2);
				myprefs.save();
	    		Log.d("time2",t2);
	    	}
	    	@Override
	    	public void onNothingSelected(AdapterView<?> parent) {
	    		// TODO Auto-generated method stub
	    	}
		});

	    
	    if(myprefs.getFlashStatue()==CommonSet.SettingType.FLASH_ALL){
	    	time1.setEnabled(false);
			time2.setEnabled(false);
		

	    } else if(myprefs.getFlashStatue()==CommonSet.SettingType.FLASH_TIME){
	    	time1.setEnabled(true);
			time2.setEnabled(true);
		
	    } else {
	    	time1.setEnabled(false);
			time2.setEnabled(false);
			
	    }
	    
	    setflash_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(CommonSet.MAIN);
				finish();
			}
		});
	 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.flash_back_btn:
			onBackPressed();
			finish();
			break;
		case R.id.always_check:
			
			flash_always_btn.setChecked(true);
			flash_custom_btn.setChecked(false);
			flash_time_btn.setChecked(false);
			time1.setEnabled(false);
			time2.setEnabled(false);
			myprefs.setFlashStatue(CommonSet.SettingType.FLASH_ALL);
			myprefs.save();
			
			
		case R.id.customer_check:
			
			flash_always_btn.setChecked(false);
			flash_custom_btn.setChecked(true);
			flash_time_btn.setChecked(false);
			time1.setEnabled(false);
			time2.setEnabled(false);
			myprefs.setFlashStatue(CommonSet.SettingType.FLASH_CUSTOM);
			myprefs.save();
			break;
			
		case R.id.time_check:
			
			flash_always_btn.setChecked(false);
			flash_custom_btn.setChecked(false);
			flash_time_btn.setChecked(true);
			time1.setEnabled(true);
			time2.setEnabled(true);
			myprefs.setFlashStatue(CommonSet.SettingType.FLASH_TIME);
			myprefs.save();
			
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
