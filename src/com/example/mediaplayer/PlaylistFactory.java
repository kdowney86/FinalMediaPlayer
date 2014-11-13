package com.example.mediaplayer;

public class PlaylistFactory {

	private AudioPlaylist audioPlaylist;
	private VideoPlaylist videoPlaylist;
	
	public Playlist getPlaylist(Media m){
				
		for(int i = 0; i < m.mediaGroup().size(); i++){
			if(m.get(i).type().equals("mp3")){
				audioPlaylist.addMedia(m.get(i));
			}else if(m.type().equals("mp4")){		
				videoPlaylist.addMedia(m.get(i));
			}
		}
		return null;		
	}
}
