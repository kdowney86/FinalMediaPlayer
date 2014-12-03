package com.example.mediaplayer;

import android.content.Context;

	public class VideoStopState implements AdvancedState {

		String myPath;
		AudioPlayerActivity myActivity;
		Context myContext;
		
		public VideoStopState(AudioPlayerActivity v, Context c, String path) {
			this.myPath = path;
			this.myActivity = v;
			this.myContext = c;
		}
		@Override
		public void playVideo() {
			myActivity.vv.start();
		}

		@Override
		public void pauseVideo() {
			myActivity.vv.pause();
		}

		@Override
		public void stopVideo() {
		}
		
	}