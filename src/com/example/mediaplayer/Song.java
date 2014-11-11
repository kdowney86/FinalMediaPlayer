package com.example.mediaplayer;

import java.util.ArrayList;
import java.util.List;

public class Song {

	private String name;
	private List<Song> songGroup;

	// constructor
	public Song(String name) {
		this.name = name;
		songGroup = new ArrayList<Song>();
	}

	public Song() {
		this.name = "";
		songGroup = new ArrayList<Song>();
	}

	public void add(Song e) {
		songGroup.add(e);
	}

	public void remove(Song e) {
		songGroup.remove(e);
	}

	public List<Song> songGroup(){
		return songGroup;
	}
	
	public Song get(int pos){
		return songGroup.get(pos);		
	}

	public String toString(){
		return name;
	}
}

