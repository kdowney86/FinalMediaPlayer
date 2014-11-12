package com.example.mediaplayer;

public abstract class Playlist {
	protected Media media;
	
	public Playlist(Media m) {
		this.media = m;
	}
	
	public void addMedia(Media m) {
		media.add(m);
	}
	
	public void removeMedia(Media m){
		media.remove(m);
	}
	
	public abstract void play();
	public abstract void next();
	public abstract void previous();

}
