package classes;

import java.util.ArrayList;
import java.util.List;

import exceptions.PlaylistException;

public class Playlist{
	private final String name;
	private final User owner;
	private TYPE state;
	private int clipsCounter;
	private int viewsOfPlaylist;
	private List<Clip> clips = null;

	public Playlist(String name, User owner, TYPE state) throws PlaylistException {
		// Chek for name validity
		if (name != null && !name.equals("")) {
			this.name = name;
		} else {
			throw new PlaylistException("Ivalid name for Playlist");
		}
		// Chek for �wner validity
		if (owner != null) {
			this.owner = owner;
		} else {
			throw new PlaylistException("Invalid user.");
		}
		this.state = state;
		this.clipsCounter = 0;
		this.viewsOfPlaylist = 0;
	}

	
	
	public void addClipToPlaylist(Clip clip) {
		if (clips != null) {
			clips = new ArrayList<Clip>();
		}
		clips.add(clip);
		increaseClipsCounter();
	}

	private void increaseClipsCounter() {
		this.clipsCounter++;
	}



	public void removeClipFromPlaylist(Clip clip) throws PlaylistException {
		if (clips.contains(clip)) {
			clips.remove(clip);
			reducedClipsCounter();
		} else {
			throw new PlaylistException("Clip not found in Playlist.");
		}

	}

	private void reducedClipsCounter() {
		this.clipsCounter--;
	}
	
	public void changeState(TYPE state) {
		this.state = state;
	}

	public void increaseViewsOfPlaylist() {
		this.viewsOfPlaylist++;
	}

	public String getName() {
		return name;
	}

	public User getOwner() {
		return owner;
	}

	public int getClipsCounter() {
		return clipsCounter;
	}

	public int getViewsOfPlayList() {
		return viewsOfPlaylist;
	}

	public TYPE getState() {
		return state;
	}

	public void setState(TYPE state) {
		this.state = state;
	}

}