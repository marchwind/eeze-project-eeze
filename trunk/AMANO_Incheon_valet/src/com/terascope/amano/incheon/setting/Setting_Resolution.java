package com.terascope.amano.incheon.setting;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import com.terascope.amano.R;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;

public class Setting_Resolution extends Activity implements OnClickListener{

	public static String TAG = "";
	private Button setResolution_home, setResultion_back;
	private CheckBox fhd_btn, hd_btn, sd_btn, mms_btn;
	
	Prefs myprefs;
	ArrayAdapter<String> list;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_resolution);
	   
	    TAG = this.getClass().getName();
	    this.myprefs = new Prefs(getApplicationContext());
	    
	    setResolution_home = (Button) findViewById(R.id.resolution_home_btn);
	    setResultion_back = (Button) findViewById(R.id.resolution_back_btn);
	    setResolution_home.setOnClickListener(this);
	    setResultion_back.setOnClickListener(this);
	    
	    fhd_btn = (CheckBox)findViewById(R.id.fhd_check);
	    hd_btn = (CheckBox)findViewById(R.id.hd_check);
	    sd_btn = (CheckBox)findViewById(R.id.sd_check);
	    mms_btn = (CheckBox)findViewById(R.id.mms_check);
	    
	    fhd_btn.setOnClickListener(this);
	    hd_btn.setOnClickListener(this);
	    sd_btn.setOnClickListener(this);
	    mms_btn.setOnClickListener(this);
	    
	    fhd_btn.setChecked(false);
	    hd_btn.setChecked(false);
	    sd_btn.setChecked(false);
	    mms_btn.setChecked(false);
	    
	    Log.i(TAG, "resolution : " + this.myprefs.getResolution());
	    
	    if(this.myprefs.getResolution().equals(CommonSet.Resolution.FHD)){
	    	fhd_btn.setChecked(true);
	    } else if(this.myprefs.getResolution().equals(CommonSet.Resolution.HD)){
	    	hd_btn.setChecked(true);
	    } else if(this.myprefs.getResolution().equals(CommonSet.Resolution.MMS)){
	    	mms_btn.setChecked(true);
	    } else {
	    	sd_btn.setChecked(true);
	    }
	    
	    setResolution_home.setOnClickListener(new OnClickListener() {
			
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
		
		case R.id.resolution_back_btn:
			onBackPressed();
			finish();
			break;
		case R.id.fhd_check:
			
			fhd_btn.setChecked(true);
		    hd_btn.setChecked(false);
		    sd_btn.setChecked(false);
		    mms_btn.setChecked(false);
			
		    this.myprefs.setResolution(CommonSet.Resolution.FHD);
			break;
		case R.id.hd_check:
			
			fhd_btn.setChecked(false);
		    hd_btn.setChecked(true);
		    sd_btn.setChecked(false);
		    mms_btn.setChecked(false);
			
		    this.myprefs.setResolution(CommonSet.Resolution.HD);
			break;
			
		case R.id.sd_check:
			
			fhd_btn.setChecked(false);
		    hd_btn.setChecked(false);
		    sd_btn.setChecked(true);
		    mms_btn.setChecked(false);
			
		    this.myprefs.setResolution(CommonSet.Resolution.SD);
			
			break;
		case R.id.mms_check:
			fhd_btn.setChecked(false);
		    hd_btn.setChecked(false);
		    sd_btn.setChecked(false);
		    mms_btn.setChecked(true);
			
		    this.myprefs.setResolution(CommonSet.Resolution.MMS);
			
			break;
		default:
			break;
		}
		
		this.myprefs.save();
		
	}
	@Override
   public void onBackPressed() {
      finish();
      super.onBackPressed();
   }

}
