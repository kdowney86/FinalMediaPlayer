package com.example.mediaplayer;

import android.content.Context;

	public class VideoPauseState implements AdvancedState {

		String myPath;
		AudioPlayerActivity myActivity;
		Context myContext;
		
		public VideoPauseState(AudioPlayerActivity v, Context c, String path) {
			this.myPath = path;
		}
		@Override
		public void playVideo() {
			myActivity.vv.start();
		}

		@Override
		public void pauseVideo() {
			
		}

		@Override
		public void stopVideo() {
			myActivity.vv.seekTo(0);
			myActivity.vv.pause();
		}
		
	}
