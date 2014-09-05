package com.terascope.amano.incheon.dbadapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.terascope.amano.R;

public class SearchAdapter extends CursorAdapter {

	private Cursor cu;
	@SuppressWarnings("deprecation")
	public SearchAdapter(Context context, Cursor c) {
		super(context, c);
		//this.cu =c;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cur) {
		final TextView search_date = (TextView)view.findViewById(R.id.search_date);
		  final TextView car_number = (TextView)view.findViewById(R.id.car_number);
		  
		  StringBuffer sb = new StringBuffer(cur.getString(cur.getColumnIndex("RECT_DT")));
		    sb.insert(4, "/");
		    sb.insert(7, "/");
		    sb.insert(10, " ");
		    sb.insert(13, ":");
		    sb.insert(16, ":");
		    
		  search_date.setText("접수 일시 : "+sb.toString());
		  car_number.setText(cur.getString(cur.getColumnIndex("CAR_NO")));  
		Log.d("pk", cur.getCount()+"");
		
	}

	@Override
	public View newView(Context context, Cursor cur, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		  View v = inflater.inflate(R.layout.search_list_row, parent, false);
		  return v;


	}

}
