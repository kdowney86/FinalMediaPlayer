package com.example.mediaplayer;

import android.content.Context;
import android.widget.Toast;

import com.example.mediaplayer.AudioPlayerActivity;

public class AudioStopState implements AudioState {
	
	AudioPlayerActivity myAudioPlayerActivity;
	MediaStateAdapter myAdapter;
	Context myContext;
	String myPath;
	String myType;
	
	public AudioStopState(AudioPlayerActivity v, Context c, String path, String type) {
		this.myAudioPlayerActivity = v;
		this.myContext = c;
		this.myPath = path;
		this.myType = type;
	}

	@Override
	public void play() {
		if (myType.equals("mp3")) {
			myAudioPlayerActivity.currentState.play();
			myAudioPlayerActivity.setState(new AudioPlayState(myAudioPlayerActivity, myContext, myType, myPath));
		} else {
			myAdapter = new MediaStateAdapter(myType, myPath, myAudioPlayerActivity);
			myAdapter.stop();
		}
	}

	@Override
	public void pause() {
		if (myType.equals("mp3")) {
			myAudioPlayerActivity.mp.pause();
			myAudioPlayerActivity.setState(new AudioStopState(myAudioPlayerActivity, myContext, myType, myPath));
		} else {
			myAdapter = new MediaStateAdapter(myType, myPath, myAudioPlayerActivity);
			myAdapter.stop();
		}
	}

	@Override
	public void stop() {
		Toast toast = Toast.makeText(myContext, "Media is already stopped", Toast.LENGTH_SHORT);
		toast.show();
	}		
}