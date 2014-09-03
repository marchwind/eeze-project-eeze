package com.terascope.amano.incheon;

import java.util.ArrayList;

import org.apache.commons.net.finger.FingerClient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.terascope.amano.R;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dbadapter.CarSearchAdapter;
import com.terascope.amano.incheon.dto.CarInfoDto;

public class Car_Search extends ListActivity implements OnClickListener {
	private DbHelper db;
	private EditText car_keyword;
	private ArrayList<String> searchList = new ArrayList<String>();
	private ArrayList<CarInfoDto> newSearchList = new ArrayList<CarInfoDto>();
	private Button carSearch_close;
	ArrayList<CarInfoDto> carInfoList;
	ListView lv;
	Cursor cursor;
	CarSearchAdapter dbAdapter ;
	private Context context;
	private MenuAdapter mAdapter;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_search);
		car_keyword = (EditText) findViewById(R.id.car_edit);
		carSearch_close = (Button) findViewById(R.id.carSearch_close);
		carSearch_close.setOnClickListener(this);
		carInfoList = new ArrayList<CarInfoDto>();
		
		car_keyword.addTextChangedListener(textWatcherInput);// 텍스트가 변할때 이벤트
		db = new DbHelper(getApplicationContext());
		
		lv = getListView();
		
		carInfoList = selectDB("");
		mAdapter = new MenuAdapter(Car_Search.this, R.layout.car_list_row, carInfoList);
		setListAdapter(mAdapter);
		
		lv.setOnItemClickListener(itemClickListener);
		
	}
	
	OnItemClickListener itemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			CarInfoDto data = (CarInfoDto) parent.getAdapter().getItem(position);
			
			Bundle bun = new Bundle();

			bun.putString("CAR_SERS_CD", data.getCAR_SERS_CD());
			bun.putString("CAR_SERS_NM", data.getCAR_SERS_NM());
			
			Intent i = new Intent();
			i.putExtras(bun);
			setResult(1, i);
			finish();
		}
		
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.carSearch_close:
			onBackPressed();
			break;
		}
	}
	
	private Cursor getAllCar(){
		return db.selectColumn("SELECT CAR_SERS_CD as _id, CAR_SERS_NM FROM AM_CAR_INFO ORDER BY CAR_SERS_NM ASC");
	}
	
	private Cursor getMatchCar(String contents) {
		return db.selectColumn("SELECT CAR_SERS_CD as _id, CAR_SERS_NM FROM AM_CAR_INFO WHERE " +
								"CAR_SERS_NM LIKE '" + contents + "%' OR " +
								"CAR_SERS_NM LIKE '%" + contents + "%' OR " +
								"CAR_SERS_NM LIKE '%" + contents + "' OR " +
								"CAR_SERS_NM LIKE '" + contents + "'" +
								" ORDER BY CAR_SERS_NM ASC");
	}
	
	private ArrayList<CarInfoDto> selectDB(String s) {
	
		Cursor mCursor = null;
		ArrayList<CarInfoDto> resultDto = new ArrayList<CarInfoDto>();
		
		try{
			db.open();
			if (s.length() == 0) {
				mCursor = getAllCar();
				
				while(mCursor.moveToNext()){
					CarInfoDto dataDto = new CarInfoDto();
					dataDto.setCAR_SERS_CD(mCursor.getString(mCursor.getColumnIndex("_id")));
					dataDto.setCAR_SERS_NM(mCursor.getString(mCursor.getColumnIndex("CAR_SERS_NM")));
					
					resultDto.add(dataDto);						
				}
				
			} else {
				if(HangulUtils.isKorean(s.charAt(0))){
					mCursor = getAllCar();
					
					while(mCursor.moveToNext()){
						CarInfoDto dataDto = new CarInfoDto();
						dataDto.setCAR_SERS_CD(mCursor.getString(mCursor.getColumnIndex("_id")));
						dataDto.setCAR_SERS_NM(mCursor.getString(mCursor.getColumnIndex("CAR_SERS_NM")));
						
						String contents = dataDto.getCAR_SERS_NM();
						String keyWord = s;
						boolean isData = HangulUtils.matchString(contents, keyWord);
						if (isData) {
							resultDto.add(dataDto);
						}
					}
				} else {
					mCursor = getMatchCar(s);
					
					while(mCursor.moveToNext()){
						CarInfoDto dataDto = new CarInfoDto();
						dataDto.setCAR_SERS_CD(mCursor.getString(mCursor.getColumnIndex("_id")));
						dataDto.setCAR_SERS_NM(mCursor.getString(mCursor.getColumnIndex("CAR_SERS_NM")));
						
						resultDto.add(dataDto);						
					}
				}
			}
			
			db.close();
			
		} catch (Exception e) {
			resultDto = null;
			e.getStackTrace();
		}
		
		return resultDto;
		
	}

	TextWatcher textWatcherInput = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			mAdapter.clear();
			mAdapter.addAll(selectDB(s.toString()));
			mAdapter.notifyDataSetChanged();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void afterTextChanged(Editable s) {}
	};
	
	public class MenuAdapter extends ArrayAdapter<CarInfoDto> {
		private ArrayList<CarInfoDto> items;
		CarInfoDto fInfo;
		int layoutResourceId;
		
		public MenuAdapter(Context c, int textViewResourseId, ArrayList<CarInfoDto> items) {
			super(c, textViewResourseId,items);
			context = c;
			this.items = items;

			this.layoutResourceId = textViewResourseId;
		}

		@SuppressLint("InflateParams")
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null) {
				LayoutInflater vi = ((Activity) parent.getContext()).getLayoutInflater();
				v = vi.inflate(this.layoutResourceId, parent, false);
			}
			
			fInfo = items.get(position);
			TextView s_carname = (TextView) v.findViewById(R.id.s_carname);
			s_carname.setText(fInfo.getCAR_SERS_NM());
		
			return v;
		}
	}
	

	@Override
	   public void onBackPressed() {
	      setResult(2);
	      finish();
	      super.onBackPressed();
	   }
	
}
