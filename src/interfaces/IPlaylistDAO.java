package interfaces;

import java.sql.SQLException;
import java.util.List;

import classes.Clip;
import classes.Playlist;
import exceptions.ClipException;
import exceptions.PlaylistException;
import exceptions.UserProblemException;

public interface IPlaylistDAO {

	// Create Playlist
	int createPlaylist(IPlaylist playlist) throws PlaylistException;

	// ADD Clip To Playlist
	void addClipToPlaylist(Playlist playlist, Clip clip) throws PlaylistException, ClipException, SQLException;

	// REMOVE clip from Playlist
	void removeClipFromPlaylist(int playlistId, int clipId) throws PlaylistException, ClipException, SQLException;

	// Increase Views of playlist by playlist ID
	void increaseViewsOfPlaylist(Playlist playlist) throws SQLException;

	// Returns list of ID of clips from playlist
	public IPlaylist getAllClipsForPlaylist(int playlistID)
			throws SQLException, PlaylistException, ClipException, UserProblemException;

	// Returns a list of IDs of Playlist by User ID
	public List<Integer> allPlayListForUser(int userID) throws PlaylistException;

	// Returns a new List of IDs of Playlist by User ID
	public List<IPlaylist> getAllPlayListForUser(int userID) throws PlaylistException;

	// Reutnrs list of Clip by playlist IDs 
	public Playlist getPlaylistById(int playlistId) throws PlaylistException;

}