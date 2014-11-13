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
import android.widget.VideoView;

public class AudioPlayerActivity extends ActionBarActivity {

	protected AudioState currentState;
	protected MediaPlayer mp;
	protected VideoView vv;
	private Button audioPlayButton;
	private Button audioPauseButton;
	private Button audioStopButton;
	private String audioPath;
	private SeekBar seekbar;
	private Handler seekHandler = new Handler(); /** Called when the activity is first created. */ 
	protected String type;
	private int time = 0, counter = 0;
	private TextView textview;
	private TextView timer;

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
		textview = (TextView)findViewById(R.id.textView1);
		seekbar = (SeekBar)findViewById(R.id.musicSeekBar);
		seekHandler = new Handler();
		timer = (TextView)findViewById(R.id.seekBarUpdate);
		counter = 0;

		currentState = new AudioPlayState(this, this.getApplicationContext(), type, audioPath);
		mp = new MediaPlayer();
		vv = (VideoView) findViewById(R.id.video_view);
		
		restoreFromMemento();

		if (type.equals("mp3")) {
			try {
				mp.setDataSource(audioPath);
				mp.prepare();
				seekbar.setMax(mp.getDuration());
				mp.seekTo(time);
				mp.start();
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
			vv.setVideoPath(audioPath);
			seekbar.setMax(vv.getDuration());
			vv.start();
		}

		textview.setText(audioPath.substring(17));
		seekUpdation(type);

		/*mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

		    @Override
		    public void onCompletion(MediaPlayer mp) {
		        counter++;
		        if (counter < files.size()) {
		            mediaPlayer.setDataSource(files.get(counter));
		            mediaPlayer.prepare();
		            mediaPlayer.start();
		        }
		    }    
		});*/

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
	}

	Runnable run = new Runnable() { 
		@Override 
		public void run() { 
			seekUpdation(type); 
		} 
	};

	int i;
	//sets the textView Timer and updates seek bar
	public void seekUpdation(String type) {
		if(type.equals("mp3")){i = mp.getCurrentPosition();}
		else{i = vv.getCurrentPosition();}
		
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
	public void onStop() {
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
}
