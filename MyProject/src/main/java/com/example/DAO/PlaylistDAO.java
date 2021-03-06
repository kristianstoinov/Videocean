package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.classes.Playlist;
import com.example.classes.TYPE;
import com.example.classes.User;
import com.example.exceptions.ClipException;
import com.example.exceptions.PlaylistException;
import com.example.exceptions.UserProblemException;
import com.example.interfaces.IPlaylistDAO;
import com.example.interfaces.IUser;

public class PlaylistDAO extends AbstractDAO implements IPlaylistDAO {

	private static final String SELECT_FROM_PLAYLISTS = "SELECT * FROM playlists where playlist_id=?";
	private static final String ALL_CLIPS_QUERY = "SELECT * FROM clips_to_playlists WHERE playlist_id= ? ;";
	private static final String INCREASE_VIEWS_OF_PLAYLIST_QUERY = "UPDATE  playlists SET  playlist_views = playlist_views + 1  WHERE playlist_id = ? ;";
	private static final String REMOVE_CLIP_FROM_PLAYLIST_QUERY = "DELETE FROM clips_to_playlists WHERE clip_id=? AND playlist_id=? LIMIT 1";
	private static final String ADD_CLIP_TO_PLAYLIST_QUERY = "INSERT INTO clips_to_playlists VALUES(null,?,?);";
	private static final String CREATE_PLAYLIST_QUERY = "INSERT INTO playlists VALUES(null,?,?,?)";
	private static final String REMOVE_PLAYLIST_BY_ID_QUERY = "DELETE FROM playlists WHERE playlist_id= ?;";

	@Override
	public int createPlaylist(Playlist playlist) throws PlaylistException {
		if (playlist != null) {
			PreparedStatement ps = null;
			ResultSet resultSet = null;
			try {
				ps = getCon().prepareStatement(CREATE_PLAYLIST_QUERY, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, playlist.getName());
				ps.setInt(2, 0);
				ps.setInt(3, ((IUser) playlist.getOwner()).getUserID());
				ps.executeUpdate();

				resultSet = ps.getGeneratedKeys();
				resultSet.next();

				return resultSet.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PlaylistException("Can`t creat Playlist");
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (resultSet != null)
						resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new PlaylistException("Can`t creat Playlist");
		}
	}

	@Override
	public void addClipToPlaylist(int playlistID, int clipID) throws PlaylistException, ClipException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(ADD_CLIP_TO_PLAYLIST_QUERY);
			ps.setInt(1, playlistID);
			ps.setInt(2, clipID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void removeClipFromPlaylist(int playlistId, int clipId) throws PlaylistException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(REMOVE_CLIP_FROM_PLAYLIST_QUERY);
			ps.setInt(1, clipId);
			ps.setInt(2, playlistId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void increaseViewsOfPlaylist(Playlist playlist) {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(INCREASE_VIEWS_OF_PLAYLIST_QUERY);
			ps.setInt(1, playlist.getPlaylistID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Playlist getAllClipsForPlaylist(int playlistID) throws PlaylistException {
<<<<<<< HEAD
		PreparedStatement ps ;
		ResultSet resultSet;
=======
		PreparedStatement ps = null;
		ResultSet resultSet = null;
>>>>>>> origin/master
		try {
			ps = getCon().prepareStatement(ALL_CLIPS_QUERY);
			ps.setInt(1, playlistID);
			resultSet = ps.executeQuery();
<<<<<<< HEAD
			
			UserDAO user = new UserDAO();
//			User owner = user.getUserById(resultSet.getInt(4));
			Playlist playlist=new PlaylistDAO().getPlaylistById(playlistID);
=======
			UserDAO user = new UserDAO();
			User owner = user.getUserById(resultSet.getInt(4));
			Playlist playlist = new Playlist(resultSet.getString(2), owner, TYPE.PUBLIC);
>>>>>>> origin/master
			ClipDAO clip = new ClipDAO();
			while (resultSet.next()) {
				playlist.addClipToPlaylist(clip.getClipByID(resultSet.getInt(3)));
			}
			return playlist;
		} catch (SQLException | ClipException | UserProblemException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid Playlist ID.");
<<<<<<< HEAD
	} 
		//finally {
//			try {
//				if (ps != null)
//					ps.close();
//				if (resultSet != null)
//					resultSet.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
=======
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
>>>>>>> origin/master
	}

	@Override
	public Playlist getPlaylistById(int playlistId) throws PlaylistException {
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Playlist playlist;
		try {
			ps = getCon().prepareStatement(SELECT_FROM_PLAYLISTS);
			ps.setInt(1, playlistId);
			resultSet = ps.executeQuery();
			UserDAO user = new UserDAO();
			resultSet.next();
			String name = resultSet.getString(2);
			int views = resultSet.getInt(3);
			User owner = user.getUserById(resultSet.getInt(4));
			playlist = new Playlist(playlistId, name, owner, TYPE.PUBLIC);
			playlist.setViewsOfPlaylist(views);
			return playlist;
		} catch (SQLException | UserProblemException | PlaylistException e) {
			e.printStackTrace();
			throw new PlaylistException("Something got wrong.Please try again later", e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
<<<<<<< HEAD
	public Playlist getPlaylistByOwner(int userId) throws PlaylistException{
		PreparedStatement ps;
		Playlist playlist = null;
		try {
			ps = getCon().prepareStatement("SELECT * FROM playlists where owner_id=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			UserDAO user = new UserDAO();
			if(rs.next()){
			int playlistId=rs.getInt(1);
			String name = rs.getString(2);
			int views = rs.getInt(3);
			User owner = user.getUserById(rs.getInt(4));
			// tuk tryabva da se selectva state a ne da go podavam;
			playlist = new Playlist(playlistId, name, owner, TYPE.PUBLIC);
			playlist.setViewsOfPlaylist(views);
=======

	@Override
	public void removePlaylistByID(int playlistID) throws PlaylistException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(REMOVE_PLAYLIST_BY_ID_QUERY);
			ps.setInt(1, playlistID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid id for Playlist");
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
>>>>>>> origin/master
			}
		}
	}
	@Override
	public void removePlaylistByID(int playlistID) throws PlaylistException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(REMOVE_PLAYLIST_BY_ID_QUERY);
			ps.setInt(1, playlistID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid id for Playlist");
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
