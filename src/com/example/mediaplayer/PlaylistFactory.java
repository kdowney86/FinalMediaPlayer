package com.example.mediaplayer;

public class PlaylistFactory {

	public Playlist getPlaylist(Media m){
			int i = 0;
			if(m.get(i).type().equals("mp3")){
				return new AudioPlaylist(m);
			}else if(m.type().equals("mp4")){		
				return new VideoPlaylist(m);
			}
		return null;		
	}
}
