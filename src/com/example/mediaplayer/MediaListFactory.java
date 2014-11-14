package com.example.mediaplayer;

import android.content.Context;
import android.content.Intent;

public class MediaListFactory {
	
	public Intent getIntent(int i, Context con) {
		if (i == R.id.bSongList) {
			return new Intent(con, AudioListActivity.class);
		} else if (i == R.id.bVideoList) {
			return new Intent(con, VideoListActivity.class);
		} return null;
	}

}
