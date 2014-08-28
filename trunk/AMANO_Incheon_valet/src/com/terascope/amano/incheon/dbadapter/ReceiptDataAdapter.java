package com.terascope.amano.incheon.dbadapter;

import com.squareup.otto.Subscribe;
import com.terascope.amano.R;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.common.Prefs;
import com.terascope.amano.incheon.dto.UpdDto;
import com.terascope.amano.incheon.service.CommandResult;
import com.terascope.amano.incheon.service.RectCommand;
import com.terascope.amano.incheon.service.UploadCommand;

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

public class ReceiptDataAdapter extends CursorAdapter {

	private Prefs myprefs =null;
	public ReceiptDataAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		  View v = inflater.inflate(R.layout.receipt_data_list_row, parent, false);
		  return v;
	}

	@Override
	public void bindView(View view, Context context, final Cursor cursor) {
		final TextView receiptedate = (TextView)view.findViewById(R.id.data_date);
		final TextView receiptcarnum = (TextView)view.findViewById(R.id.car_num_data);
		final Button connection = (Button) view.findViewById(R.id.connection_text);
		final TextView mediafinish = (TextView) view.findViewById(R.id.finishtext);
		this.myprefs = new Prefs(context);
	
		
		Log.d("pk", cursor.getCount()+"");
		Log.d("reg",cursor.getString(cursor.getColumnIndex("REG_DT"))+"dd");
		if(cursor.getString(cursor.getColumnIndex("UPD_DT"))==null ||cursor.getString(cursor.getColumnIndex("UPD_DT"))==""){
			connection.setVisibility(view.GONE);
			mediafinish.setVisibility(view.VISIBLE);
			mediafinish.setText("대기중");

			 StringBuffer sb = new StringBuffer(cursor.getString(cursor.getColumnIndex("REG_DT")));
			    /*sb.insert(4, "/");
			    sb.insert(7, "/");
			    sb.insert(10, " ");
			    sb.insert(13, ":");
			    sb.insert(16, ":");*/
			    
			  //receiptedate.setText("접수 일시 : "+sb.toString());
			    receiptedate.setText(sb.toString());
			  receiptcarnum.setText(cursor.getString(cursor.getColumnIndex("CAR_NO")));  
		}else{
			connection.setVisibility(view.GONE);
			mediafinish.setVisibility(view.VISIBLE);
			mediafinish.setText("완료");
			
			
			 StringBuffer sb = new StringBuffer(cursor.getString(cursor.getColumnIndex("REG_DT")));
			    /*sb.insert(4, "/");
			    sb.insert(7, "/");
			    sb.insert(10, " ");
			    sb.insert(13, ":");
			    sb.insert(16, ":");*/
			    
			  //receiptedate.setText("접수 일시 : "+sb.toString());
			    receiptedate.setText(sb.toString());
			  receiptcarnum.setText(cursor.getString(cursor.getColumnIndex("CAR_NO")));  
		}
		Log.d("upd",cursor.getString(cursor.getColumnIndex("UPD_DT"))+"dd");
	connection.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				
				connection.setVisibility(v.GONE);
				mediafinish.setVisibility(v.VISIBLE);
				UpdDto dto = new UpdDto();
				dto.setMAC_ADRES(myprefs.getMacAdress());
				dto.setVL_NO(cursor.getString(cursor.getColumnIndex("_id")));
				dto.setFILE_NM(cursor.getString(cursor.getColumnIndex("FILE_NM")));
				dto.setFLAG(cursor.getString(cursor.getColumnIndex("MM_TYPE")));
				dto.setREG_DT(cursor.getString(cursor.getColumnIndex("REG_DT")));
				BusBuilder.getInstance().post(new UploadCommand("UPLOAD", dto));
				
			}
		});
		
	}
	@Subscribe
	public void onCommandResult(CommandResult cr) {
		if (cr.getResultCd() == CommandResult.CD_SUCCESS) {
		Log.i(this.getClass().getName(), cr.getResultCd()+"/"+cr.getResultMsg());
		}else{
			Log.i("통신거절","거절");
		}
	}
	

}
