package com.example.mediaplayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public abstract class ListActivity extends ActionBarActivity implements Observer {

	protected MediaFilesObserver mfo;
	protected ListView filesList;
	protected String mediaDirectoryPath;
	public static Media fileToPlay;
	protected Media fileNames;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_files);
		mediaDirectoryPath = "/sdcard/Download";
		fileToPlay  = new Media();
		mfo = new MediaFilesObserver(mediaDirectoryPath);
		mfo.registerObserver(this);
		mfo.startWatching();
		filesList = (ListView)findViewById(R.id.filesList);
		refreshList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.files, menu);
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

	@Override
	public void update(int event, String path) {
		refreshList();
		
	}
	
	public abstract void refreshList();
	
	public static String getFileExt(String fileName)
	{       
	     return fileName.substring((fileName.lastIndexOf(".") + 1), fileName.length());
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
