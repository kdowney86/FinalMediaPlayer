package com.example.mediaplayer;

import java.io.File;
import java.util.ArrayList;

public abstract class Playlist {
	protected ArrayList<File> files;
	
	public Playlist(ArrayList<File> f) {
		this.files = f;
	}
	
	public void addFile(File f) {
		files.add(f);
	}
	
	public void removeFile(File f){
		files.add(f);
	}
	
	public abstract void play();

}
