package makemachine.android.examples;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.terascope.amano.R;
import com.terascope.amano.incheon.Receipt;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;
import com.terascope.amano.incheon.dto.ReceiptDto;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.dto.ResvDto;
import com.terascope.amano.incheon.service.ResvCommand;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ParkInProcess_CameraLPR extends Activity {
	
	private static final String TAG = "CameraRegonize";
	
	private ParkInProcess_CameraLPR dThis;
	private Preview mPreview;
	private byte[][] mImageData;	
	private boolean gFocussed = false;
	private boolean gCameraPressed = false;

	private static SharedPreferences sPrefs = null;
	public static final String KEY_POPUP_ENV = "key_env";
	public static final String KEY_POPUP_ENV_RUN_MODE = "key_env_run";

	public String mFilename; 
	private int mFileNameYear;
	private int mFileNameMonth;
	private int mFileNameDay; 
	private int mFileNameCount; 

	public static final String SAVE_FILE_YEAR = "sava_file_year"; 
	public static final String SAVE_FILE_MONTH = "sava_file_month";
	public static final String SAVE_FILE_DATE = "sava_file_date"; 
	public static final String SAVE_FILE_COUNT = "sava_file_count"; 

	private String mFileimageRoute = "/sdcard/camera/image/";
	
	//private ToggleButton dTbLPR;
	private boolean dLPRGo = false;
	
	public EditText ev;
	
	public Button recamera_btn;
	private Button camera_receipt_btn;
	private Button camera_cancel_btn;
	private CheckBox camera_flash_btn;
	
	private RectDto dto;
	
	private Prefs myprefs = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_receipt);
		
		dThis = this;
		this.myprefs = new Prefs(getApplicationContext());
		
		mPreview = (Preview) findViewById(R.id.preview);
		
		Bundle bundle = getIntent().getExtras();		
		dto = (RectDto) bundle.getSerializable(CommonSet.RECEIPT_DATA_NAME);
		dto.setCARNO_AUTO_RECG_AT("Y");
		
		ev = (EditText) findViewById(R.id.plate);
		
		recamera_btn = (Button) findViewById(R.id.recamera_btn);
		recamera_btn.setEnabled(false);
		recamera_btn.setOnClickListener(btnEventListener);
		camera_receipt_btn = (Button) findViewById(R.id.camera_receipt_btn);
		camera_receipt_btn.setOnClickListener(btnEventListener);
		camera_cancel_btn = (Button) findViewById(R.id.camera_cancel_btn);
		camera_cancel_btn.setOnClickListener(btnEventListener);
		
		mPreview.setControl(this, ev);
		mPreview.startView();
		//mPreview.setControl(this);
		mImageData = new byte[6][];
		
		camera_flash_btn = (CheckBox)findViewById(R.id.camera_flash_radio);
	    camera_flash_btn.setOnCheckedChangeListener(checkToggleListner);
		
	    ev.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				Log.i(TAG,"edit focus >> " + hasFocus);
				if(hasFocus) {
					mPreview.stopView();
					dto.setCARNO_AUTO_RECG_AT("N");
					if (!mPreview.isRecognition){
						ev.setText("");
					}
				} else {
					InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
				    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
				}
			}
		});
	    
	    ev.setImeOptions(EditorInfo.IME_ACTION_DONE);
	    ev.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_DONE){
					Log.i(TAG,"edit focus out");
					ev.clearFocus();
				}
				return false;
			}
		});
	    
	    
	    //dTbLPR = (ToggleButton)findViewById(R.id.tbLPR);
		
		// listener
		//
		//mTbLPR.setOnClickListener((OnClickListener) this);
	    
	}
	
	
	public void setReButtonText(String txt){
		recamera_btn.setText(txt);
		recamera_btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
		recamera_btn.setGravity(Gravity.CENTER);
		recamera_btn.setPadding(0, 0, 0, 0);
		recamera_btn.setEnabled(false);
	}
	
	public void setReButtonEnable() {
		int imgResource = R.drawable.camera_icon;
		recamera_btn.setCompoundDrawablesWithIntrinsicBounds(imgResource, 0, 0, 0);
		recamera_btn.setText(getString(R.string.camera_rebtn_text));
		recamera_btn.setGravity(Gravity.CENTER_VERTICAL);
		recamera_btn.setPadding((int) getResources().getDimension(R.dimen.camera_rebtn_padding), 0, 0, 0);
		recamera_btn.setEnabled(true);
	}

	
	@Override
	protected void onResume() {
		if (mPreview.mCamera == null) {
			mPreview.startView();
		}
		super.onResume();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	Button.OnClickListener btnEventListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch(v.getId()) {
			case R.id.recamera_btn :
				mPreview.startView();
				dto.setCARNO_AUTO_RECG_AT("Y");
				break;
			case R.id.camera_receipt_btn :
				
				if(ev.getText().toString().trim() == "" || ev.getText().toString() == null || ev.getText().toString().length() < 7) {
					new AlertDialog.Builder(ParkInProcess_CameraLPR.this)
					.setTitle(getString(R.string.alert_title_text))
					.setMessage(getResources().getString(R.string.carNumber_need_alert))
					.setPositiveButton(getString(R.string.alert_Ok_text), new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {}
					})
					.show();	
				} else {
					
					Intent i = new Intent(ParkInProcess_CameraLPR.this, Receipt.class);
					
					dto.setCAR_NO(ev.getText().toString());
					
					Bundle bundle = new Bundle();
					
					bundle.putSerializable(CommonSet.RECEIPT_DATA_NAME, dto);
					i.putExtras(bundle);

					startActivity(i);
					mPreview.mCamera.stopPreview();
					finish();
				}
				break;
			case R.id.camera_cancel_btn :
				mPreview.stopView();
				new AlertDialog.Builder(ParkInProcess_CameraLPR.this)
				.setTitle(getString(R.string.alert_receipt_cancel_title))
				.setMessage(getResources().getString(R.string.receipt_cancel_alert))
				.setPositiveButton(getString(R.string.alert_Ok_text), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(mPreview.mCamera != null){
							mPreview.mCamera.stopPreview();
						}
						finish();
					}
				})
				.setNegativeButton(getString(R.string.alert_Cancel_text), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
				.show();
				
				break;
			}
			
		}
	}; 
	
	CompoundButton.OnCheckedChangeListener checkToggleListner = new CompoundButton.OnCheckedChangeListener() {
 		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
 			
 			camera_flash(isChecked);
 			
		} 
	};	
	
	public void camera_flash(Boolean c){
		
		Parameters params = mPreview.mCamera.getParameters();
		mPreview.mCamera.setParameters(params);
		List<String> flashlist=params.getSupportedFlashModes();
		
		if(c){
			if(flashlist != null){
				camera_flash_btn.setChecked(true);
				params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				mPreview.mCamera.setParameters(params);					
			}
		}else{
			camera_flash_btn.setChecked(false);
			if(flashlist != null){
		        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				mPreview.mCamera.setParameters(params);	
		    }
		}
	}
	
	public void camera_flash_prefer() {
		if(myprefs.getFlashStatue() == CommonSet.SettingType.FLASH_ALL){
			camera_flash(true);
		}else if (myprefs.getFlashStatue() == CommonSet.SettingType.FLASH_CUSTOM) {
			camera_flash(false);

		} else if(myprefs.getFlashStatue()==CommonSet.SettingType.FLASH_TIME) {
			if((Integer.parseInt(myprefs.getFlashFromTime())<=Integer.parseInt(new SimpleDateFormat("HH").format(new Date())) )&& (Integer.parseInt(new SimpleDateFormat("HH").format(new Date()))<=Integer.parseInt(myprefs.getFlashToTime())))
			{
				camera_flash(true);
				Log.d("time1",myprefs.getFlashFromTime());
				Log.d("time2",myprefs.getFlashToTime());
			}
			 else
			{
				 camera_flash(false);
				 
				 Log.d("time11111",myprefs.getFlashFromTime());
					Log.d("time22222",myprefs.getFlashToTime());
			}

		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
/*
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
		if (mPreview.mCamera != null) {
			mPreview.mCamera.takePicture(mShutterCallback, mPictureCallbackRaw,
					mPictureCallbackJpeg);
			}
		}
*/
		return false;
	}


	@Override
	protected void onPause() {
		if (mPreview != null) {
			Log.e("onDestroy", "onDestroy...");

			if (mPreview.mCamera != null) {
				mPreview.mCamera.stopPreview();
				mPreview.mCamera.release();
				mPreview.mCamera = null;
			}
		}
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		if (mPreview != null) {
			Log.e("onDestroy", "onDestroy...");

			if (mPreview.mCamera != null) {
				mPreview.mCamera.stopPreview();
				mPreview.mCamera.release();
				mPreview.mCamera = null;
			}
		}
		super.onDestroy();
	}

	private int getPreFileName() {
		if (sPrefs == null) {
			sPrefs = getSharedPreferences(KEY_POPUP_ENV, Context.MODE_PRIVATE);
		}
		mFileNameYear = sPrefs.getInt(SAVE_FILE_YEAR, 0);
		mFileNameMonth = sPrefs.getInt(SAVE_FILE_MONTH, 0);
		mFileNameDay = sPrefs.getInt(SAVE_FILE_DATE, 0);
		mFileNameCount = sPrefs.getInt(SAVE_FILE_COUNT, 0);
		return mFileNameCount;
	}

	private String getRealFileName() {
		MakeFileName_Demo();
		return mFilename;
	}

	private void MakeFileName_Demo() {
		DecimalFormat decimalFormat = new DecimalFormat("00");// decimalformat
		DecimalFormat NumFormat = new DecimalFormat("0000");// 4�ڸ��� ǥ�� �Ѵ�.
		Calendar rightNow = Calendar.getInstance();// ��¥ �ҷ����� �Լ�
		int year = rightNow.get(Calendar.YEAR) % 100;// 100�� ������ �⵵ǥ�ø� 2009->9���� decimal�������� 09�� ǥ��
		int month = rightNow.get(Calendar.MONTH);// ��
		int date = rightNow.get(Calendar.DATE);// ��
		int num = getPreFileName(); // xml�� ���� �Ǿ��ִ� �ε��� ���� �ִ´�.
		String result =decimalFormat.format(month) + decimalFormat.format(date);
		/* ��¥�� 00��� ������ �ε����� 0000��� �̶� ��� ���� ���� */
		String FormatNum = NumFormat.format(num);
		mFilename = result + "_" + FormatNum;

		File[] files = new File("/sdcard/camera/image").listFiles();

		if (files.length == 0) {
			num++;
		} else if (files.length > 0) {

			if (CompareDate(year, month, date) == true) {
				num++;
			} else if (CompareDate(year, month, date) == false) {
		
				num = 0;
			}
		}

		SaveFileName(year, month, date, num);// xml�� ����
	}

	private boolean CompareDate(int year, int month, int date) {
		boolean ret = false;

		if (year == getFileNameYear()) {
			if (month == getFileNameMonth()) {
				if (date == getFileNameDay()) {
					ret = true;
				}
			}
		}

		return ret;
	}

	private int getFileNameYear() {
		return mFileNameYear;
	}

	private int getFileNameMonth() {
		return mFileNameMonth;
	}

	private int getFileNameDay() {
		return mFileNameDay;
	}


	private void SaveFileName(int year, int month, int date, int num) {
		SharedPreferences.Editor editor = sPrefs.edit();
		editor.putInt(SAVE_FILE_YEAR, year);
		editor.putInt(SAVE_FILE_MONTH, month);
		editor.putInt(SAVE_FILE_DATE, date);
		editor.putInt(SAVE_FILE_COUNT, num);
		editor.commit();
	}


	public int SaveImage() {

	int ret = 0;
		

			try {
			
				FileOutputStream fileoutstream = new FileOutputStream(mFileimageRoute+getRealFileName()+".jpg");
    			   	
    			fileoutstream.write(mImageData[0]);
    			fileoutstream.close();
    			System.gc();
			}
    			catch (FileNotFoundException fne) 
            	{
            		Log.e("writing and scanning image ", fne.toString());
            		fne.printStackTrace();
            		ret = -1;
                } 
                catch (IOException ioe) 
                {
            		Log.e("writing and scanning image ", ioe.toString());
            		ioe.printStackTrace();
            		ret = -1;
            	} catch (Exception e) 
            	{
            		ret = -1;
            	}	

		return ret;

	}



	Camera.PictureCallback mPictureCallbackRaw = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera c) {
			Log.e("mPictureCallbackRaw ", "11111");

		}
	};

	Camera.PictureCallback mPictureCallbackJpeg = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera c) {
		/*	Log.e("PictureCallback ", "11111");

			Log.e("PictureCallback ", "22222");

			mImageData[0] = data;
			
			Display defaultDisplay = ((WindowManager) getSystemService(WINDOW_SERVICE))
					.getDefaultDisplay();
			int width = defaultDisplay.getWidth();
			int height = defaultDisplay.getHeight();
			Log.e(String.valueOf(width), String.valueOf(height));
			Log.e(String.valueOf(width), String.valueOf(height));
			Log.e(String.valueOf(width), String.valueOf(height));
		
			ShowSaveDailog();
*/
		}

	};

	Camera.ShutterCallback mShutterCallback = new Camera.ShutterCallback() {
		public void onShutter() {
			Log.i(getClass().getSimpleName(), "SHUTTER CALLBACK");
		}
	};
	Camera.AutoFocusCallback cb = new Camera.AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera c) {
			Log.e("AutoFocusCallback ", "11111");

			if (success) {

				ToneGenerator tg = new ToneGenerator(
						AudioManager.STREAM_SYSTEM, 100);
				if (tg != null)
					tg.startTone(ToneGenerator.TONE_PROP_BEEP2);
				gFocussed = true;
				try {
					if (gCameraPressed) {
						if (mPreview.mCamera != null) {
							mPreview.mCamera.takePicture(mShutterCallback,
									mPictureCallbackRaw, mPictureCallbackJpeg);
						}
					}
				} catch (Exception e) {
					Log.i("Exc", e.toString());
				}
			} else {
				ToneGenerator tg = new ToneGenerator(
						AudioManager.STREAM_SYSTEM, 100);
				if (tg != null)
					tg.startTone(ToneGenerator.TONE_PROP_BEEP2);

				try {
					if (gCameraPressed) {
						if (mPreview.mCamera != null) {
							mPreview.mCamera.takePicture(mShutterCallback,
									mPictureCallbackRaw, mPictureCallbackJpeg);
						}
					}
				} catch (Exception e) {
					Log.i("Exc", e.toString());
				}
			}

		}
	};


	
	private void ShowSaveDailog() {
		
		Toast.makeText(this, "save image", Toast.LENGTH_SHORT).show();
		SaveImage();
		mImageData[0] = null;
		
		
		if (mPreview.mCamera != null) {
			// mPreview.mCamera.lock();
			try{
			mPreview.mCamera.startPreview();
			}
			catch (Exception e) {
				
			}									
		}	
	}
	
	
	public void onLPRClick(View v) {
		//String bStr = (String)dTbLPR.getText();
		
		if(dLPRGo == false ){
			dLPRGo = true;
			//dTbLPR.setText("Start");
			
			//mPreview.mCamera.takePicture(mShutterCallback, mPictureCallbackRawLPR, mPictureCallbackJpegLPR	);
			
		
		} else {
			dLPRGo = false;
			//dTbLPR.setText("Stop");
		}
		//Toast.makeText(this, (String)dTbLPR.getTag(), Toast.LENGTH_LONG).show();
		
	}
	
	Camera.PictureCallback mPictureCallbackRawLPR = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera c) {
			//Log.e("mPictureCallbackRaw ", "11111");
			//Toast.makeText(dThis, (String)dTbLPR.getText(), Toast.LENGTH_LONG).show();
			
		 
		}
	};
	
	Camera.PictureCallback mPictureCallbackJpegLPR = new Camera.PictureCallback() {

		public void onPictureTaken(byte[] data, Camera c) {
			//Log.e("mPictureCallbackRaw ", "11111");
			//Toast.makeText(dThis, (String)dTbLPR.getText(), Toast.LENGTH_LONG).show();
			
			try{
				mPreview.mCamera.startPreview();
			}
			catch (Exception e) {
			}		
		}
	};
	

}

	



