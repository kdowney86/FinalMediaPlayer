package com.example.mediaplayer;

import android.content.Context;

public class VideoPlayState implements AdvancedState {

	String myPath;
	AudioPlayerActivity myActivity;
	Context myContext;
	
	public VideoPlayState(AudioPlayerActivity v, Context c, String path) {
		this.myPath = path;
		this.myActivity = v;
		this.myContext = c;
	}
	@Override
	public void playVideo() {
		
	}

	@Override
	public void pauseVideo() {
		myActivity.vv.pause();
	}

	@Override
	public void stopVideo() {
		myActivity.vv.seekTo(0);
		myActivity.vv.pause();
	}
	
}
