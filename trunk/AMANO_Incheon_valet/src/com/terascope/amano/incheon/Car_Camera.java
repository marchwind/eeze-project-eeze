package com.terascope.amano.incheon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;
import com.terascope.amano.R;
import com.terascope.amano.incheon.dto.LoginDto;
import com.terascope.amano.incheon.dto.UpdDto;

public class Car_Camera extends Activity {

	UpdDto upddto;
	MyCameraSurface mSurface;
	String mRootPath;
	//static final String PICFOLDER = "Picture";

	double latPoint = 0;
	double lngPoint = 0;

	String FileName;
	ArrayList<String> fileNames = new ArrayList<String>();
	int nWidth, nHeight;

	private String filename,path;
	private Button mShutter, car_camera_cancel_btn;
	private Boolean btnindex = true, index = true;
	private CheckBox car_camera_flash;
	private Intent i;
	
	private SharedPreferences pref = null;
	private SharedPreferences.Editor ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_camera);
		

		mShutter = (Button) findViewById(R.id.mShutter);
		car_camera_flash = (CheckBox)findViewById(R.id.car_camera_flash);
		car_camera_cancel_btn = (Button) findViewById(R.id.car_camera_cancel_btn);

		mSurface = (MyCameraSurface) findViewById(R.id.previewFrame);
	/*	Camera.Parameters params = mSurface.mCamera.getParameters();
		pref = getSharedPreferences("pref", MODE_PRIVATE);
		if(pref.getString("flash", "t")=="a"){
			car_camera_flash.setImageResource(R.drawable.check_on_icon);
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mSurface.mCamera.setParameters(params);
			mSurface.mCamera.startPreview();
		}else if(pref.getString("flash", "t")=="c"){
			car_camera_flash.setImageResource(R.drawable.check_off_icon);
			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			mSurface.mCamera.setParameters(params);
			mSurface.mCamera.startPreview();
		}else{
			Toast.makeText(this, "후레쉬.", 1).show();
		}*/
		
		car_camera_flash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Camera.Parameters params = mSurface.mCamera.getParameters();
				if(car_camera_flash.isChecked()==true){
				
					params.setFlashMode(Parameters.FLASH_MODE_OFF);
					mSurface.mCamera.setParameters(params);
					mSurface.mCamera.startPreview();
					car_camera_flash.setChecked(false);
					
				}else{
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					mSurface.mCamera.setParameters(params);
					mSurface.mCamera.startPreview();
					car_camera_flash.setChecked(true);
					
				}
				/*if (index) {
					car_camera_flash.setImageResource(R.drawable.check_on_icon);
					index = false;
					params.setFlashMode(Parameters.FLASH_MODE_TORCH);
					mSurface.mCamera.setParameters(params);
					mSurface.mCamera.startPreview();
					//flashOn();
				} else {
					car_camera_flash.setImageResource(R.drawable.check_off_icon);
					index = true;
					params.setFlashMode(Parameters.FLASH_MODE_OFF);
					mSurface.mCamera.setParameters(params);
					mSurface.mCamera.startPreview();
					//flashOff();
				}*/
				
			}
		});
		mShutter.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {

				mSurface.mCamera.autoFocus(mAutoFocus);
				upddto = new UpdDto();
				upddto.setFILE_NM(filename);
				//upddto.setFILE_PATH(path);
				

			}

		});

		car_camera_cancel_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				i= new Intent(Car_Camera.this,MainActivity.class);
				startActivity(i);
			}
		});
		

		mRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();

		File fRoot = new File(mRootPath);

		if (fRoot.exists() == false) {

			if (fRoot.mkdir() == false) {

				Toast.makeText(this, "사진이 저장되었습니다.", 1).show();

				finish();

				return;

			}

		}

	}

	// ��Ŀ�� �����ϸ� �Կ� �㰡

	AutoFocusCallback mAutoFocus = new AutoFocusCallback() {

		public void onAutoFocus(boolean success, Camera camera) {

			mShutter.setEnabled(success);

			mSurface.mCamera.takePicture(null, null, mPicture);

		}

	};

	// ���� ����.

	PictureCallback mPicture = new PictureCallback() {

		public void onPictureTaken(byte[] data, Camera camera) {

			// ��¥�� ���� �̸� �����

			Calendar calendar = Calendar.getInstance();

			FileName = String.format("%02d%02d%02d%02d%02d%02d.jpg",

			calendar.get(Calendar.YEAR) % 100,
					calendar.get(Calendar.MONTH) + 1,

					calendar.get(Calendar.DAY_OF_MONTH),
					calendar.get(Calendar.HOUR_OF_DAY),

					calendar.get(Calendar.MINUTE),
					calendar.get(Calendar.SECOND));

			path = mRootPath + "/" + FileName;

			File file = new File(path);

			try {

				FileOutputStream fos = new FileOutputStream(file);

				fos.write(data);

				fos.flush();

				fos.close();

			} catch (Exception e) {

				return;

			}

			String aa = path.toString();
			Log.i("aa",aa);
			// ������ �������� ����

			Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

			Uri uri = Uri.parse("file://" + path);

			intent.setData(uri);

			sendBroadcast(intent);

			Toast.makeText(getApplicationContext(), "사진이 저장되었습니다.", 0).show();

			camera.startPreview();

		}

	};

}

@SuppressLint("Instantiatable")
class MyCameraSurface extends SurfaceView implements SurfaceHolder.Callback {

	SurfaceHolder mHolder;

	Camera mCamera;

	@SuppressLint("Instantiatable")
	public MyCameraSurface(Context context, AttributeSet attrs) {

		super(context, attrs);

		mHolder = getHolder();

		mHolder.addCallback(this);

		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}

	// ǥ�� ��� ī�޶� �����ϰ� �̸����� ����

	public void surfaceCreated(SurfaceHolder holder) {

		mCamera = Camera.open();

		mCamera.setDisplayOrientation(90);

		try {

			mCamera.setPreviewDisplay(mHolder);

		} catch (IOException e) {

			mCamera.release();

			mCamera = null;

		}

	}

	// ǥ�� �ı��� ī�޶� �ı��Ѵ�.

	public void surfaceDestroyed(SurfaceHolder holder) {

		if (mCamera != null) {

			mCamera.stopPreview();

			mCamera.release();

			mCamera = null;

		}

	}

	// ǥ���� ũ�Ⱑ ������ �� ������ �̸����� ũ�⸦ ���� �����Ѵ�.

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		Camera.Parameters params = mCamera.getParameters();

		params.setPreviewSize(width, height);

		params.setPictureSize(width, height);

		params.setRotation(90);

		mCamera.setParameters(params);

		mCamera.startPreview();

	}
}