package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Clip;
import classes.Playlist;
import exceptions.ClipException;
import exceptions.PlaylistException;
import interfaces.IPlaylistDAO;

public class PlaylistDAO extends AbstractDAO implements IPlaylistDAO {

	private static final String ALL_CLIPS_QUERY = "SELECT * FROM clips_to_playlists WHERE playlist_id= ? ;";
	private static final String INCREASE_VIEWS_OF_PLAYLIST_QUERY = "UPDATE  playlists SET  playlist_views = playlist_views + 1  WHERE id = ? ;";
	private static final String REMOVE_CLIP_FROM_PLAYLIST_QUERY = "DELETE FROM clips_to_playlists WHERE clip_id=? AND playlist_id=?;";
	private static final String ADD_CLIP_TO_PLAYLIST_QUERY = "INSERT INTO clips_to_playlists(playlist_id, clip_id) VALUES(null,?,?);";
	private static final String CREATE_PLAYLIST_QUERY = "INSERT INTO playlists VALUES(null,?,?,?)";

	// Create Playlist
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylistDAO#createPlaylist(classes.Playlist)
	 */
	@Override
	public int createPlaylist(Playlist playlist) throws PlaylistException {
		if (playlist != null) {
			String insertSQL = CREATE_PLAYLIST_QUERY;
			try {
				PreparedStatement stmt = getCon().prepareStatement(insertSQL);
				stmt.setString(2, playlist.getName());
				stmt.setInt(3, 0);
				stmt.setInt(4, playlist.getOwner().getUserID());
				stmt.executeQuery();
				ResultSet rs = stmt.getGeneratedKeys();
				rs.next();

				return rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PlaylistException("Can`t creat Playlist");
			}
		} else {
			throw new PlaylistException("Can`t creat Playlist");
		}
	}

	// ADD Clip To Playlist
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylistDAO#addClipToPlaylist(classes.Playlist, classes.Clip)
	 */
	@Override
	public void addClipToPlaylist(Playlist playlist, Clip clip) throws PlaylistException, ClipException, SQLException {
		if (playlist != null) {
			if (clip != null) {
				PreparedStatement stmt = getCon().prepareStatement(ADD_CLIP_TO_PLAYLIST_QUERY);
				stmt.setInt(2, playlist.getPlaylistID());
				stmt.setInt(3, clip.getClipID());
				stmt.executeUpdate();
			} else {
				throw new ClipException("Invalid clip");
			}

		} else {
			throw new PlaylistException("Invalid Playlist.");
		}
	}

	// REMOVE clip from Playlist
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylistDAO#removeClipFromPlaylist(int, int)
	 */
	@Override
	public void removeClipFromPlaylist(int playlistId, int clipId)
			throws PlaylistException, ClipException, SQLException {
		PreparedStatement stmt = getCon().prepareStatement(REMOVE_CLIP_FROM_PLAYLIST_QUERY);
		stmt.setInt(1, clipId);
		stmt.setInt(2, playlistId);
	}

	// Increase Views of playlist by playlist ID
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylistDAO#increaseViewsOfPlaylist(classes.Playlist)
	 */
	@Override
	public void increaseViewsOfPlaylist(Playlist playlist) throws SQLException {
		String insertSQL = INCREASE_VIEWS_OF_PLAYLIST_QUERY;
		PreparedStatement stmt = getCon().prepareStatement(INCREASE_VIEWS_OF_PLAYLIST_QUERY);
		stmt.setInt(1, playlist.getPlaylistID());
		stmt.executeUpdate();
	}

	// Return list of ID of clips from playlist
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylistDAO#AllClips(classes.Playlist)
	 */
	@Override
	public List<Integer> AllClips(Playlist playlist) throws SQLException {
		List allClips = new ArrayList<Clip>();
		PreparedStatement statement = getCon().prepareStatement(ALL_CLIPS_QUERY);
		statement.setInt(1, playlist.getPlaylistID());
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			allClips.add(resultSet.getInt(3));
		}
		return allClips;
	}

}
