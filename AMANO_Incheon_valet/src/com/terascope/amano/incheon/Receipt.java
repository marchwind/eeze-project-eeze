package com.terascope.amano.incheon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.terascope.amano.R;
import com.terascope.amano.incheon.batch.BusBuilder;
import com.terascope.amano.incheon.common.CommonSet;
import com.terascope.amano.incheon.common.Prefs;
import com.terascope.amano.incheon.common.Util;
import com.terascope.amano.incheon.dto.CarInfoDto;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.dto.ResvDto;
import com.terascope.amano.incheon.service.CommandResult;
import com.terascope.amano.incheon.service.JsonToDto;
import com.terascope.amano.incheon.service.ResvCommand;

public class Receipt extends Activity implements OnClickListener {
	
	public static String TAG = "";
	public static String RESERVE = "RESERVE";
	
	private BroadcastReceiver mReceiver;
	
	LinearLayout mContent;
	signature mSignature;
	Button mClear, mGetSign, mCancel;
	public int count = 1;
	public String current = null;
	
	
	View mView;
	LockScrollView lockScrollView;
	boolean signedCheck = true;
	
	private Prefs myprefs = null;
	private long now;
	private Intent i;
	private Button receipt_finsh, receipt_cancel;
	private TextView receipt_num, receipt_name, receipt_date, car_num,
			return_date, term_text;
	private String nMoveDay, strNow, strTime, strAll;
	Calendar cal;
	Date date;
	int a;
	private CheckBox car_yn_checkbox, car_check_east, car_check_west;
	private EditText phon_num1, phon_num2, phon_num3, car_name,
			receipt_travel_term, receipt_return_time;
	private String receipt_car_name, travel_term, return_time, check,
			car_check = "N", transcd, user_id, user_pw, mac, checksign, mac_Addr;
	ArrayAdapter<CharSequence> timespin, datespin, carspin;

	private ImageView carImg;
	public boolean isCarImgDrag = false;

