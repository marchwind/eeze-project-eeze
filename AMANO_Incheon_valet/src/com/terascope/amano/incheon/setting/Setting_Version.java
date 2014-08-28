package com.terascope.amano.incheon.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.terascope.amano.R;
import com.terascope.amano.incheon.MainActivity;
import com.terascope.amano.incheon.common.CommonSet;

public class Setting_Version extends Activity implements OnClickListener {

	private Intent i;
	private TextView version_home, version_back;//,version_update_btn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_version);
	    
	    version_home=(TextView)findViewById(R.id.set_version_home);
	    version_home.setOnClickListener(this);
	    version_back = (TextView)findViewById(R.id.set_version_back);
	    version_back.setOnClickListener(this);
	    /*version_update_btn = (LinearLayout)findViewById(R.id.version_update_btn);
	    version_update_btn.setOnClickListener(this);*/
	    
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_version_home:
			setResult(CommonSet.MAIN);
			finish();
			break;
		case R.id.set_version_back:
			onBackPressed();
			finish();
			break;
		case R.id.version_update_btn:
			
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
