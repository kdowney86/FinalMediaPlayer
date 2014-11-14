package com.example.mediaplayer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MediaOptionsActivity extends ActionBarActivity {

	Intent myIntent;
	MediaListFactory myFactory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picklist);		
        myFactory = new MediaListFactory();
	}
	
	public void onClick(View v) { // Parameter v stands for the view that was clicked.  

		myIntent = myFactory.getIntent(v.getId(), MediaOptionsActivity.this);
		startActivity(myIntent);
	}
    
    @Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}
}
