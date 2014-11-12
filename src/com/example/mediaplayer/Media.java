package com.example.mediaplayer;

import java.util.ArrayList;
import java.util.List;

public class Media {

	private String name;
	private List<Media> mediaGroup;

	// constructor
	public Media(String name) {
		this.name = name;
		mediaGroup = new ArrayList<Media>();
	}

	public Media() {
		// TODO Auto-generated constructor stub
		this.name = "";
		mediaGroup = new ArrayList<Media>();
	}

	public void add(Media e) {
		mediaGroup.add(e);
	}

	public void remove(Media e) {
		mediaGroup.remove(e);
	}
	
	public Media get(int pos){		
		return mediaGroup.get(pos);		
	}

	public String toString(){
		return name;
	}

	public List<Media> mediaGroup() {
		return mediaGroup;
	}
}
