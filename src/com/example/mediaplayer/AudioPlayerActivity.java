package com.example.mediaplayer;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AudioPlayerActivity extends ActionBarActivity {

	private State currentState;
	private MediaPlayer mp;
	private Button audioPlayButton;
	private Button audioPauseButton;
	private Button audioStopButton;
	private String audioPath;
	private SeekBar seekbar;
	private Handler seekHandler = new Handler(); /** Called when the activity is first created. */ 
	private int time = 0;
	private TextView textview;
	private TextView timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio_player);

		Intent myIntent = getIntent();
		audioPath = myIntent.getStringExtra("path");

		audioPlayButton = (Button)findViewById(R.id.playButtonAudio);
		audioPauseButton = (Button)findViewById(R.id.pauseButtonAudio);
		audioStopButton = (Button)findViewById(R.id.stopButtonAudio);
		textview = (TextView)findViewById(R.id.textView1);
		seekbar = (SeekBar)findViewById(R.id.musicSeekBar);
		seekHandler = new Handler();
		timer = (TextView)findViewById(R.id.seekBarUpdate);

		currentState = new AudioPlayState(this);
		mp = new MediaPlayer();
		try {
			mp.setDataSource(audioPath);
			mp.prepare();
			seekbar.setMax(mp.getDuration());
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		restoreFromMemento();

		mp.seekTo(time);
		mp.start();
		textview.setText(audioPath.substring(17));
		seekUpdation();

		audioPlayButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AudioPlayerActivity.this.currentState.play();
			}

		});

		audioPauseButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AudioPlayerActivity.this.currentState.pause();
			}

		});

		audioStopButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AudioPlayerActivity.this.currentState.stop();
			}

		});

		try {
			mp.setDataSource(audioPath);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Runnable run = new Runnable() { 
		@Override 
		public void run() { 
			seekUpdation(); 
		} 
	}; 

	//sets the textView Timer and updates seek bar
	public void seekUpdation() { 
		int i = mp.getCurrentPosition();
		seekbar.setProgress(i);
		if(i%60000/1000 < 10) timer.setText(i/60000 + ":0" + (i%60000)/1000);
		else timer.setText(i/60000 + ":" + (i%60000)/1000);
		seekHandler.postDelayed(run, 1000); 
	} 

    @Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Memento saveToMemento() {
		return new Memento(Integer.toString(this.time), this.audioPath);
	}

	public void restoreFromMemento() {
		for (int u = 0; u < MainActivity.getCaretaker().myMementos.size(); u++) {
			Memento memento = MainActivity.getCaretaker().myMementos.get(u);
			if (memento.getPath().equals(audioPath)) {
				setTime(Integer.parseInt(memento.getState()));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.audio_player, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void setState(State s) {
		this.currentState = s;
	}

	public class AudioPlayState implements State {

		AudioPlayerActivity myAudioPlayerActivity;

		public AudioPlayState(AudioPlayerActivity v) {
			this.myAudioPlayerActivity = v;
		}

		@Override
		public void play() {
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "audio is already playing", Toast.LENGTH_SHORT);
			toast.show();
		}

		@Override
		public void pause() {
			myAudioPlayerActivity.setState(new AudioPauseState(myAudioPlayerActivity));
			myAudioPlayerActivity.mp.pause();
			String pos = Integer.toString(myAudioPlayerActivity.mp.getCurrentPosition());
			MainActivity.getCaretaker().addMemento(new Memento(audioPath, pos));
			MainActivity.writeCaretaker();
		}

		@Override
		public void stop() {
			myAudioPlayerActivity.setState(new AudioStopState(myAudioPlayerActivity));
			myAudioPlayerActivity.mp.seekTo(0);
			myAudioPlayerActivity.mp.pause();
		}
	}

	public class AudioPauseState implements State {

		AudioPlayerActivity myAudioPlayerActivity;

		public AudioPauseState(AudioPlayerActivity v) {
			this.myAudioPlayerActivity = v;
		}

		@Override
		public void play() {
			myAudioPlayerActivity.setState(new AudioPlayState(myAudioPlayerActivity));
			myAudioPlayerActivity.mp.start();
		}

		@Override
		public void pause() {
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "audio is already paused", Toast.LENGTH_SHORT);
			toast.show();
		}

		@Override
		public void stop() {
			myAudioPlayerActivity.setState(new AudioStopState(myAudioPlayerActivity));
			myAudioPlayerActivity.mp.seekTo(0);
			myAudioPlayerActivity.mp.pause();
		}
	}

	public class AudioStopState implements State {

		AudioPlayerActivity myAudioPlayerActivity;

		public AudioStopState(AudioPlayerActivity v) {
			this.myAudioPlayerActivity = v;
		}

		@Override
		public void play() {
			myAudioPlayerActivity.setState(new AudioPlayState(myAudioPlayerActivity));
			myAudioPlayerActivity.mp.start();
		}

		@Override
		public void pause() {
			myAudioPlayerActivity.setState(new AudioPauseState(myAudioPlayerActivity));
			myAudioPlayerActivity.mp.pause();
			String pos = Integer.toString(myAudioPlayerActivity.mp.getCurrentPosition());
			MainActivity.getCaretaker().addMemento(new Memento(audioPath, pos));
			MainActivity.writeCaretaker();
		}

		@Override
		public void stop() {
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, "audio is already stopped", Toast.LENGTH_SHORT);
			toast.show();
		}		
	}
}
