package com.terascope.amano.incheon.dbadapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.terascope.amano.R;

public class MediaDataAdapter extends CursorAdapter{

	public MediaDataAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.media_data_list_row, parent, false);
		return v;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final TextView mediadate = (TextView) view.findViewById(R.id.media_date);
		final TextView carnummedia = (TextView) view.findViewById(R.id.car_num_media);
		final Button connection = (Button) view.findViewById(R.id.media_connection_text);
		final TextView mediafinish = (TextView) view.findViewById(R.id.media_finishtext);

		mediadate.setText("접수 일시 : "+ cursor.getString(cursor.getColumnIndex("RECT_DT")));
		carnummedia.setText(cursor.getString(cursor.getColumnIndex("CAR_NO")));
		Log.d("pk", cursor.getCount() + "");
		
		
	connection.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			connection.setVisibility(v.GONE);
			mediafinish.setVisibility(v.VISIBLE);
			
		}
	});
	}


}
