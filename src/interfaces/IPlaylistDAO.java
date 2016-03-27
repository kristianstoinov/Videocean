package interfaces;

import java.sql.SQLException;
import java.util.List;

import classes.Clip;
import classes.Playlist;
import exceptions.ClipException;
import exceptions.PlaylistException;

public interface IPlaylistDAO {

	// Create Playlist
	int createPlaylist(Playlist playlist) throws PlaylistException;

	// ADD Clip To Playlist
	void addClipToPlaylist(Playlist playlist, Clip clip) throws PlaylistException, ClipException, SQLException;

	// REMOVE clip from Playlist
	void removeClipFromPlaylist(int playlistId, int clipId) throws PlaylistException, ClipException, SQLException;

	// Increase Views of playlist by playlist ID
	void increaseViewsOfPlaylist(Playlist playlist) throws SQLException;

	// Return list of ID of clips from playlist
	List<Integer> AllClips(int playlistID) throws SQLException, PlaylistException;
	
	// Returns a list of IDs of Playlist by User ID
	public List<Integer> allPlayListForUser(int userID) throws PlaylistException;

}