package com.terascope.amano.incheon;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.VideoView;

import com.sewoo.port.android.BluetoothPort;
import com.squareup.otto.Subscribe;
import com.terascope.amano.R;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.batch.MainService;
import com.terascope.amano.incheon.common.BluetoothPrinterHelper;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;
import com.terascope.amano.incheon.common.Util;
import com.terascope.amano.incheon.dao.helper.DbHelper;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.dto.ResultDto;
import com.terascope.amano.incheon.dto.UpdDto;
import com.terascope.amano.incheon.service.CommandResult;
import com.terascope.amano.incheon.service.RectCommand;

public class Car_Video extends Activity implements SurfaceHolder.Callback {
	private static Car_Video singleton;

	public static String TAG = "",today;
	
	private PopupWindow mPopupWindow;
	UpdDto upddto;
	private RectDto rec;
	// Video View 객체
	private VideoView mVideoView = null;
	// 카메라 객체
	private Camera mCamera;
	// 레코더 객체 생성
	private MediaRecorder recorder = null;
	// 아웃풋 파일 경로
	private static final String OUTPUT_FILE = Environment
			.getExternalStorageDirectory() + "/";
	private static final String OUTPUT_FILE_NAME = "_VDO.mp4";
	// 녹화 시간 - 10초
	private static final int RECORDING_TIME = 30000;
	private Boolean btnindex = true, index = true;
	private Button video_onoff_btn, video_cancel_btn;
	private CheckBox video_flash_btn;
	private String filename, outfile, strDay, strTime, mac_Addr;
	private Intent i;
	Prefs myprefs = null;
	private ImageView rec_icon;
	private DbHelper dbhelper;
	private DbHelper sdb;
	private SimpleDateFormat sdfDay, sdfTime;
	private UpdDto upd;
	private LinearLayout layout2;
	private Util util;
	private BluetoothPrinterHelper bp;
	private int vwidth=640, vheight=480;
	public static String tempDir = Environment.getExternalStorageDirectory()
			+ "/Amano/";
	
	ProgressDialog dialog;

	public static Car_Video getInstance() {
		if (singleton == null) {
			synchronized (Car_Video.class) {
				if (singleton == null)
					singleton = new Car_Video();
			}
		}
		return singleton;
	}

	protected void onPause() {
		// TODO Auto-generated method stub
		// on Pause 상태에서 카메라 ,레코더 객체를 정리한다
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
		if (recorder != null) {
			recorder.stop();
			recorder.release();
			recorder = null;
		}
		
		BusBuilder.getInstance().unregister(this);
		super.onPause();
	}
	
