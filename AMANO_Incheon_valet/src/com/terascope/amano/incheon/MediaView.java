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
	
	private int mVideoWidth;
	private int mVideoHeight;
	private MediaPlayer mMediaPlayer;
	private VideoView mPreview;
	private SurfaceHolder holder;
	//private String path;
	private Bundle extras;
	private boolean mIsVideoSizeKnown = false;
	private boolean mIsVideoReadyToBePlayed = false;
	  
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
	    
	    MediaController mMediaController = new MediaController(this){
	    	@Override
	    	public void hide() {}
	    	
	    	@Override
	    	public boolean dispatchKeyEvent(KeyEvent event) {
	    		if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
	    			
	    			try{
		    			//mPreview.pause();
	    				//mPreview.stopPlayback();
		    			mPreview = null;
		    			finish();
	    			} catch (Exception e) {
	    				e.getStackTrace();
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
				// TODO Auto-generated method stub
				return true;
			}
		});
	    
	    //holder.addCallback(this);
	    //holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	    
	}

//	private void playVideo(String path) {
//	    doCleanUp();
//	    
//	    Log.i(TAG, "play path : "+ path);
//	    
//	    try {
//	      mMediaPlayer = new MediaPlayer();
//	      mMediaPlayer.setDataSource(path);
//	      mMediaPlayer.setDisplay(holder);
//	      mMediaPlayer.prepare();
//	      mMediaPlayer.setOnBufferingUpdateListener(this);
//	      mMediaPlayer.setOnCompletionListener(this);
//	      mMediaPlayer.setOnPreparedListener(this);
//	      mMediaPlayer.setOnVideoSizeChangedListener(this);
//	      mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//
//	    } catch (Exception e) {
//	      Log.e(TAG, "error: " + e.getMessage(), e);
//	    }
//	}
	
//	@Override
//	public void onBufferingUpdate(MediaPlayer mp, int percent) {
//		 Log.i(TAG, "onBufferingUpdate percent:" + percent);
//		
//	}
//	
	@Override
	public void onCompletion(MediaPlayer mp) {
		Log.i(TAG, "onCompletion called");
		
	}
	
//	@Override
//	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//		Log.i(TAG, "onVideoSizeChanged called");
//	    if (width == 0 || height == 0) {
//	      Log.e(TAG, "invalid video width(" + width + ") or height(" + height + ")");
//	      return;
//	    }
//	    mIsVideoSizeKnown = true;
//	    mVideoWidth = width;
//	    mVideoHeight = height;
//	    if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
//	      startVideoPlayback();
//	    }
//		
//	}
	
//	private void startVideoPlayback() {
//	    Log.i(TAG, "startVideoPlayback");
//	    holder.setFixedSize(mVideoWidth, mVideoHeight);
//	    mMediaPlayer.start();
//	}
//	
//	@Override
//	public void onPrepared(MediaPlayer mp) {
//		Log.i(TAG, "onPrepared called");
//	    mIsVideoReadyToBePlayed = true;
//	    if (mIsVideoReadyToBePlayed && mIsVideoSizeKnown) {
//	      startVideoPlayback();
//	    }
//		
//	}
//	
//	@Override
//	public void surfaceCreated(SurfaceHolder holder) {
//		playVideo(extras.getString(CommonSet.MEDIA_PATH_NAME));
//	}
//	
//	@Override
//	public void surfaceChanged(SurfaceHolder holder, int format, int width,
//			int height) {
//		Log.i(TAG, "surfaceChanged called");
//		
//	}
//	
//	@Override
//	public void surfaceDestroyed(SurfaceHolder holder) {
//		Log.i(TAG, "surfaceDestroyed called");
//		
//	}
//	
//	private void doCleanUp() {
//	    mVideoWidth = 0;
//	    mVideoHeight = 0;
//	    mIsVideoReadyToBePlayed = false;
//	    mIsVideoSizeKnown = false;
//	}
	
	@Override
  	protected void onPause() {
		super.onPause();
		//releaseMediaPlayer();
		//doCleanUp();
  	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//releaseMediaPlayer();
		//doCleanUp();
	}

//	private void releaseMediaPlayer() {
//		if (mMediaPlayer != null) {
//			mMediaPlayer.release();
//			mMediaPlayer = null;
//		}
//	}
}
