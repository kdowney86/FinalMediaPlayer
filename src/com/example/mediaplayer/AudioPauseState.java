package com.example.mediaplayer;

import android.content.Context;
import android.widget.Toast;

//import com.example.mediaplayer.AudioPlayerActivity.AudioPlayState;
//import com.example.mediaplayer.AudioPlayerActivity.AudioStopState;

public class AudioPauseState implements AudioState {
	
	AudioPlayerActivity myAudioPlayerActivity;
	Context myContext;
	String myType;
	String myPath;
	MediaStateAdapter myAdapter;
	
	public AudioPauseState(AudioPlayerActivity v, Context c, String type, String p) {
		this.myAudioPlayerActivity = v;
		this.myContext = c;
		this.myType = type;
		this.myPath = p;
	}

	@Override
	public void play() {
		if (myType.equals("mp3")) {
			myAudioPlayerActivity.mp.start();
			myAudioPlayerActivity.setState(new AudioPlayState(myAudioPlayerActivity, myContext, myType, myPath));
		} else {
			myAdapter = new MediaStateAdapter(myType, myPath, myAudioPlayerActivity);
			myAdapter.play();
		}
	}

	@Override
	public void pause() {
		Toast toast = Toast.makeText(myContext, "Media is already paused", Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void stop() {
		if (myType.equals("mp3")) {
			myAudioPlayerActivity.mp.seekTo(0);
			myAudioPlayerActivity.mp.pause();
			myAudioPlayerActivity.setState(new AudioStopState(myAudioPlayerActivity, myContext, myType, myPath));
		} else {
			myAdapter = new MediaStateAdapter(myType, myPath, myAudioPlayerActivity);
			myAdapter.stop();
		}
	}
}