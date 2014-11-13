package com.example.mediaplayer;

import java.io.File;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PlaylistsActivity extends ActionBarActivity implements Observer {

	private String mediaDirectoryPath;
	private Media fileNames;
	private MediaFilesObserver mfo;
	private ListView filesList;
	private Playlist playlist;
	private PlaylistFactory playlistFactory;
	private Media medialist;
	
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_playlists);
		mediaDirectoryPath = "/sdcard/Download";
		mfo = new MediaFilesObserver(mediaDirectoryPath);
		mfo.registerObserver(this);
		mfo.startWatching();
		filesList = (ListView)findViewById(R.id.listPlaylist);
		refreshList();
		
		filesList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				String path = "/sdcard/Download/"+fileNames.get(position).toString();
				String type = getFileExt(fileNames.get(position).toString());
				medialist.add(new Media(path));
			}
		});
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
		// TODO Auto-generated method stub
		refreshList();
		
	}
	
	public void refreshList(){
		File downloadDirectory = new File(mediaDirectoryPath);
	    File [] files = downloadDirectory.listFiles();
	    fileNames= new Media();
		for(File f : files){
			String extension = getFileExt(f.getPath());
			if (extension.equals("mp3") || extension.equals("mp4")) {
				fileNames.add(new Media(f.getPath().substring(17)));
			}
		}
		ArrayAdapter<Media> filesAdapter = new ArrayAdapter<Media>(this, android.R.layout.simple_list_item_1, fileNames.mediaGroup());
		filesList.setAdapter(filesAdapter);
	}
	
	public static String getFileExt(String fileName)
	{       
	     return fileName.substring((fileName.lastIndexOf(".") + 1), fileName.length());
	}

	public void addToPlaylist(View v){
		playlist = playlistFactory.getPlaylist(medialist);
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
