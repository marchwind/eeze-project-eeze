package com.terascope.amano.incheon.setting;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.terascope.amano.R;
import com.terascope.amano.incheon.MainActivity;
import com.terascope.amano.incheon.PrintActivity;
import com.terascope.amano.incheon.Search;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dbadapter.SearchAdapter;
import com.terascope.amano.incheon.dbadapter.UserAdapter;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.dto.UseDto;

public class Setting_Use_Status extends Activity implements OnClickListener {
	private DbHelper db;
	Cursor cursor;
	private Intent i;
	private TextView use_home, use_back;
	private ListView use_list;

	private Context c;
	//ArrayList<UseDto> usedto = new ArrayList<UseDto>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.setting_use_status);
	    use_home=(TextView)findViewById(R.id.set_use_home);
	    use_home.setOnClickListener(this);
	    use_back = (TextView)findViewById(R.id.set_use_back);
	    use_back.setOnClickListener(this);
	    use_list = (ListView)findViewById(R.id.use_status_list);
	    //useadapter = new UseAdapter(this, usedto);
	    //use_list.setAdapter(useadapter);
	    db = new DbHelper(getApplicationContext());
	    selectDB();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.set_use_home:
			setResult(CommonSet.MAIN);
			finish();
			break;
		case R.id.set_use_back:
			onBackPressed();
			finish();
			break;
		default:
			break;
		}
		
		
		
	}
	
	@SuppressWarnings("deprecation")
	private void selectDB() {
		
		db.open();
		cursor = db.selectColumn("SELECT AM_USR.USR_ID as _id, AM_USR.USR_NM as USR_NM, AM_LOGIN.LOGIN_DT as LOGIN_DT FROM AM_USR INNER JOIN AM_LOGIN ON AM_USR.USR_ID= AM_LOGIN.USR_ID");
		cursor.moveToNext();
		if (cursor.getCount() > 0) {

			UserAdapter userAdapter = new UserAdapter(this, cursor);			
			use_list.setAdapter(userAdapter);
			
		}
		
		db.close();
		
	}
	
	@Override
	   public void onBackPressed() {
	      // TODO Auto-generated method stub
	      finish();
	      super.onBackPressed();
	   }

	
}
