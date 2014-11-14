package com.example.mediaplayer;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class AudioListActivity extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		filesList.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				fileToPlay.mediaGroup().clear();
				
				fileToPlay.add(fileNames.get(position));
					Intent myIntent = new Intent(AudioListActivity.this, AudioPlayerActivity.class);
					startActivity(myIntent);
			}
		});
	}
	
	@Override
	public void refreshList() {
		File downloadDirectory = new File(mediaDirectoryPath);
	    File [] files = downloadDirectory.listFiles();
	    fileNames= new Media();
		for(File f : files){
			String extension = getFileExt(f.getPath());
			if (extension.equals("mp3")) {
				fileNames.add(new Media(f.getPath().substring(17)));
			}
		}
		ArrayAdapter<Media> filesAdapter = new ArrayAdapter<Media>(this, android.R.layout.simple_list_item_1, fileNames.mediaGroup());
		filesList.setAdapter(filesAdapter);
	}


}
