package com.example.mediaplayer;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlaylistsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playlists);
		
		Button addPlaylistButton = (Button) findViewById(R.id.addPlaylistButton);
		addPlaylistButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				AlertDialog alertDialog = new AlertDialog.Builder(PlaylistsActivity.this).create();

			    alertDialog.setTitle("Playlist Creation");

			    alertDialog.setMessage("Please Select a Playlist Type");

			    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Audio Playlist", new DialogInterface.OnClickListener() {

			      public void onClick(DialogInterface dialog, int id) {

			        //...

			    } }); 

			    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {

			      public void onClick(DialogInterface dialog, int id) {

			        //...

			    }}); 

			    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Video Playlist", new DialogInterface.OnClickListener() {

			      public void onClick(DialogInterface dialog, int id) {

			        //...

			    }});
			    alertDialog.show();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.playlists, menu);
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
}
