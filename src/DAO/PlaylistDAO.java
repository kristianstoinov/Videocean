package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Clip;
import classes.Playlist;
import exceptions.ClipException;
import exceptions.PlaylistException;

public class PlaylistDAO {

	// Create Playlist
	public void creatPlaylist(Playlist playlist) throws PlaylistException {
		if (playlist != null) {
			int insertId = -1;
			Connection con = DBConnection.getInstance().getConnection();
			String insertSQL = "INSERT INTO playlists VALUES(null,?,?,?)";
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement(insertSQL);
				stmt.setString(2, playlist.getName());
				stmt.setInt(3, 0);
				stmt.setInt(4, playlist.getOwner().getId());
				stmt.executeQuery();

				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					insertId = rs.getInt(1);
					// Set id from DB
					playlist.setId(insertId);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PlaylistException("Can`t creat Playlist")
			}
		}
	}

	// ADD Clip To Playlist
	public void addClipToPlaylist(Playlist playlist, Clip clip) throws PlaylistException, ClipException, SQLException {
		if (playlist != null) {
			if (clip != null) {
				Connection con = DBConnection.getInstance().getConnection();
				String insertSQL = "INSERT INTO clips_to_playlists(playlist_id, clip_id) VALUES(?,?,?)";
				PreparedStatement stmt = con.prepareStatement(insertSQL);
				stmt.setInt(2, playlist.getId());
				stmt.setDouble(3, clip.getId());
				stmt.executeUpdate();
			} else {
				throw new ClipException("Invalid clip");
			}

		} else {
			throw new PlaylistException("Invalid Playlist.");
		}
	}

	// REMOVE clip from Playlist
	public void removeClipFromPlaylist(int playlistId, int clipId)
			throws PlaylistException, ClipException, SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		String insertSQL = "DELETE FROM clips_to_playlists WHERE clip_id=? AND playlist_id=?";
		PreparedStatement stmt = con.prepareStatement(insertSQL);
		stmt.setInt(1, clipId);
		stmt.setInt(2, playlistId);
	}

	// Increase Views of playlist by playlist ID
	public void increaseViewsOfPlaylist(Playlist playlist) throws SQLException {
		Connection con = DBConnection.getInstance().getConnection();
		String insertSQL = "UPDATE  playlists SET  playlist_views = playlist_views + 1  WHERE id = " + playlist.getId();
		PreparedStatement stmt = con.prepareStatement(insertSQL);
		stmt.executeUpdate();
	}

	public void sortByName() {
		Connection con = DBConnection.getInstance().getConnection();
		String insertSQL = "SELECT * FROM playlist whre";
	}

}
