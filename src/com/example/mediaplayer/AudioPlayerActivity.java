package com.example.mediaplayer;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class AudioPlayerActivity extends ActionBarActivity {

	protected AudioState currentState;
	protected MediaPlayer mp;
	protected VideoView vv;
	private Button audioPlayButton;
	private Button audioPauseButton;
	private Button audioStopButton;
	protected String audioPath;
	protected String type;
	private int time = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio_player);

		Intent myIntent = getIntent();
		audioPath = myIntent.getStringExtra("path");
		type = myIntent.getStringExtra("type");

		audioPlayButton = (Button)findViewById(R.id.playButtonAudio);
		audioPauseButton = (Button)findViewById(R.id.pauseButtonAudio);
		audioStopButton = (Button)findViewById(R.id.stopButtonAudio);

		currentState = new AudioPlayState(this, this.getApplicationContext(), type, audioPath);
		mp = new MediaPlayer();
		vv = (VideoView) findViewById(R.id.video_view);
		//vv.setVisibility(0);
		
		if (type.equals("mp3")) {
			try {
				mp.setDataSource(audioPath);
				mp.prepare();
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
		} else {
			vv.setVideoPath("audioPath");
		}
		restoreFromMemento();
		mp.seekTo(time);
		mp.start();
		
		//currentState = new AudioPlayState(this, this.getApplicationContext(), type, audioPath);

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
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, audioPath, Toast.LENGTH_SHORT);
		toast.show();
	}
	@Override
	public void onBackPressed() {
		//this.currentState.pause();
		super.onBackPressed();
	}
	
	@Override
	public void onStop() {
		//this.currentState.pause();
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

	public void setState(AudioState s) {
		this.currentState = s;
	}

	public void exit(View v) {
		this.onDestroy();
	}
}
