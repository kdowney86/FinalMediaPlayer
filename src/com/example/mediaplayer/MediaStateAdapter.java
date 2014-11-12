package com.example.mediaplayer;

public class MediaStateAdapter implements AudioState {
	
	AdvancedState currentAdvancedState;
	String myType;
	String myPath;
	AudioPlayerActivity a;
	
	public MediaStateAdapter (String type, String path, AudioPlayerActivity a) {
		this.myType = type;
		this.myPath = path;
		this.a = a;
	}

	@Override
	public void play() {
		if (myType.equals("mp4")) {
			currentAdvancedState = new VideoPlayState(a, a.getApplicationContext(), myPath);
		}
	}

	@Override
	public void pause() {
		if (myType.equals("mp4")) {
			currentAdvancedState = new VideoPauseState(a, a.getApplicationContext(), myPath);
		}
	}

	@Override
	public void stop() {
		if (myType.equals("mp4")) {
			currentAdvancedState = new VideoStopState(a, a.getApplicationContext(), myPath);
		}
	}

}
