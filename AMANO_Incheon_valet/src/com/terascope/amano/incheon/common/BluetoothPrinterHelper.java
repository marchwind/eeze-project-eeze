package com.terascope.amano.incheon.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import com.sewoo.jpos.command.CPCLConst;
import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.command.ESCPOSConst;
import com.sewoo.jpos.printer.ESCPOSPrinter;
import com.sewoo.jpos.printer.LKPrint;
import com.sewoo.port.android.BluetoothPort;
import com.sewoo.request.android.RequestHandler;
import com.terascope.amano.R;
import com.terascope.amano.incheon.Car_Video;
import com.terascope.amano.incheon.dto.RectDto;
import com.terascope.amano.incheon.dto.ResultDto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class BluetoothPrinterHelper {
	
	public Activity ac;
	public String TAG = "AMANO_BLUETOOTH";

	public ProgressDialog progress ;
	
	private ESCPOSPrinter posPtr = new ESCPOSPrinter("EUC-KR");
	private final char ESC = ESCPOS.ESC;
	private final char LF = ESCPOS.LF;
	
	private BluetoothPort bp;
	private BluetoothAdapter mBluetoothAdapter;
	private static final int REQUEST_ENABLE_BT = 22;
	private Thread hThread;
	public boolean bluetoothConnected = false;
	public RectDto rec;
	public String printUser = "";
	
	private Util util;
	Prefs myprefs = null;
	
	public BluetoothPrinterHelper(Activity actmp){
		this.ac = actmp;
		myprefs = new Prefs(actmp.getApplicationContext());
		util = new Util();
	}

	public void bluetoothIsConnected(){
		bluetoothConnect();
	}
	
	private void bluetoothConnect() {
		progress = ProgressDialog.show(ac, "프린트", "프린트 연결 확인 중입니다...");
		try {
			bp = BluetoothPort.getInstance();
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
 
            if (mBluetoothAdapter == null) {
               
            }
 
            if (!mBluetoothAdapter.isEnabled()) {
            	
                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                ac.startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);
                mBluetoothAdapter.enable();
            }
 
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
            	boolean is = false;
                for (BluetoothDevice device : pairedDevices) {
                    Log.i(TAG,"bluetooth devices address >> " + device.getAddress());
                	Log.i(TAG,"bluetooth saved address >> " + myprefs.getBluetoothID());
                    
                	if (device.getAddress().equals(myprefs.getBluetoothID())) {
                    	btConn(device);
                    	is = true;
                    	break;
                    }
                }
                
                if(!is){
                	progress.dismiss();
                	Toast.makeText(ac, "연결된 프린트가 주위에 없습니다.", Toast.LENGTH_LONG).show();
                }
                
            } else {
            	Toast.makeText(ac, "블루투스 기기가 없습니다.", Toast.LENGTH_LONG).show();
            	progress.dismiss();
            }
           
        } catch (NullPointerException e) {
        	progress.dismiss();
        	Toast.makeText(ac, "프린터 연결도중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (Exception e) {
        	progress.dismiss();
        	Toast.makeText(ac, "프린터 연결도중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
		
	}
	
	// Bluetooth Connection method.
	private void btConn(final BluetoothDevice btDev) throws IOException {
		new connTask().execute(btDev);
	}

	public class connTask extends AsyncTask<BluetoothDevice, Void, Integer> {
		
		@Override
		protected void onPreExecute() {
			// bp = BluetoothPort.getInstance();
			super.onPreExecute();
		}

		@SuppressLint("UseValueOf")
		@Override
		protected Integer doInBackground(BluetoothDevice... params) {
			Integer retVal = null;
			try {
				bp.connect(params[0]);
				retVal = new Integer(0);
			} catch (IOException e) {
				retVal = new Integer(-1);
			}
			return retVal;
		}

		@SuppressLint("ShowToast")
		@SuppressWarnings("static-access")
		@Override
		protected void onPostExecute(Integer result) {
			progress.dismiss();
			if (result.intValue() == 0) {
				RequestHandler rh = new RequestHandler();
				hThread = new Thread(rh);
				hThread.start();
				bluetoothConnected = true;
				
				Toast.makeText(ac, "프린트가 연결 되었습니다. 프린트를 사용하실 수 있습니다.", Toast.LENGTH_SHORT).show();
				
			} else {
				
				new AlertDialog.Builder(ac)
				.setTitle("프린터 오류")
				.setMessage("프린트 연결에 실패하였습니다.\n다시 연결하시겠습니까?")
				.setNegativeButton(ac.getString(R.string.alert_Cancel_text), 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								myprefs.setBluetoothID("");
								myprefs.save();
								Toast.makeText(ac, "프린터 설정에서 재연결 해주시기 바랍니다.", Toast.LENGTH_LONG).show();
							}
						})
				.setPositiveButton(ac.getString(R.string.alert_Ok_text),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								bluetoothConnect();
							}
						}).show();
			}
			
			super.onPostExecute(result);
		}
	}
	
	public ResultDto statusCheck(){
		ResultDto result = new ResultDto();
		Log.i(TAG, "bluetooth print check >>> " + posPtr.printerCheck() );
		Log.i(TAG, "bluetooth print status >>> " + posPtr.status() );
	
		posPtr.printerCheck();
    	if(posPtr.status() != 0){
    		result.setRTN_ST(posPtr.status());
    		result.setRTN_CD(CommonSet.BLUETHOOTH_ERROR_CODE);
			result.setRTN_MSG("프린터 연결안됨.");
    	} else {
    		int sts = posPtr.status();
			if(sts == CPCLConst.LK_STS_CPCL_NORMAL) {
				result.setRTN_CD(CommonSet.RESULT_SUCCESS_CODE);
				result.setRTN_MSG("프린터 연결됨");
			} else {
				if((sts & CPCLConst.LK_STS_CPCL_BUSY) > 0){
					result.setRTN_CD(CommonSet.BLUETHOOTH_ERROR_CODE);
					result.setRTN_MSG("프린터 출력중");
				} else if((sts & CPCLConst.LK_STS_CPCL_PAPER_EMPTY) > 0){
					result.setRTN_CD(CommonSet.BLUETHOOTH_ERROR_CODE);
					result.setRTN_MSG("프린터 용지부족");
				} else if((sts & CPCLConst.LK_STS_CPCL_COVER_OPEN) > 0){
					result.setRTN_CD(CommonSet.BLUETHOOTH_ERROR_CODE);
					result.setRTN_MSG("프린터 덮개 열려 있음");
				} else if((sts & CPCLConst.LK_STS_CPCL_BATTERY_LOW) > 0){
					result.setRTN_CD(CommonSet.BLUETHOOTH_ERROR_CODE);
					result.setRTN_MSG("프린터 전원부족");
				}
			}
			result.setRTN_ST(sts);
		} 
		return result;
	};
	
	// Bluetooth Disconnection method.
	public void btDisconn()
	{
		try {
			bp.disconnect();
		} catch (Exception e) {
			Log.w(TAG, e.getMessage(), e);
		}
	}
	
	public ResultDto valetParkPrint(RectDto r, String user) {
		this.rec = r;
		this.printUser = user;
		
		ResultDto res = null;
		
		try {
			if(printUser.equals(CommonSet.PRINT_CUSTOMER)){
				res = ParkPrintCustom();
			} else if(printUser.equals(CommonSet.PRINT_OFFER)){
				res = ParkPrintMember();
			} 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res = new ResultDto();
			res.setRTN_CD(CommonSet.BLUETHOOTH_ERROR_CODE);
			res.setRTN_MSG("프린터 오류");
			res.setRTN_ST(-1);
		}
		
		return res;
		//bluetoothConnect();
	}
	
	/**
	 * Print
	 * @throws UnsupportedEncodingException
	 */
	public ResultDto ParkPrintCustom() throws UnsupportedEncodingException
    {
		ResultDto res = statusCheck();
		 
		Log.i(TAG, "bluetooth print check >>> " + res.getRTN_MSG() );
		if(res.getRTN_CD().equals(CommonSet.RESULT_SUCCESS_CODE)){
			posPtr.printNormal(ESC + "|cA" + ESC + "|bC" + ESC + "|3C" + "차량보관증(고객용)" + LF + LF);
			posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"접수번호 : " + rec.getVL_NO() + LF + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"접수일시 : " + rec.getRECT_FULL_DT() + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"접 수 자  : " + rec.getRECT_USR_NM() + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"차량번호 : " + rec.getCAR_NO() + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"차     종 : " + rec.getCAR_SERS_NM() + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"귀국일시 : " + rec.getENTRY_FULL_DT() + LF);   
	        
	        String checkString = "없음";
	        if(rec.getCAR_DAMG_AT().equals("Y")){
	        	checkString = "있음";
	        }
	        
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"흠집유무 : " + checkString + LF);
	        try {
				posPtr.printBitmap(CommonSet.SAVE_PATH + rec.getCAR_DAMG_FILE_NM(), LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_BITMAP_NORMAL);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        posPtr.setCharSet("Big5");
	        posPtr.printBarCode(rec.getVL_NO(), LKPrint.LK_BCS_Code93, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
	        posPtr.setCharSet("EUC-KR");
	        
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"고객서명 : " + LF);
	        try {
				posPtr.printBitmap(CommonSet.SAVE_PATH + rec.getCSTMR_SIGN_FILE_NM(),  LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_BITMAP_NORMAL);//rec.filepath삭제
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"차량찾는곳: " + LF);
	        posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" + ESC + "|3C" + rec.getCAR_TRANS_NM() +LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"※차량보관장소 : 장기옥외주차장 " + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"※고지사항 : 뒷면 약관내용 고객 필독 사항임. " + LF);
	    	posPtr.lineFeed(3);
		} else {
			Toast.makeText(ac, res.getRTN_MSG(), Toast.LENGTH_LONG).show();
		}
		return res;

    }
	
	/**
	 * Print
	 * @throws UnsupportedEncodingException
	 */
	public ResultDto ParkPrintMember() throws UnsupportedEncodingException
    {
		
		ResultDto res = statusCheck();
		
		if(res.getRTN_CD().equals(CommonSet.RESULT_SUCCESS_CODE)){
			
			boolean shortTerm = false;
			if(Integer.parseInt(rec.getTRVL_TERM()) < 2) {
				shortTerm = true;
			}
			
			posPtr.printNormal(ESC + "|cA" + ESC + "|bC" + ESC + "|3C" + "차량보관증(보관용)" + LF + LF);
			posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"접수번호 : " + rec.getVL_NO() +LF);
			
			if(shortTerm) {
				posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" +LF);
				//posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"==============================" +LF);
				
			} else {
				posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" + LF);
			}
			
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"접수일시 : " + rec.getRECT_FULL_DT() +LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"접 수 자  : " + rec.getRECT_USR_NM() +LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"차량번호 : " + rec.getCAR_NO() +LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"차     종 : " + rec.getCAR_SERS_NM() +LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"귀국일시 : " + rec.getENTRY_FULL_DT() +LF);      
	        
	        if(shortTerm) {
	        	posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" +LF);
				//posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"==============================" +LF);
			} else {
				posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" + LF);
			}
	        
	        String checkString = "없음";
	        if(rec.getCAR_DAMG_AT().equals("Y")){
	        	checkString = "있음";
	        }
	        
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"흠집유무 : " + checkString + LF);
	        try {
				posPtr.printBitmap(CommonSet.SAVE_PATH + rec.getCAR_DAMG_FILE_NM(), LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_BITMAP_NORMAL);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	        
	        if(shortTerm) {
	        	posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" +LF);
				//posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"==============================" +LF);
			} else {
				posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" + LF);
			}
	        posPtr.setCharSet("Big5");
	        posPtr.printBarCode(rec.getVL_NO(), LKPrint.LK_BCS_Code93, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
	        posPtr.setCharSet("EUC-KR");
	        
	        if(shortTerm) {
	        	posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" +LF);
				//posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"==============================" +LF);
			} else {
				posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" + LF);
			}
	        
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"장기주차요원 : " + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"장기주차위치 : " + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"차량찾는곳 : " + LF);
	        posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" + ESC + "|3C" + rec.getCAR_TRANS_NM() +LF);
	        
	        if(shortTerm) {
	        	posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" +LF);
				//posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" +"==============================" +LF);
			} else {
				posPtr.printNormal(ESC + "|cA"+ ESC + "|bC" + LF);
			}
	        
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"단기주차요원: " + LF);
	        posPtr.printNormal(ESC + "|lA"+ ESC + "|bC"  +"단기주차위치: " + LF);
	    	posPtr.lineFeed(3);
		} else {
			Toast.makeText(ac, res.getRTN_MSG(), Toast.LENGTH_LONG).show();
		}

		return res;
		/*
		try {
			bp.disconnect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
    }
	
}
