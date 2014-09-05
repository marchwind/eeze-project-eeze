package com.terascope.amano.incheon;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.terascope.amano.R;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dbadapter.SearchAdapter;
import com.terascope.amano.incheon.dto.RectDto;

public class Search extends Activity implements OnClickListener{

	public static String TAG = "";
	Cursor cursor;

	private DbHelper db;
	String sql;
	ListView mylist;
	EditText search_edit;
	SearchAdapter dbAdapter;
	
	private Button home_btn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		TAG = this.getClass().getName();
		
		home_btn =(Button) findViewById(R.id.search_home);
		home_btn.setOnClickListener(this);
		
		mylist = (ListView) findViewById(R.id.search_list);
		search_edit = (EditText)findViewById(R.id.search_edit);
		
		search_edit.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				selectDBCar(search_edit.getText().toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		db = new DbHelper(getApplicationContext());

		selectDB();

		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.search_home :
			/*Intent i = new Intent(Search.this, MainActivity.class);
			startActivity(i);*/
			finish();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void selectDB() {
		
		db.open();
		cursor = db.selectColumn("SELECT VL_NO as _id, * FROM AM_RECT ORDER BY RECT_DT DESC");
		cursor.moveToNext();
		if (cursor.getCount() > 0) {

			dbAdapter = new SearchAdapter(this, cursor);			
			mylist.setAdapter(dbAdapter);
			mylist.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					cursor.moveToPosition(position);
					String vl_no = cursor.getString(cursor.getColumnIndex("_id"));
					
					Log.i(TAG,"VL_NO >>> " + vl_no);
					
					Intent i = new Intent(Search.this, PrintActivity.class);
					i.putExtra("VL_NO", vl_no);
					startActivityForResult(i, 0);
					
				}
				
			});
		}
		
		db.close();
		
	}
	
	@SuppressWarnings("deprecation")
	private void selectDBCar(String param) {
		db.open();
		cursor = db.selectColumn("SELECT VL_NO as _id, * FROM AM_RECT WHERE CAR_NO LIKE '%"+param+"%' ORDER BY RECT_DT DESC");
		cursor.moveToNext();
		if (cursor.getCount() > 0) {

			dbAdapter = new SearchAdapter(this, cursor);			
			mylist.setAdapter(dbAdapter);
			mylist.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					cursor.moveToPosition(position);
					String vl_no = cursor.getString(cursor.getColumnIndex("_id"));
					
					Log.i(TAG,"VL_NO >>> " + vl_no);
					
					Intent i = new Intent(Search.this, PrintActivity.class);
					i.putExtra("VL_NO", vl_no);
					startActivityForResult(i, 0);
					
				}
				
			});
		}
		db.close();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == CommonSet.MAIN) {
			//setResult(0);
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
