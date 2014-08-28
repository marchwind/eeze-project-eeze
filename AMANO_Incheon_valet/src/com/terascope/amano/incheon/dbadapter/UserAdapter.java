package com.terascope.amano.incheon.dbadapter;

import com.terascope.amano.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class UserAdapter extends CursorAdapter {

	public UserAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		  View v = inflater.inflate(R.layout.use_list_row, parent, false);
		return v;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final TextView login_date = (TextView)view.findViewById(R.id.login_date);
		  final TextView login_name = (TextView)view.findViewById(R.id.login_name);
		  
		  login_date.setText(cursor.getString(cursor.getColumnIndex("LOGIN_DT")));
		  login_name.setText(cursor.getString(cursor.getColumnIndex("USR_NM")));
		  
	}

}
