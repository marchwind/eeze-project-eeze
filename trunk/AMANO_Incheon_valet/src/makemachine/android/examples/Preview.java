package makemachine.android.examples;
 
//import makemachine.android.examples.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.terascope.amano.R;
import com.terascope.amano.incheon.common.CommonSet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.YuvImage;
import android.graphics.Bitmap.Config;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


@SuppressLint("Instantiatable")
class Preview extends SurfaceView implements SurfaceHolder.Callback {
	private static final int    IMAGE_WIDTH = 1600; // ���� ����
    private static final int    IMAGE_HEIGHT = 1200; // ���� ����

    private static final String TAG = "CAMERA_WATCH";
    
    EditText tvCarNo1 ;

    private Clpr myLpr = new Clpr();
    private ParkInProcess_CameraLPR mParent;

    
    SurfaceHolder mHolder;
    Camera mCamera = null;
    
    String OrgCarNumber="";
    int nSameCount=0;
    int nLprStartFlag=0;
    int nLoopCount=0;
    String CarNumber="";
    public String[] str_num = new String[30];
    public boolean isRecognition = false;
    
    int mPicWidth, mPicHeight;
  
    public void setControl(ParkInProcess_CameraLPR mContext, EditText vControl)
    {
    	//mParent = vParent;
    	mParent = mContext;
    	tvCarNo1 = vControl; //(TextView)findViewById(R.id.textView1);
    }
    
    public void stopView() {
    	mParent.setReButtonText(mParent.getResources().getString(R.string.camera_rebtn_text));
    	mParent.setReButtonEnable();
    	this.nLprStartFlag = 0;
    }
    
    public void startView() {
    	mParent.setReButtonText(mParent.getResources().getString(R.string.camera_regonizing_text));
    	this.nSameCount = 0;
    	this.nLoopCount = 0;
    	//this.str_num = null;
    	this.nLprStartFlag = 1;
    	tvCarNo1.setText("");
    }
    
    public void close()
    {
    	if( mCamera != null){
    		mCamera.stopPreview();
    		mCamera.release();
    		mCamera = null;
    	
    	}
    }
    
