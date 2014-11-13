package com.example.mediaplayer;

public class PlaylistFactory {

	private AudioPlaylist audioPlaylist;
	private VideoPlaylist videoPlaylist;
	private Media audioMedia = new Media();
	private Media videoMedia = new Media();
	
	public Playlist getPlaylist(Media m){
		
		audioPlaylist = new AudioPlaylist(audioMedia);
		videoPlaylist = new VideoPlaylist(videoMedia);
		
		for(int i = 0; i < m.mediaGroup().size(); i++){
			if(m.get(i).type().equals("mp3")){
				audioMedia.add(m.get(i));
			}else if(m.type().equals("mp4")){		
				videoMedia.add(m.get(i));
			}
		}
		if(audioMedia.mediaGroup().size() > 0){
			audioPlaylist = new AudioPlaylist(audioMedia);
		}
		if(videoMedia.mediaGroup().size() > 0){
			videoPlaylist = new VideoPlaylist(videoMedia);
		}
		return null;		
	}
}
