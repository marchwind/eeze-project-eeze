package com.terascope.amano.incheon.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertView
{	
	public static void showError(String message, Context ctx)
	{
		showAlert("오류", message, ctx);
	}
	
	public void showAlert(String message, Context ctx)
	{
		showAlert("주의", message, ctx);
	}
	
	public static void showAlert(String title, String message, Context ctx)
	{
		//Create a builder	
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(title);
		builder.setMessage(message);
		//add buttons and listener
		builder.setPositiveButton("확인", new EmptyListener());
		//Create the dialog
		AlertDialog ad = builder.create();
		//show
		ad.show();	
	}
	
	
}

class EmptyListener implements android.content.DialogInterface.OnClickListener {

//	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		// EventListener ���?dismiss()
	}

}
