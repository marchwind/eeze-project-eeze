package com.terascope.amano.incheon.dbadapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.terascope.amano.R;

public class CarSearchAdapter extends CursorAdapter{

	@SuppressWarnings("deprecation")
	public CarSearchAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		  View v = inflater.inflate(R.layout.car_list_row, parent, false);
		  return v;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		//final TextView s_carcode = (TextView)view.findViewById(R.id.s_carcode);
		final TextView s_carname = (TextView)view.findViewById(R.id.s_carname);
		s_carname.setText(cursor.getString(cursor.getColumnIndex("CAR_SERS_NM")));
		Log.d("pk", cursor.getCount()+"");
	}

}
