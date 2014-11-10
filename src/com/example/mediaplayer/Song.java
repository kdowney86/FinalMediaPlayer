package com.example.mediaplayer;

import java.util.ArrayList;
import java.util.List;

public class Song {

	private String name;
	   private String artist;
	   private String year;
	   private List<Song> songGroup;

	   // constructor
	   public Song(String name, String artist, String year) {
	      this.name = name;
	      this.artist = artist;
	      this.year = year;
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

	   public String toString(){
	      return ("Song :[ Name : "+ name 
	      +", artist : "+ artist + ", year :"
	      + year +" ]");
	   }
}
