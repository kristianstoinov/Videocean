package com.example.interfaces;
import com.example.classes.Playlist;
import com.example.exceptions.ClipException;
import com.example.exceptions.PlaylistException;

public interface IPlaylistDAO {

	// Create Playlist
	int createPlaylist(Playlist playlist) throws PlaylistException;

	// ADD Clip To Playlist
	void addClipToPlaylist(int i, int j) throws PlaylistException, ClipException;

	// REMOVE clip from Playlist
	void removeClipFromPlaylist(int playlistId, int clipId) throws PlaylistException;

	// Increase Views of playlist by playlist ID
	void increaseViewsOfPlaylist(Playlist playlist);

	Playlist getAllClipsForPlaylist(int playlistID) throws PlaylistException;

	// metoda vrashta playlist po ID ot tablicata s playlists
	Playlist getPlaylistById(int playlistId) throws PlaylistException;

	void removePlaylistByID(int playlistID) throws PlaylistException;

}