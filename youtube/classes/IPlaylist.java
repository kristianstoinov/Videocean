package com.youtube.classes;

public interface IPlaylist {

	void addClipToPlaylist(Clip clip);

	void removeClipFromPlaylist(Clip clip) throws PlaylistException;

	void changeState(Type state);

	void increaseViewsOfPlaylist();

}