	private String buncarcode;
	private Context context;
	private RectDto rec;
	private CarInfoDto carinfo;
	ResvDto resv = new ResvDto();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receipt);
		
		TAG = this.getClass().getName();
		
		this.myprefs = new Prefs(getApplicationContext());

		Bundle bundle = getIntent().getExtras();
		rec = (RectDto) bundle.getSerializable(CommonSet.RECEIPT_DATA_NAME);

		mac_Addr = myprefs.getMacAdress();

		lockScrollView = (LockScrollView) findViewById(R.id.lockScrollView);
		date = new Date();
		cal = Calendar.getInstance();

		SimpleDateFormat sdfAll = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
		strAll = sdfAll.format(date);
		reserveCall();
		prepareDirectory();

		mContent = (LinearLayout) findViewById(R.id.sign);
		mSignature = new signature(this, null);
		mSignature.setBackgroundColor(Color.WHITE);
		mContent.addView(mSignature, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		mView = mContent;
		term_text = (TextView) findViewById(R.id.term_text);
		receipt_num = (TextView) findViewById(R.id.receipt_num); 
		receipt_name = (TextView) findViewById(R.id.receipt_name); 
		receipt_date = (TextView) findViewById(R.id.receipt_date); 
		car_num = (TextView) findViewById(R.id.car_num); 
		car_name = (EditText) findViewById(R.id.car_name);
		receipt_travel_term = (EditText) findViewById(R.id.travel_term); 
		return_date = (TextView) findViewById(R.id.return_date); 
		receipt_return_time = (EditText) findViewById(R.id.return_time); 
		car_yn_checkbox = (CheckBox) findViewById(R.id.car_yn_checkbox); 
		car_check_west = (CheckBox) findViewById(R.id.car_check_west); 
		car_check_east = (CheckBox) findViewById(R.id.car_check_east); 
		phon_num1 = (EditText) findViewById(R.id.phon_num1); 
		phon_num2 = (EditText) findViewById(R.id.phon_num2); 
		phon_num3 = (EditText) findViewById(R.id.phon_num3); 

		Log.e("today date", strAll);
		phon_num1.setText("010");
		receipt_num.setText(rec.getVL_NO());
		receipt_name.setText(rec.getRECT_USR_NM());
		receipt_date.setText(strAll);
		car_num.setText(rec.getCAR_NO());
		
		car_name.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				if (event.getAction() == MotionEvent.ACTION_UP) {
					i = new Intent(Receipt.this, Car_Search.class);
					startActivityForResult(i, 1);
				}
				return false;
			}
		});

		phon_num2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (phon_num2.getText().length() == 4) {
					phon_num3.requestFocus();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		receipt_travel_term.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				receipt_travel_term.addTextChangedListener(new TextWatcher() {

					@Override
					public void afterTextChanged(Editable s) {
						Log.i("after", "1");
					}

					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						
						Log.i("before", "2");
					}

					@Override
					public void onTextChanged(CharSequence s, int start, int before,
							int count) {
						if (receipt_travel_term.getText().toString().length() > 0) {
							   SimpleDateFormat sdfDATE = new SimpleDateFormat("yyyy/MM/dd");
				               cal.setTime(date);
				               cal.add(cal.DATE, Integer.parseInt(receipt_travel_term.getText().toString())-1);
				               Log.i("ChangeTime", new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime()));
				               return_date.setText(new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime()));
				            } else if(receipt_travel_term.getText().toString().length() ==0 || receipt_travel_term.getText().toString().equals(null)){
				               cal.setTime(date);
				               return_date.setText(new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime()));
				            }
				         }
				});
				return false;
			}

		});
		
		car_yn_checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (buttonView.getId() == R.id.car_yn_checkbox) {
							if (isChecked) {
								car_check = "N";
								Bitmap ori = BitmapFactory.decodeResource(getResources(), R.drawable.carimage);
								carImg.setImageBitmap(ori);
							} else {
								car_check = "Y";
							}
						}
					}
				});

		car_check_east.setOnCheckedChangeListener(checkListener);
		car_check_west.setOnCheckedChangeListener(checkListener);

		receipt_finsh = (Button) findViewById(R.id.receipt_finsh);
		receipt_cancel = (Button) findViewById(R.id.receipt_cancel);

		receipt_finsh.setOnClickListener(this);
		receipt_cancel.setOnClickListener(this);

		carImg = (ImageView) findViewById(R.id.car_image_map);
		
		carImg.setOnTouchListener(carImageTouchLister);
	}

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter intentFilter = new IntentFilter("android.intent.action.MAIN");
		mReceiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String msg = intent.getStringExtra("bind");
				Log.i(TAG,"bind >> " + msg);
			
			}
		};
		this.registerReceiver(mReceiver, intentFilter);
		BusBuilder.getInstance().register(this);
	};

	@Override
	protected void onPause() {
		super.onPause();
		this.unregisterReceiver(this.mReceiver);
		BusBuilder.getInstance().unregister(this);
	}
	
	public void reserveCall() {
		resv.setMAC_ADRES(rec.getMAC_ADRES());
		resv.setCAR_NO(rec.getCAR_NO());
		BusBuilder.getInstance().post(new ResvCommand(RESERVE, resv));
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      switch (resultCode) {
	      case 1:
	         Bundle bun = data.getExtras();
	         car_name.setText(bun.getString("CAR_SERS_NM"));
	         rec.setCAR_SERS_CD(bun.getString("CAR_SERS_CD"));
	         rec.setCAR_SERS_NM(bun.getString("CAR_SERS_NM"));
	         break;
	      case 2:
	         Log.e("back", "back");
	         break;
	      default:
	         break;
	      }
	   };

	ImageView.OnTouchListener carImageTouchLister = new OnTouchListener() {
		
		@SuppressLint("ClickableViewAccessibility")
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN :
				isCarImgDrag = false;
				break;
			case MotionEvent.ACTION_MOVE :
				isCarImgDrag = true;
				break;
			case MotionEvent.ACTION_UP :
				
				if(!isCarImgDrag){
					
					car_check = "Y";
					car_yn_checkbox.setChecked(false);
					
					int x = (int) event.getX();
					int y = (int) event.getY();
					
					Bitmap checkIcon = BitmapFactory.decodeResource(getResources(), R.drawable.car_check_icon);
					BitmapDrawable d = (BitmapDrawable) carImg.getDrawable();
					Bitmap ori = d.getBitmap();
					
					Bitmap overlay = Util.getBitmapOverlay(carImg, ori, checkIcon, x, y);
					
					carImg.setImageBitmap(overlay);
				}
				break;
			}
			
			Log.i(TAG, "image touch : " + event.getAction() + ", isdrag : " + isCarImgDrag);
			
			return true;
		}
	};
	
	public String saveImage(Bitmap mBitmap) {
			String fileName = "";
			Log.i(TAG,"car image width : " + mBitmap.getWidth() + ", height : " + mBitmap.getHeight());
			
			fileName = rec.getVL_NO() + "_DAMG.png";
			File mypath = new File(CommonSet.SAVE_PATH + fileName);
			
			FileOutputStream fos = null;
		try {
			mypath.createNewFile();
			fos = new FileOutputStream(mypath);
			
			int rh = (mBitmap.getWidth()/mBitmap.getHeight());
			mBitmap = Util.resizeScaledImage(mBitmap, CommonSet.IMAGE_WIDTH, CommonSet.IMAGE_WIDTH * rh);
			
			mBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
			
		} catch (Exception e) {
			Log.v(TAG, e.toString());
		} finally {
            try {
            	fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
		Log.i(TAG,"damg file name >>> " + fileName);
		return fileName;
	}
	
	OnCheckedChangeListener checkListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {

			switch (buttonView.getId()) {
			case R.id.car_check_east:
				if (isChecked) {
					transcd = "01";
					car_check_east.setChecked(true);
					car_check_west.setChecked(false);
				} else {
					transcd = "02";
					car_check_east.setChecked(false);
					car_check_west.setChecked(true);
				}
				break;
			case R.id.car_check_west:
				if (isChecked) {
					transcd = "02";
					car_check_east.setChecked(false);
					car_check_west.setChecked(true);
				} else {
					transcd = "01";
					car_check_east.setChecked(true);
					car_check_west.setChecked(false);
				}
				break;
			default:
				break;
			}

		}
	};

	boolean checkEandW(int id) {
		switch (id) {
		case R.id.car_check_west:
			break;
		case R.id.car_check_east:

			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.receipt_finsh:

			if (!signedCheck) {
				checksign = "Y";
			} else {
				checksign = "N";
			}

			if (return_date.getText().toString().equals("")
					|| return_date.getText().toString().equals(null)
					|| receipt_return_time.getText().toString().equals("")
					|| receipt_return_time.getText().toString().equals(null)
					|| car_name.getText().toString().equals("")
					|| car_name.getText().toString().equals(null)
					|| checksign == "N" || transcd == "" || transcd == null
					|| phon_num2.getText().toString().equals("") ||phon_num2.getText().toString().equals(null)
					|| phon_num3.getText().toString().equals("") ||phon_num3.getText().toString().equals(null) ){
				new AlertDialog.Builder(Receipt.this)
						.setTitle(getString(R.string.alert_title_text))
						.setMessage(
								getResources().getString(R.string.receipt_need_alert))
						.setPositiveButton(getString(R.string.alert_Ok_text),
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
			} else {

				if (!signedCheck) {
					mView.setDrawingCacheEnabled(true);
					mSignature.save(mView);
					
					/*Bundle b = new Bundle();
					b.putString("status", "done");
					Intent intent = new Intent();
					intent.putExtras(b);
					setResult(RESULT_OK, intent);*/
				}
	

				String entryFull = return_date.getText().toString() + " "
						+ receipt_return_time.getText().toString() + ":00:00";

				
				if(receipt_return_time.length()==1){
					receipt_return_time.setText("0"+receipt_return_time.getText().toString());
				}
				rec.setTRVL_TERM(receipt_travel_term.getText().toString());
				rec.setENTRY_FULL_DT(entryFull);
				rec.setENTRY_DE(return_date.getText().toString().replaceAll("/", ""));
				rec.setENTRY_HM(receipt_return_time.getText().toString().trim()+"0000");
				Log.i("setENTRY_DE",rec.getENTRY_DE());
				Log.i("setENTRY_HM",rec.getENTRY_HM());
				Log.i("setENTRY_FULL_DT",entryFull);
				rec.setCAR_TRANS_CD(transcd);
				
				BitmapDrawable d = (BitmapDrawable) carImg.getDrawable();
				Bitmap carBit = d.getBitmap();
				
				int term = Integer.parseInt(rec.getTRVL_TERM());
				if(term >= 3){
					rec.setLNG_SHRT_TY_CD("2");
				}else{
					rec.setLNG_SHRT_TY_CD("1");
				}
				rec.setCAR_DAMG_FILE_NM(saveImage(carBit));
				rec.setCAR_DAMG_AT(car_check);
				
				if (rec.getCAR_TRANS_CD() == "01") {
					rec.setCAR_TRANS_NM("지하3층A32(동측)");
				} else {
					rec.setCAR_TRANS_NM("지하3층H38(서측)");
				}

				rec.setCSTMR_SIGN_AT(checksign);
				rec.setCSTMR_SIGN_FILE_NM(current);

				Log.i("all",
						rec.getMAC_ADRES() + "," + rec.getVL_NO() + ","
								+ rec.getRECT_USR_ID() + "," + rec.getRECT_DE() +"," + rec.getRECT_USR_NM()
								+ "," + rec.getRECT_HM() + ","
								+ rec.getCAR_NO() + ","
								+ rec.getCARNO_AUTO_RECG_AT() + ","
								+ rec.getCAR_SERS_CD() + ","
								+ rec.getCAR_SERS_NM() + ","
								+ rec.getTRVL_TERM() + "," + rec.getENTRY_DE()
								+ "," + rec.getENTRY_HM() + ","
								+rec.getCAR_DAMG_AT()+","
								+rec.getCAR_DAMG_FILE_NM()+","
								+ rec.getRESV_AT() + ","
								+ rec.getCAR_TRANS_CD() + ","
								+ rec.getCSTMR_SIGN_AT() + ","
								+ rec.getCSTMR_SIGN_FILE_NM() + ","
								+ rec.getLNG_SHRT_TY_CD());

				i = new Intent(Receipt.this, Car_Video.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable(CommonSet.RECEIPT_DATA_NAME, rec);

				i.putExtras(bundle);
				startActivity(i);

				finish();

			}

			// i = new Intent(this, Car_Camera.class);

			break;
		case R.id.receipt_cancel:
			new AlertDialog.Builder(this)
					.setTitle(getString(R.string.alert_receipt_cancel_title))
					.setMessage(
							getResources().getString(
									R.string.receipt_cancel_alert))
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

			break;
		default:
			break;
		}

	}

	@Override
	protected void onDestroy() {
		Log.w("GetSignature", "onDestory");
		
		super.onDestroy();
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
		File tempdir = new File(CommonSet.SAVE_PATH);
		if (!tempdir.exists())
			tempdir.mkdirs();
		
		return false;
	}

	public class signature extends View {
		private static final float STROKE_WIDTH = 5f;
		private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
		private Paint paint = new Paint();
		private Path path = new Path();
		private float lastTouchX;
		private float lastTouchY;
		private final RectF dirtyRect = new RectF();

		public signature(Context context, AttributeSet attrs) {
			super(context, attrs);
			paint.setAntiAlias(true);
			paint.setColor(Color.BLACK);
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeJoin(Paint.Join.ROUND);
			paint.setStrokeWidth(STROKE_WIDTH);
			Log.d("s", "" + path.isEmpty());
			signedCheck = path.isEmpty();
		}

		public void save(View v) {
			Log.v("log_tag1", "Width: " + v.getWidth());
			Log.v("log_tag2", "Height: " + v.getHeight());
			
			Bitmap mBitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
			
			current = rec.getVL_NO() + "_SIGN.png";
			File mypath = new File(CommonSet.SAVE_PATH + current);
			FileOutputStream fos = null;
			
			Canvas canvas = new Canvas(mBitmap);
			canvas.drawColor(Color.WHITE);
			try {
				mypath.createNewFile();
				fos = new FileOutputStream(mypath);
				
				v.draw(canvas);
				
				int imgHeight = (int) (CommonSet.IMAGE_WIDTH * 0.75);
				mBitmap = Util.resizeScaledImage(mBitmap, CommonSet.IMAGE_WIDTH, imgHeight);
				
				mBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);

			} catch (Exception e) {
				Log.v("log_tag4", e.toString());
			} finally {
	            try {
	            	fos.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}

		public void clear() {
			path.reset();
			invalidate();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawPath(path, paint);
			Log.d("s", "" + path.isEmpty());
			signedCheck = path.isEmpty();
		}

		@SuppressLint("ClickableViewAccessibility")
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			float eventX = event.getX();
			float eventY = event.getY();
			
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				lockScrollView.setScrollingEnabled(false);
				path.moveTo(eventX, eventY);
				lastTouchX = eventX;
				lastTouchY = eventY;
				return true;
			case MotionEvent.ACTION_MOVE :
				resetDirtyRect(eventX, eventY);
	            int historySize = event.getHistorySize();
	            for (int i = 0; i < historySize; i++) {
	               float historicalX = event.getHistoricalX(i);
	               float historicalY = event.getHistoricalY(i);
	               expandDirtyRect(historicalX, historicalY);
	               path.lineTo(historicalX, historicalY);
	            }
	            path.lineTo(eventX, eventY);
	            break;
			case MotionEvent.ACTION_UP:
	            lockScrollView.setScrollingEnabled(true);
	            break;

			default:
				debug("Ignored touch event: " + event.toString());
				return false;
			}

			invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
					(int) (dirtyRect.top - HALF_STROKE_WIDTH),
					(int) (dirtyRect.right + HALF_STROKE_WIDTH),
					(int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

			lastTouchX = eventX;
			lastTouchY = eventY;

			return true;
		}

		private void debug(String string) {
		}

		private void expandDirtyRect(float historicalX, float historicalY) {
			if (historicalX < dirtyRect.left) {
				dirtyRect.left = historicalX;
			} else if (historicalX > dirtyRect.right) {
				dirtyRect.right = historicalX;
			}

			if (historicalY < dirtyRect.top) {
				dirtyRect.top = historicalY;
			} else if (historicalY > dirtyRect.bottom) {
				dirtyRect.bottom = historicalY;
			}
		}

		private void resetDirtyRect(float eventX, float eventY) {
			dirtyRect.left = Math.min(lastTouchX, eventX);
			dirtyRect.right = Math.max(lastTouchX, eventX);
			dirtyRect.top = Math.min(lastTouchY, eventY);
			dirtyRect.bottom = Math.max(lastTouchY, eventY);
		}
	}

	@Subscribe
	public void onCommandResult(CommandResult cr) {
		if (cr.getResultCd() == CommandResult.CD_SUCCESS && cr.getRefId().equals(RESERVE)) {
			List<ResvDto> list = new JsonToDto<ResvDto>().setJson(cr.getJson()).parse((ResvDto) resv).getInstances();
			
			Log.i(TAG, cr.getRefId() + " : " + cr.getResultCd() + "/" + cr.getResultMsg());
			Log.i(TAG, cr.getJson().toString());
			
			if (list != null) {
				String date = list.get(0).getENTRY_SCHDL_TM();
				String subdate1 = date.substring(0, 8);
				String subdate2 = date.substring(8, 10);
				StringBuffer sb = new StringBuffer(subdate1);
				sb.insert(4, "/");
				sb.insert(7, "/");
				Log.d(TAG, "ENTRY DATA : " + sb.toString());
				
				StringBuffer sb2 = new StringBuffer(list.get(0).getRESV_DE());
				sb2.insert(4, "/");
				sb2.insert(7, "/");

				Calendar yDay = Calendar.getInstance(); //�낃뎅
				Calendar tDay = Calendar.getInstance(); //異쒓뎅
				
				String n = list.get(0).getENTRY_SCHDL_TM();
				String nyear = n.substring(0,4);
				String nmonth = n.substring(4,6);
				String nday = n.substring(6,8);
				
				Log.i("nday", nyear + "   " +nmonth +"   "+ nday);
				
				int nYear = Integer.parseInt(nyear);
				int nMonth = Integer.parseInt(nmonth)-1;
				int nDay = Integer.parseInt(nday); 
				yDay.set(nYear, nMonth, nDay);
				
				String x = list.get(0).getDPTR_SCHDL_TM();
				String xyear = x.substring(0,4);
				String xmonth = x.substring(4,6);
				String xday = x.substring(6,8);
				Log.i("nday", xyear + "   " +xmonth +"   "+ xday);
				
				int xYear = Integer.parseInt(xyear);
				int xMonth = Integer.parseInt(xmonth)-1;
				int xDay = Integer.parseInt(xday); 
				tDay.set(xYear, xMonth, xDay);
				
				long diffSec = (yDay.getTimeInMillis() - tDay.getTimeInMillis()) / 1000;       //珥�
				long diffDay = diffSec/(60 * 60 * 24) +1;                                                     //��
				
				
				String diff = String.valueOf(diffDay);
				receipt_date.setText(sb2.toString());
				car_num.setText(list.get(0).getCAR_NO());
				car_name.setText(list.get(0).getCAR_TY_NM());
				return_date.setText(sb.toString());
				receipt_return_time.setText(subdate2);
				receipt_travel_term.setText(diff);
				
				rec.setVL_NO(list.get(0).getRESV_DE());
				rec.setRESV_AT("Y");
				rec.setTRVL_TERM(diff);
				rec.setCAR_SERS_NM(list.get(0).getCAR_TY_NM());
				rec.setENTRY_DE(subdate1);
				rec.setENTRY_HM(subdate2);

			}

		} else {
			rec.setRESV_AT("N");
		}

	}

	@Override
	   public void onBackPressed() {
	      // TODO Auto-generated method stub
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

	protected void startMainService() {
		Intent intent = new Intent(getApplicationContext(),
				com.terascope.amano.incheon.batch.MainService.class);
		startService(intent);
		// startService(new Intent("com.hsit.iqsc.IQSClientService"));
	}

	protected void stopMainService() {
		stopService(new Intent(getApplicationContext(),
				com.terascope.amano.incheon.batch.MainService.class));
		// stopService(new Intent("com.hsit.iqsc.IQSClientService"));
	}

}
