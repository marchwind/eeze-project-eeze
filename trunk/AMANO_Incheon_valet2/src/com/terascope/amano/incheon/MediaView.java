package com.terascope.amano.incheon;


import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.widget.MediaController;
import android.widget.VideoView;

import com.terascope.amano.R;
import com.terascope.amano.incheon.common.CommonSet;

public class MediaView extends Activity implements OnCompletionListener{
	
	private static String TAG = "";
	
	private VideoView mPreview;
	private SurfaceHolder holder;
	//private String path;
	private Bundle extras;
	private MediaController mMediaController;
	  
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media);
		
		TAG = this.getClass().getName();
		extras = getIntent().getExtras();
		
		mPreview = (VideoView) findViewById(R.id.videoView);
	    holder = mPreview.getHolder();
	    
	    mPreview.setOnCompletionListener(this);
	    mPreview.setVideoPath(extras.getString(CommonSet.MEDIA_PATH_NAME));
	    
	    mMediaController = new MediaController(this){
	    	@Override
	    	public void hide() {}
	    	
	    	@Override
	    	public boolean dispatchKeyEvent(KeyEvent event) {
	    		mMediaController.hide();
	    		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
	    			
	    			try{
		    			mPreview = null;
		    			finish();
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    		}
	    		return true;
	    	}
	    };
	    mMediaController.setAnchorView(mPreview);
	    mMediaController.setMediaPlayer(mPreview);
	    
	    mPreview.setMediaController(mMediaController);
	    
	    mPreview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				mPreview.start();				
			}
		});
	    
	    mPreview.setOnErrorListener(new OnErrorListener() {
			
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return true;
			}
		});
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.i(TAG, "onCompletion called");
		
	}
	
	@Override
  	protected void onPause() {
		super.onPause();
  	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
