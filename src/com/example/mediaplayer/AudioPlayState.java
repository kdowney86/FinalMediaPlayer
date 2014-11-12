package com.example.mediaplayer;

import android.content.Context;
import android.widget.Toast;

//import com.example.mediaplayer.AudioPlayerActivity.AudioPauseState;
//import com.example.mediaplayer.AudioPlayerActivity.AudioStopState;

public class AudioPlayState implements AudioState {
	
	AudioPlayerActivity myAudioPlayerActivity;
	MediaStateAdapter myAdapter;
	Context myContext;
	String myType;
	String myPath;
	
	public AudioPlayState(AudioPlayerActivity v, Context c, String type, String path) {
		this.myAudioPlayerActivity = v;
		this.myContext = c;
		this.myType = type;
		this.myPath = path;
	}

	@Override
	public void play() {
		Toast toast = Toast.makeText(myContext, "Media is already playing", Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void pause() {
		if (myType.equals("mp3")) {
			myAudioPlayerActivity.mp.pause();
			myAudioPlayerActivity.setState(new AudioPauseState(myAudioPlayerActivity, myContext, myType, myPath));
			String pos = Integer.toString(myAudioPlayerActivity.mp.getCurrentPosition());
			MainActivity.getCaretaker().addMemento(new Memento(myPath, pos));
			MainActivity.writeCaretaker();
		} else {
			myAdapter = new MediaStateAdapter(myType, myPath, myAudioPlayerActivity);
			myAdapter.pause();
		}
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

