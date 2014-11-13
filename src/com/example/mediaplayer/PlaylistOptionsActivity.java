package com.example.mediaplayer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class PlaylistOptionsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_playlist);		
	}
	
	public void songList(View v){
		this.finish();
	}

	public void videoList(View v){
		this.finish();
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
}
