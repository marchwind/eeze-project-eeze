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
		
		lv = getListView();
		
		car_keyword.addTextChangedListener(textWatcherInput);// 텍스트가 변할때 이벤트
		db = new DbHelper(getApplicationContext());
		selectDB();
		
		

		
	}

	@Override
	   public void onClick(View v) {
	      // TODO Auto-generated method stub
	      switch (v.getId()) {
	      case R.id.carSearch_close:
	         onBackPressed();
	         break;
	      }
	   }
	@SuppressWarnings("deprecation")
	private void selectDB() {
	
		db.open();	
		cursor = db
				.selectColumn("SELECT CAR_SERS_CD as _id,CAR_SERS_NM FROM AM_CAR_INFO ORDER BY CAR_SERS_NM ASC");
		cursor.moveToNext();
		if (cursor.getCount() > 0) {
			
			dbAdapter = new CarSearchAdapter(this, cursor);
			lv.setAdapter(dbAdapter);
			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Bundle bun = new Bundle();

					Log.e("Position == ", carInfoList.get(position)
							.getCAR_SERS_NM());
					bun.putString("CAR_SERS_CD", carInfoList.get(position)
							.getCAR_SERS_CD());
					bun.putString("CAR_SERS_NM", carInfoList.get(position)
							.getCAR_SERS_NM());
					
					Intent i = new Intent();
					i.putExtras(bun);
					setResult(1, i);
					finish();
				}

			});
		}

		if (cursor.moveToFirst())
			
		do {
			
			CarInfoDto dto = new CarInfoDto();
			dto.setCAR_SERS_CD(cursor.getString(cursor.getColumnIndex("_id")));
			dto.setCAR_SERS_NM(cursor.getString(cursor
					.getColumnIndex("CAR_SERS_NM")));
			
			carInfoList.add(dto);
		} while (cursor.moveToNext());
		db.close();
	}

	TextWatcher textWatcherInput = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			newSearchList.clear();
			for (int i = 0; i < carInfoList.size(); i++) {
				CarInfoDto newcar = new CarInfoDto();
				newcar.setCAR_SERS_CD(carInfoList.get(i).getCAR_SERS_CD());
				newcar.setCAR_SERS_NM(carInfoList.get(i).getCAR_SERS_NM());
				
				String car_name = newcar.getCAR_SERS_NM();
				String keyWord = s.toString();
				boolean isData = HangulUtils.matchString(car_name, keyWord); 
				Log.i("key",keyWord.toString());
				if (isData) {
					newSearchList.add(newcar);// 검색대상에 있으면 새로운 리스트를 만들어서
					Log.e("dsdsd","sdsd");								// 이름을 애드해준다
				}
			
			}
			Log.i("newSearchSize", newSearchList.size()+"");
			mAdapter = new MenuAdapter(Car_Search.this, R.layout.car_list_row, newSearchList);
			setListAdapter(mAdapter);
			dbAdapter.notifyDataSetChanged();
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO Auto-generated method stub
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub

		}
	};
	
	public class MenuAdapter extends ArrayAdapter<CarInfoDto> {
		private ArrayList<CarInfoDto> items;
		CarInfoDto fInfo;

		public MenuAdapter(Context c, int textViewResourseId, ArrayList<CarInfoDto> items) {
			super(c, textViewResourseId,items);
			context = c;
			this.items = items;
			Log.d("arraysize", this.items.size()+"");

		}

		@SuppressLint("InflateParams")
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null) {
			    //LayoutInflater inflater = LayoutInflater.from(context);
				LayoutInflater vi = (LayoutInflater) getSystemService(context.LAYOUT_INFLATER_SERVICE);

				v = vi.inflate(R.layout.car_list_row, null);
				Log.d("v", v.toString());
			}
			fInfo = items.get(position);
			Log.d("nm", fInfo.getCAR_SERS_NM());
			
			TextView s_carname = (TextView) v.findViewById(R.id.s_carname);
			
				s_carname.setText(fInfo.getCAR_SERS_NM());
			
				lv.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Bundle bun = new Bundle();

						Log.e("Position == ", newSearchList.get(position).getCAR_SERS_NM());
						bun.putString("CAR_SERS_CD", fInfo.getCAR_SERS_CD());
						bun.putString("CAR_SERS_NM", fInfo.getCAR_SERS_NM());
						
						Intent i = new Intent();
						i.putExtras(bun);
						setResult(1, i);
						finish();
					}

				});
			
			

			return v;
		}
	}
	

	@Override
	   public void onBackPressed() {
	      // TODO Auto-generated method stub
	      setResult(2);
	      finish();
	      super.onBackPressed();
	   }
	
}
