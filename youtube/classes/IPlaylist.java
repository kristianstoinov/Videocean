package com.youtube.classes;

public interface IPlaylist {

	void addClipToPlaylist(String clipUrl);

	void removeClipFromPlaylist(String clipUrl) throws PlaylistException;

	void changeState(Type state);

	void increaseViewsOfPlaylist();

}