    @SuppressWarnings("deprecation")
	public Preview(Context context, AttributeSet attrs) { 
		super(context, attrs); 
		
	  	try{
	  		if (mCamera == null) {
		  		//mCamera = Camera.open();
	  			//mCamera.setDisplayOrientation(90);
	  		}
	  		
	        mHolder = getHolder();
	      
	        mHolder.addCallback(this);
	        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);      
	  	}catch (Exception e) {
	  		e.printStackTrace();
		}
	
    } 
  

    @Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
       
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}    
    
	public void surfaceCreated(SurfaceHolder holder) {

		try{
			
			mCamera = Camera.open();
			if( mCamera == null){
				
			}
			
			if (mCamera != null)
			{
				try {
					
					nSameCount=0;
					
					mCamera.setPreviewDisplay(holder);
					mCamera.setPreviewCallback(mPreviewCallback);
				  	Camera.Parameters parameters = mCamera.getParameters();
				  	
				  	List<Size> sizes = parameters.getSupportedPictureSizes();
				  	ArrayList<Size> mAllowCamSizes = new ArrayList<Size>();
				  	
				  	for( int i = 0; i < sizes.size(); i++ )
				  	{
				  		Size tAt = sizes.get( i );
				  		
				  		if( 640 <= tAt.width && tAt.width <= 1280 &&
				  			480 <= tAt.height && tAt.height <= 960 )
				  		{
				  			mAllowCamSizes.add( tAt );
				  			
				  		}
				  		
				  		String ErrMsg = tAt.width + ", " + tAt.height ;
				  		Log.e( "getSupportedPictureSizes", ErrMsg );
				  	}
				  	
				  	if( mAllowCamSizes.size() > 0 )
				  	{
				  		// 선택할 항목중에서 제일 마지막에 있는 걸 선택한다.
				  		Size tAt = mAllowCamSizes.get( mAllowCamSizes.size() - 1 );
				  		
				  		mPicWidth = tAt.width;
				  		mPicHeight = tAt.height;
				  	}
				  	else
				  	{
				  		mPicWidth = 640;
				  		mPicHeight = 480;				  		
				  	}
				  	
				  	//mCamSize = getOptimalPreviewSize(sizes, IMAGE_WIDTH, IMAGE_HEIGHT);
 
				  	// 90도 돌리기.
					//parameters.setRotation(90);
				  	//mCamera.setDisplayOrientation(90);
/*					
					//--------- 960/720 으로 화상크기가 자동변환 됨
			    	mPicWidth = 3264;	//  mPicHeight/mPicWidth = 0.5625
			    	mPicHeight = 1836; 
			    	
					//--------- 960/720 으로 화상크기가 자동변환 됨
			    	mPicWidth = 3264; 	//  mPicHeight/mPicWidth = 0.75
			    	mPicHeight = 2448; 
			    	
					//--------- 960/720 으로 화상크기가 자동변환 됨
			    	mPicWidth = 2048;	//  mPicHeight/mPicWidth = 0.5625
			    	mPicHeight = 1536; 

					//--------- 960/720 으로 화상크기가 자동변환 됨
			    	mPicWidth = 2048; 	//  mPicHeight/mPicWidth = 0.75
			    	mPicHeight = 1152; 

			    	//----- 설정된 변수로  동영상 화면크기 설정됨 
			    	mPicWidth = 1280; 	//  mPicHeight/mPicWidth = 0.5625
			    	mPicHeight = 720; 

			    	
			    	//----- 설정된 변수로  동영상 화면크기 설정됨 
			    	mPicWidth = 640; 	//  mPicHeight/mPicWidth = 0.75
			    	mPicHeight = 480; 
*/
					
					
					
			    	parameters.setPreviewSize(mPicWidth, mPicHeight);
			 
				  	parameters.setPictureSize(mPicWidth, mPicHeight);
			  		mCamera.setParameters(parameters);
			  		mCamera.setDisplayOrientation(90);
			  		
			  		mCamera.startPreview();
			  		
					
			  		mParent.camera_flash_prefer();
				}
				
				catch (IOException exception) {
					Log.e("Error", "exception:surfaceCreated Camera Open ");
					mCamera.release();
					mCamera = null;
					// TODO: add more exception handling logic here
				}
			}	
		
		}
		catch (Exception e) {
			
			Log.e("camera open error","error");
					
		}
    }

	 private Size getOptimalPreviewSize(List<Size> sizes, int width, int height) {

		   final double ASPECT_TOLERANCE = 0.05;

		   double targetRatio = (double) width / height;

		   if (sizes == null) {

		    return null;

		   }
		   
		return null;
	 }

    public void surfaceDestroyed(SurfaceHolder holder) {
      	if(mCamera!=null ){
    		
    		mCamera.stopPreview();
    		mCamera.release(); 
    	    mCamera = null;
    	
    	}
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
    }

	@SuppressLint("NewApi")
	Camera.PreviewCallback mPreviewCallback = new Camera.PreviewCallback() {
		
		@SuppressLint("NewApi")
		@Override
		public void onPreviewFrame(byte[] data, Camera camera) {
			//Log.e("SSS","sss");
			String output = null;   
	    	
			Camera.Parameters parameters = camera.getParameters();

			int format = parameters.getPreviewFormat();

			if (format == ImageFormat.NV21  /*|| format == ImageFormat.YUY2 || format == ImageFormat.NV16*/)
			{
				int w = parameters.getPreviewSize().width;
				int h = parameters.getPreviewSize().height;
				
				//Log.e(Integer.toString(w), Integer.toString(h));
				if( nLprStartFlag ==1)
				{
					YuvImage yuv_image = new YuvImage(data, format, w, h, null);
					byte yuv[] = yuv_image.getYuvData();
					int rgb[] = decodeYUV420SP(yuv, w, h);
					//Log.e(Integer.toString(yuv[0]), Integer.toString(yuv[1]) + ":" + Integer.toString(yuv[2]));
					Bitmap tBmp = Bitmap.createBitmap( rgb, w, h, Config.ARGB_8888 );
					Bitmap tBmpRotate = ImageUtil.GetRotatedBitmap( tBmp, 90 );
					w = tBmpRotate.getWidth();
					h = tBmpRotate.getHeight();
					
					tBmpRotate.getPixels( rgb, 0, w, 0, 0, w, h );					
					
					byte[] Limg = new byte[300*300]; 
					int[] WH = new int[8]; 
					byte[] Br = myLpr.libProc(rgb, w, h, Limg, WH);
					
			    	int w2 = WH[3]-WH[2];
			    	int h2 = WH[5]-WH[4];
			    	
			    	//Log.e(Integer.toString(w2), Integer.toString(h2));
			    	
			    	try {
						output = new String(Br, "KSC5601");
						output = output.replace("?", "").trim();
	//				    String OrgCarNumber="";
	//				    int nSameCount=0;
						if(OrgCarNumber.equalsIgnoreCase(output)) {
							boolean value1 = output.contains("No");
	//						boolean value2 = output.contains("?");
							
							if(value1!=true)
							{
								// 인식 및 부분인식된 경우
								nSameCount = nSameCount + 1;
								if(nSameCount > 2) 
								{
									// 차량번호가 3번 동일한 번호가 인식된 경우
									isRecognition = true;
									mParent.setReButtonEnable();
									nLprStartFlag =0;
									nSameCount=0;
//									mCamera.stopPreview();					
								} else {
									if(nSameCount <= 2){ 
										isRecognition = false;
										mParent.setReButtonText(mParent.getResources().getString(R.string.camera_regonizing_text)); 
									}
								}
							} else {
								// 미인식인 경우 초기화
								isRecognition = false;
								nSameCount = 0;
							}
						} else {
							// 다른 차량번호가 도출된 경우 nSameCount를 1로 초기화
							OrgCarNumber = output;
							nSameCount = 1;
							isRecognition = false;
						}
			    	
					} catch (UnsupportedEncodingException e) {
						isRecognition = false;
						e.printStackTrace();
					}
		    	
			    	if(nLoopCount > 3 || isRecognition) {
						nLprStartFlag = 0;
						mParent.setReButtonEnable();
					} else {
						nLoopCount = nLoopCount +1;
						mParent.setReButtonText(mParent.getResources().getString(R.string.camera_regonizing_text));
					}
			    	
			    	Log.e(Integer.toString(w2), output);
		    	
			    	if(output.contains("No")){
			    		output = "인식불가";
			    	}
			    	
			    	if(tvCarNo1 != null)
			    		tvCarNo1.setText(output);
		    	}
			}
			
			
			/*if (format == ImageFormat.NV21  || format == ImageFormat.YUY2 || format == ImageFormat.NV16){
				
				if( nLprStartFlag == 1){
				
					
					int w = parameters.getPreviewSize().width;
					int h = parameters.getPreviewSize().height;
					YuvImage yuv_image = new YuvImage(data, format, w, h, null);
					byte yuv[] = yuv_image.getYuvData();
				
					int rgb[] = decodeYUV420SP(yuv, w, h);
					//Log.e(Integer.toString(yuv[0]), Integer.toString(yuv[1]) + ":" + Integer.toString(yuv[2]));

					Bitmap tBmp = Bitmap.createBitmap( rgb, w, h, Config.ARGB_8888 );
					Bitmap tBmpRotate = ImageUtil.GetRotatedBitmap( tBmp, 90 );
					w = tBmpRotate.getWidth();
					h = tBmpRotate.getHeight();
					tBmpRotate.getPixels( rgb, 0, w, 0, 0, w, h );
					
					byte[] Limg = new byte[300*300]; 
					int[] WH = new int[8]; 
					byte[] Br = null;
					
					//번호인식모듈과 연계시킴.
					try{
						Br = myLpr.libProc(rgb, w, h, Limg, WH);					
				  	}catch (Exception e) {
 			            Log.i(TAG,"Log+++ libProc Error +++ >> " + output);	
					
					}
					
			    	int w2 = WH[3]-WH[2];
			    	int h2 = WH[5]-WH[4];
			    	
			    	//Log.e(Integer.toString(w2), Integer.toString(h2));
			    	
			    	try {
						output = new String(Br, "KSC5601");
						output = output.replace("?", "").trim();
						
						Log.i(TAG,"output >>> " + output);
						str_num[nLoopCount] = output;
						
						if(OrgCarNumber.equalsIgnoreCase(output)){
							// 직전인식값과 현재 인식값이 같은 경우
							
							boolean value1 = output.contains("No");

							if(!value1){
								// 인식 및 부분인식된 경우
								nSameCount = nSameCount+1;
								
								if(nSameCount > 1 || output.length() > 7){
									nLprStartFlag =0;
									mParent.setReButtonEnable();
									nLoopCount=0;
								} else {
									mParent.setReButtonText(mParent.getResources().getString(R.string.camera_regonizing_text));
								}
							} else {
								nLoopCount = nLoopCount +1;
								if(nLoopCount > 3){
									nLprStartFlag =0;
									mParent.setReButtonEnable();
								} else {
									mParent.setReButtonText(mParent.getResources().getString(R.string.camera_regonizing_text));
								}
								
							}
						} else {
							if(output.equalsIgnoreCase(null)) output = "No Detection";
							
							OrgCarNumber = output;
							nSameCount = 1;						
							
							if(nLoopCount > 3) {
								nLprStartFlag =0;
								mParent.setReButtonEnable();
							} else {
								nLoopCount = nLoopCount +1;
								mParent.setReButtonText(mParent.getResources().getString(R.string.camera_regonizing_text));
							}	
						}
						
					} catch (UnsupportedEncodingException e) {
					
						nLprStartFlag =0;
 						nSameCount=0;
 						mParent.setReButtonEnable();
 						nLoopCount=0;
					
						e.printStackTrace();
					}
			    	
			    	Log.e(Integer.toString(w2), output);
			    	
			    	if(nSameCount >= 2 || nLprStartFlag == 0){
				    	//if(output != null || !output.contains("No")){
				    		if ( nSameCount >= 2){
				    			tvCarNo1.setText(output);
				    		} else {
				    			output = setFinalCarData(nLoopCount);
			    				tvCarNo1.setText(output);
				    		}
				    	//}
			    	}
			    	
				}
			}*/


		}
	};
	
	public  String setFinalCarData(int nCount  ){
		String retString="";

		for (int i=nCount-1 ; i>0 ; i--)
		{
			if(str_num[i].equalsIgnoreCase(null))
				retString = "No Detection";
			else
				retString = str_num[i];
			
//			if(retString.equalsIgnoreCase(null)) continue;
			boolean value1 = retString.contains("No");
			if(value1!=true) break;
		}
		if(retString.equalsIgnoreCase(null)) retString="No Detection";
//		retString = sb.toString();
		if(retString.contains("No")){
			retString = "인식불가";
			isRecognition = false;
		} else {
			isRecognition = true;
		}
		
		return retString;
	}	
	
	public static int[] decodeYUV420SP(byte[] yuv420sp, int width, int height) {
		final int frameSize = width * height;
		int[] rgb = new int[frameSize];


		for (int j = 0, yp = 0; j < height; j++) { 
			int uvp = frameSize + (j >> 1) * width, u = 0, v = 0; 
			for (int i = 0; i < width; i++, yp++) { 

				int y = (0xff & ((int) yuv420sp[yp])) - 16; 
				if (y < 0) y = 0; 
				if ((i & 1) == 0) { 
					v = (0xff & yuv420sp[uvp++]) - 128; 
					u = (0xff & yuv420sp[uvp++]) - 128; 
				} 

				int y1192 = 1192 * y; 
				int r = (y1192 + 1634 * v); 
				int g = (y1192 - 833 * v - 400 * u); 
				int b = (y1192 + 2066 * u); 

				if (r < 0) r = 0; else if (r > 262143) r = 262143; 
				if (g < 0) g = 0; else if (g > 262143) g = 262143; 
				if (b < 0) b = 0; else if (b > 262143) b = 262143; 

				rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff); 
			}
		} 
		return rgb;
	}
}
 
