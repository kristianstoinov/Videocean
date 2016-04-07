package com.example.interfaces;

import com.example.classes.Clip;
import com.example.classes.TYPE;
import com.example.exceptions.PlaylistException;

public interface IPlaylist {

	void addClipToPlaylist(Clip clip);

	void removeClipFromPlaylist(Clip clip) throws PlaylistException;

	void changeState(TYPE state);

	void increaseViewsOfPlaylist();

	String getName();

	Object getOwner();

}