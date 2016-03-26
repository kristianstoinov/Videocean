package com.youtube.classes;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
	private final String name;
	private final User owner;
	private Type state;
	private int clipsCounter;
	private int viewsOfPlaylist;
	private List<String> clips = null;

	public Playlist(String name, User owner, Type state) throws PlaylistException {
		// Chek for name validity
		if (name != null && !name.equals("")) {
			this.name = name;
		} else {
			throw new PlaylistException("Ivalid name for Playlist");
		}
		// Chek for Îwner validity
		if (owner != null) {
			this.owner = owner;
		} else {
			throw new PlaylistException("Invalid user.");
		}
		this.state = state;
		this.clipsCounter = 0;
		this.viewsOfPlaylist = 0;
	}

	public void addClipToPlaylist(String clipUrl) {
		if (clips != null) {
			clips = new ArrayList<String>();
		}
		clips.add(clipUrl);
		increaseClipsCounter();
	}

	private void increaseClipsCounter() {
		this.clipsCounter++;
	}

	public void removeClipFromPlaylist(String clipUrl) throws PlaylistException {
		if (clips.contains(clipUrl)) {
			clips.remove(clipUrl);
			reducedClipsCounter();
		} else {
			throw new PlaylistException("Clip not found in Playlist.");
		}

	}

	private void reducedClipsCounter() {
		this.clipsCounter--;
	}

	public void changeState(Type state) {
		this.state = state;
	}

	public void increaseViewsOfPlaylist() {
		this.viewsOfPlaylist++;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) throws PlaylistException {

	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) throws PlaylistException {

	}

	public int getClipsCounter() {
		return clipsCounter;
	}

	public int getViewsOfPlayList() {
		return viewsOfPlaylist;
	}

	public Type getState() {
		return state;
	}

	public void setState(Type state) {
		this.state = state;
	}

}
