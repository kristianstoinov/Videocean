package DAO;

import classes.Clip;
import classes.Playlist;
import exceptions.ClipException;
import exceptions.PlaylistException;

public interface IPlaylistDAO {

	// Create Playlist
	int createPlaylist(Playlist playlist) throws PlaylistException;

	// ADD Clip To Playlist
	void addClipToPlaylist(Playlist playlist, Clip clip) throws PlaylistException, ClipException;

	// REMOVE clip from Playlist
	void removeClipFromPlaylist(int playlistId, int clipId) throws PlaylistException;

	// Increase Views of playlist by playlist ID
	void increaseViewsOfPlaylist(Playlist playlist);

	Playlist getAllClipsForPlaylist(int playlistID) throws PlaylistException;

	// metoda vrashta playlist po ID ot tablicata s playlists
	Playlist getPlaylistById(int playlistId) throws PlaylistException;

	void removePlaylistByID(int playlistID) throws PlaylistException;

}