	// 카메라 프리뷰를 설정한다
	private void setCameraPreview(SurfaceHolder holder) {
		try {
			// 카메라 객체를 만든다
			mCamera = Camera.open();
			Camera.Parameters params = mCamera.getParameters();
			
			mCamera.setDisplayOrientation(90);
			mCamera.setPreviewDisplay(holder);
			
			Log.i(TAG, "resolution : " + this.myprefs.getResolution());
			
			if(this.myprefs.getResolution().equals("") || this.myprefs.getResolution().equals(null)){
				String[] rwh = CommonSet.Resolution.SD.split("_");
				vwidth = Integer.parseInt(rwh[0]);
				vheight = Integer.parseInt(rwh[1]);
			} else {
				String[] r = myprefs.getResolution().split("_");
				vwidth = Integer.parseInt(r[0]);
				vheight = Integer.parseInt(r[1]);
			}
			
			params.setPictureSize(vwidth, vheight);
			mCamera.setParameters(params);
			
			mCamera.startPreview();

			camera_flash_prefer();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		// 서피스가 만들어졌을 때의 대응 루틴
		setCameraPreview(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		// TODO Auto-generated method stub
		// 서피스 변경되었을 때의 대응 루틴
		/*
		 * if (mCamera != null) { Camera.Parameters parameters =
		 * mCamera.getParameters(); // 프리뷰 사이즈 값 재조정
		 * parameters.setPreviewSize(width, height);
		 * mCamera.setParameters(parameters);
		 * 
		 * mCamera.setDisplayOrientation(90);
		 * 
		 * // 프리뷰 다시 시작 mCamera.startPreview(); }
		 */
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		// 서피스 소멸시의 대응 루틴

		// 프리뷰를 멈춘다
		if (mCamera != null) {
			mCamera.stopPreview();
			// 카메라 객체 초기화
			mCamera = null;
		}

	}

	// 프리뷰(카메라가 찍고 있는 화상을 보여주는 화면) 설정 함수
	private void setPreview() {
		// 1) 레이아웃의 videoView 를 멤버 변수에 매핑한다
		mVideoView = (VideoView) findViewById(R.id.car_video_view);
		// 2) surface holder 변수를 만들고 videoView로부터 인스턴스를 얻어온다
		final SurfaceHolder holder = mVideoView.getHolder();
		// 3)표면의 변화를 통지받을 콜백 객체를 등록한다
		holder.addCallback(this);
		// 4)Surface view의 유형을 설정한다, 아래 타입은 버퍼가 없이도 화면을 표시할 때 사용된다.카메라 프리뷰는 별도의
		// 버퍼가 필요없다
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	}
	
	private boolean prepareDirectory() {
		try {
			if (makedirs()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private boolean makedirs() {
		File tempdir = new File(CommonSet.SAVE_PATH +"M"+today +"/" );
		if (!tempdir.exists())
			tempdir.mkdirs();
		
		return false;
	}


	private void setButtons() {
		video_flash_btn.setOnCheckedChangeListener(checkToggleListner);

		video_onoff_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (btnindex) {

					rec_icon.setVisibility(View.VISIBLE);

					video_onoff_btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
					video_onoff_btn.setText("완료");
					video_onoff_btn.setGravity(Gravity.CENTER);
					btnindex = false;

					if (mVideoView.getHolder() == null)
						Log.e("CAM TEST", "View Err!!!!!!!!!!!!!!!");
					beginRecording(mVideoView.getHolder());

				} else {
					/*
					 * video_onoff_btn.setCompoundDrawablesWithIntrinsicBounds(R.
					 * drawable.video_on_icon, 0, 0, 0);
					 * video_onoff_btn.setText("촬영");
					 */

					btnindex = true;
					if (recorder != null) {

						Log.e("CAM TEST", "CAMERA STOP!!!!!");
						recorder.stop();
						recorder.release();
						recorder = null;
					}
					// 프리뷰가 없을 경우 다시 가동 시킨다
					if (mCamera == null) {
						Log.e("CAM TEST", "Preview Restart!!!!!"); // 프리뷰 다시 설정
						setCameraPreview(mVideoView.getHolder()); // 프리뷰 재시작
						mCamera.startPreview();
					}
					
				

					// BusBuilder.getInstance().post(new
					// UploadCommand("MAIN_ACTIVITY", upd));
					

					recieptSuccess();
				}

			}
		});
		
		video_cancel_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new AlertDialog.Builder(Car_Video.this)
						.setTitle(
								getString(R.string.alert_receipt_cancel_title))
						.setMessage(
								getResources().getString(
										R.string.receipt_cancel_alert))
						.setPositiveButton(getString(R.string.alert_Ok_text),
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {

										if (recorder != null) {
											Log.e("CAM TEST","CAMERA STOP!!!!!");
											recorder.stop();
											recorder.release();
											recorder = null;
										}

										if (mCamera != null) {
											mCamera.stopPreview();
											// mCamera.release();
											mCamera = null;
											Log.e("CAM TEST", "#3 Release Camera  _---> OK!!!");
										}
									

										finish();
									}
								})
						.setNegativeButton(
								getString(R.string.alert_Cancel_text),
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();

			}

		});

	}

	private void recieptSuccess() {

		DbHelper db = new DbHelper(this);
		db.open();

		ContentValues values = new ContentValues();

		values.put("VL_NO", rec.getVL_NO());
		values.put("RECT_USR_ID", rec.getRECT_USR_ID());
		values.put("RECT_USR_NM", rec.getRECT_USR_NM());
		values.put("RECT_DT", rec.getRECT_DE() + rec.getRECT_HM());
		values.put("CAR_NO", rec.getCAR_NO());
		values.put("CARNO_AUTO_RECG_AT", rec.getCARNO_AUTO_RECG_AT());
		values.put("CAR_SERS_CD", rec.getCAR_SERS_CD());
		values.put("CAR_SERS_NM", rec.getCAR_SERS_NM());
		values.put("TRVL_TERM", rec.getTRVL_TERM());
		values.put("ENTRY_DT", rec.getENTRY_DE() + " " + rec.getENTRY_HM());
		values.put("CAR_DAMG_AT", rec.getCAR_DAMG_AT());
		values.put("CAR_DAMG_FILE_NM", rec.getCAR_DAMG_FILE_NM());
		
		values.put("RESV_AT", rec.getRESV_AT());
		values.put("CAR_TRANS_CD", rec.getCAR_TRANS_CD());
		values.put("CSTMR_SIGN_AT", rec.getCSTMR_SIGN_AT());
		values.put("CSTMR_SIGN_FILE_NM", rec.getCSTMR_SIGN_FILE_NM());
		values.put("LNG_SHRT_TY_CD", rec.getLNG_SHRT_TY_CD());
		// values.put("CSTMR_SIGN_FILE_PATH", rec.getCSTMR_SIGN_FILE_PATH());

		db.insertColumn(getString(R.string.db_rect_table_name), values);
		values.clear();
		db.close();
		
		sendRecieptData();
	}
	
	public void sendRecieptData(){
		dialogLoadingView();
		BusBuilder.getInstance().post(new RectCommand("RECIEPT", rec));
		
	}
	

	@Subscribe
	public void onCommandResult(CommandResult cr) {
		dialog.dismiss();
		if (cr.getResultCd() == CommandResult.CD_SUCCESS){
			multiSuccess();
		} else {
			new AlertDialog.Builder(Car_Video.this)
			.setTitle("접수오류")
			.setMessage("접수 통신에 오류가 발생하였습니다.\n다시 시도하시겠습니까?")
			.setPositiveButton(getString(R.string.alert_Ok_text),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							sendRecieptData();
						}
					})
			.setNegativeButton(getString(R.string.alert_Cancel_text), 
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					}).show();
		}
	}
	
	private void multiSuccess() {
		DbHelper dhel = new DbHelper(this);
		dhel.open();
		String v = "V";
		
		upd.setMAC_ADRES(mac_Addr);
		upd.setVL_NO(rec.getVL_NO());
		upd.setUPD_DE(strDay);
		upd.setUPD_HM(strTime);
		upd.setFLAG(v);
		upd.setFILE_NM(filename);
		upd.setFILE_PATH(outfile);
		upd.setREG_DT(strDay+strTime);
		Log.e(TAG, "insert AM_MM REGDT >>> " + upd.getREG_DT());
		Log.e(TAG, "insert AM_MM FILEMN is >>> " + upd.getFILE_NM());
		ContentValues values = new ContentValues();
		values.put("VL_NO", upd.getVL_NO());
		values.put("REG_DT", upd.getREG_DT());
		values.put("FILE_NM", upd.getFILE_NM());
		values.put("R_FILE_PATH", outfile);
		values.put("MM_TYPE", v);
		dhel.insertColumn(getString(R.string.db_mm_table_name), values);
		Log.i("dup", upd.getFILE_NM() + upd.getFILE_PATH());
		values.clear();
		dhel.close();

		if(BluetoothPort.getInstance().isConnected()){
			printProccess();
		} else {
			if(myprefs.getBluetoothID() != null && myprefs.getBluetoothID() != ""){
				bp.bluetoothIsConnected();
				if(bp.bluetoothConnected){
					printProccess();
				}
			} else {
				Toast.makeText(this, "프린트가 설정되어 있지 않습니다. 프린트를 설정해 주세요.", Toast.LENGTH_LONG).show();
			}
			
			recieptAllSuccess();
		}

		

	}
	
	public void printProccess(){
		ResultDto res = bp.valetParkPrint(rec, CommonSet.PRINT_CUSTOMER);
		
		if(res.getRTN_CD().equals(CommonSet.BLUETHOOTH_ERROR_CODE)){
			Toast.makeText(this, res.getRTN_MSG(), Toast.LENGTH_LONG).show();
			recieptAllSuccess();
		} else {
			new AlertDialog.Builder(Car_Video.this)
			.setTitle("보관증 출력")
			.setMessage("보관용 차량증을 출력하시겠습니까?")
			.setNegativeButton(getString(R.string.alert_Cancel_text), 
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							recieptAllSuccess();
						}
					})
			.setPositiveButton(getString(R.string.alert_Ok_text),
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							ResultDto res  = bp.valetParkPrint(rec, CommonSet.PRINT_OFFER);
							
							if(res.getRTN_CD().equals(CommonSet.BLUETHOOTH_ERROR_CODE)){
								Toast.makeText(Car_Video.this, res.getRTN_MSG(), Toast.LENGTH_LONG).show();
							} 
							recieptAllSuccess();
							
						}
					}).show();
		}
	}
	
	public void recieptAllSuccess(){
		new AlertDialog.Builder(Car_Video.this)
		.setTitle(getString(R.string.receipt_title_text))
		.setMessage(getResources().getString(R.string.receipt_save_alert))
		.setPositiveButton(getString(R.string.alert_Ok_text),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						finish();
					}
				}).show();
	}
	
	

	public void recoderSet(){
		
	}

	@SuppressLint({ "SimpleDateFormat", "NewApi" })
	private void beginRecording(SurfaceHolder holder) {
		// 레코더 객체 초기화
		Log.e("CAM TEST", "#1 Begin REC!!!");
		if (recorder != null) {
			recorder.stop();
			recorder.release();
		}

		String state = android.os.Environment.getExternalStorageState();
		if (!state.equals(android.os.Environment.MEDIA_MOUNTED)) {
			Log.e("CAM TEST", "I/O Exception");
		}

		Log.e("CAM TEST", "#3 Release Camera!!!");
		if (mCamera != null) {
			// mCamera.stopPreview();
			// mCamera.release();
			// mCamera = null;
			Log.e("CAM TEST", "#3 Release Camera  _---> OK!!!");
		}

		try {
			recorder = new MediaRecorder();
			// Video/Audio 소스 설정
			mCamera.unlock();
			recorder.setCamera(mCamera);

			recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			// 비디오 사이즈를 수정하면 prepare 에러가 난다, 왜 그럴까? -> 특정 해상도가 있으며 이 해상도에만 맞출 수가
			// 있다
			/*
			 * CamcorderProfile profile =
			 * CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
			 * recorder.setVideoSize(profile.videoFrameWidth,
			 * profile.videoFrameHeight);
			 */
			recorder.setVideoSize(vwidth, vheight);
			// recorder.setVideoFrameRate(25);
			// Video/Audio 인코더 설정
			// 오디오와영상 인코더 설정
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

			// 녹화 시간 한계 , 10초

			filename = rec.getVL_NO() + OUTPUT_FILE_NAME;
			Log.i("filename", filename);

			outfile = CommonSet.SAVE_PATH + filename;
			recorder.setOutputFile(outfile);

			recorder.setMaxDuration(RECORDING_TIME);
			// 프리뷰를 보여줄 서피스 설정

			recorder.setOrientationHint(90);
			recorder.setPreviewDisplay(holder.getSurface());
			// 녹화할 대상 파일 설정
			// recorder.setOutputFile(OUTPUT_FILE);
			recorder.prepare();
			recorder.start();

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("CAM TEST", "Error Occur???!!!");
			e.printStackTrace();
		}

	}

	CompoundButton.OnCheckedChangeListener checkToggleListner = new CompoundButton.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			camera_flash(isChecked);

		}
	};

	public void camera_flash(Boolean c) {
		Camera.Parameters params = mCamera.getParameters();
		// Camera params = mCamera.getParameters();
		mCamera.setParameters(params);
		List<String> flashlist = params.getSupportedFlashModes();

		if (c) {
			if (flashlist != null) {
				video_flash_btn.setChecked(true);
				params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
				mCamera.setParameters(params);
			}
		} else {
			video_flash_btn.setChecked(false);
			if (flashlist != null) {
				params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
				mCamera.setParameters(params);
			}
		}
	}

	public void camera_flash_prefer() {
		if (myprefs.getFlashStatue() == CommonSet.SettingType.FLASH_ALL) {
			camera_flash(true);
		} else if (myprefs.getFlashStatue() == CommonSet.SettingType.FLASH_CUSTOM) {
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
	public void onBackPressed() {

		new AlertDialog.Builder(this)
				.setTitle(getString(R.string.alert_receipt_cancel_title))
				.setMessage(
						getResources().getString(R.string.receipt_cancel_alert))
				.setPositiveButton(getString(R.string.alert_Ok_text),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						})
				.setNegativeButton(getString(R.string.alert_Cancel_text),
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						}).show();
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_video);

		TAG = this.getClass().getName();
		
		util = new Util(this);
		bp = new BluetoothPrinterHelper(this);

		this.myprefs = new Prefs(getApplicationContext());
		
		today = myprefs.getToday();
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HHmmss");
		long now = System.currentTimeMillis();
		Date date = new Date(now);

		mac_Addr = myprefs.getMacAdress();
		// SimpleDateFormat sdfAll = new SimpleDateFormat("yyyyMMddHHmmss");
		strDay = sdfDay.format(date);
		strTime = sdfTime.format(date);

		Bundle bundle = getIntent().getExtras();
		rec = (RectDto) bundle.getSerializable(CommonSet.RECEIPT_DATA_NAME);
		upd = new UpdDto();
		layout2 = (LinearLayout) findViewById(R.id.layout2);
		
		Log.e("dto", rec.getVL_NO());
		video_onoff_btn = (Button) findViewById(R.id.video_onoff_btn);
		video_flash_btn = (CheckBox) findViewById(R.id.video_flash_btn);
		video_cancel_btn = (Button) findViewById(R.id.video_cancel_btn);
		rec_icon = (ImageView) findViewById(R.id.rec_icon);
		// 세로화면 고정으로 처리한다
		// SCREEN_ORIENTATION_LANDSCAPE - 가로화면 고정
		// SCREEN_ORIENTATION_PORTRAIT - 세로화면 고정
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// 프리뷰를 설정한다
		prepareDirectory();
		setPreview();

		// 버튼을 설정한다
		setButtons();
	}

	public void dialogLoadingView() {
		dialog = new ProgressDialog(this);
		dialog.setTitle("접수중");
		dialog.setMessage("잠시만 기다리세요.");
		dialog.setIndeterminate(true);
		dialog.setCancelable(true);
		dialog.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
		dialog.show();
	}
	
	protected void startMainService() {
		Intent intent = new Intent(getApplicationContext(), MainService.class);
		startService(intent);
		// startService(new Intent("com.hsit.iqsc.IQSClientService"));
	}

	protected void stopMainService() {
		stopService(new Intent(getApplicationContext(), MainService.class));
		// stopService(new Intent("com.hsit.iqsc.IQSClientService"));
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		BusBuilder.getInstance().register(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		bp.btDisconn();
	}
	
}