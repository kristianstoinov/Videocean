package interfaces;

import java.util.List;

import classes.Playlist;
import exceptions.ClipException;
import exceptions.PlaylistException;

public interface IPlaylistDAO {

	// Create Playlist
	int createPlaylist(IPlaylist playlist) throws PlaylistException;

	// ADD Clip To Playlist
	void addClipToPlaylist(int playlistID, int clipID) throws PlaylistException, ClipException;

	void removeClipFromPlaylist(int playlistId, int clipId) throws PlaylistException;

	void increaseViewsOfPlaylist(Playlist playlist);

	// Return new Playlist by ID
	IPlaylist getAllClipsForPlaylist(int playlistID) throws PlaylistException;

	// Reutnrs list of Clip by playlist IDs
	Playlist getPlaylistById(int playlistId) throws PlaylistException;

	// Remove Playlist by ID
	void removePlaylistByID(int playlistID) throws PlaylistException;

	// Serach Playlists by name
	List<IPlaylist> serachPlaylistByName(String name) throws PlaylistException;

}