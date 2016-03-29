package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Clip;
import classes.Playlist;
import classes.TYPE;
import classes.User;
import exceptions.ClipException;
import exceptions.PlaylistException;
import exceptions.UserProblemException;
import interfaces.IPlaylist;
import interfaces.IPlaylistDAO;
import interfaces.IUser;

public class PlaylistDAO extends AbstractDAO implements IPlaylistDAO {

	private static final String GET_ALL_PLAYLIST_BY_ID_FOR_USER = "SELECT * FROM library WHERE user_id=?";
	private static final String SELECT_FROM_PLAYLISTS = "SELECT * FROM playlists where playlist_id=?";
	private static final String SELECT_PLAYLIST_BY_ID = "SELECT * FROM clips_to_playlists WHERE playlist_id=?";
	private static final String ALL_CLIPS_QUERY = "SELECT * FROM clips_to_playlists WHERE playlist_id= ? ;";
	private static final String INCREASE_VIEWS_OF_PLAYLIST_QUERY = "UPDATE  playlists SET  playlist_views = playlist_views + 1  WHERE id = ? ;";
	private static final String REMOVE_CLIP_FROM_PLAYLIST_QUERY = "DELETE FROM clips_to_playlists WHERE clip_id=? AND playlist_id=?;";
	private static final String ADD_CLIP_TO_PLAYLIST_QUERY = "INSERT INTO clips_to_playlists(playlist_id, clip_id) VALUES(null,?,?);";
	private static final String CREATE_PLAYLIST_QUERY = "INSERT INTO playlists VALUES(null,?,?,?)";

	// Create Playlist
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylist#createPlaylist(interfaces.IPlaylist)
	 */
	@Override
	public int createPlaylist(IPlaylist playlist) throws PlaylistException {
		if (playlist != null) {
			String insertSQL = CREATE_PLAYLIST_QUERY;
			try {
				PreparedStatement stmt = getCon().prepareStatement(insertSQL);
				stmt.setString(2, playlist.getName());
				stmt.setInt(3, 0);
				stmt.setInt(4, ((IUser) playlist.getOwner()).getUserID());
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
	 * @see DAO.IPlaylist#addClipToPlaylist(classes.Playlist, classes.Clip)
	 */
	@Override
	public void addClipToPlaylist(Playlist playlist, Clip clip) throws PlaylistException, ClipException {
		if (playlist != null) {
			if (clip != null) {
				PreparedStatement stmt;
				try {
					stmt = getCon().prepareStatement(ADD_CLIP_TO_PLAYLIST_QUERY);
					stmt.setInt(2, playlist.getPlaylistID());
					stmt.setInt(3, clip.getClipID());
					stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
	 * @see DAO.IPlaylist#removeClipFromPlaylist(int, int)
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
	 * @see DAO.IPlaylist#increaseViewsOfPlaylist(classes.Playlist)
	 */
	@Override
	public void increaseViewsOfPlaylist(Playlist playlist) {
		String insertSQL = INCREASE_VIEWS_OF_PLAYLIST_QUERY;
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(INCREASE_VIEWS_OF_PLAYLIST_QUERY);
			stmt.setInt(1, playlist.getPlaylistID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Return new Playlist by ID
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylist#getAllClipsForPlaylist(int)
	 */
	@Override
	public IPlaylist getAllClipsForPlaylist(int playlistID) throws PlaylistException {
		IPlaylist playlist = null;
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(ALL_CLIPS_QUERY);
			stmt.setInt(1, playlistID);
			ResultSet resultSet = stmt.executeQuery();
			UserDAO user = new UserDAO();
			User owner = user.getUserById(resultSet.getInt(4));
			// tuk tryabva da se selectva state a ne da go podavam;
			// Trqbva li da castvam kym IPlaylist
			playlist = (IPlaylist) new Playlist(resultSet.getString(2), owner, TYPE.PUBLIC);
			while (resultSet.next()) {
				ClipDAO clip = new ClipDAO();
				playlist.addClipToPlaylist(clip.getClipByID(resultSet.getInt(3)));
			}
		} catch (SQLException | ClipException | UserProblemException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid Playlist ID.");
		}
		return playlist;
	}

	// Returns a list of IDs of Playlist by User ID
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylist#allPlayListForUser(int)
	 */
	@Override
	public List<Integer> allPlayListForUser(int userID) throws PlaylistException {
		List<Integer> allPlayListForUser = new ArrayList<Integer>();
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(GET_ALL_PLAYLIST_BY_ID_FOR_USER);
			ResultSet resultSet = stmt.executeQuery();
			stmt.setInt(1, userID);
			while (resultSet.next()) {
				allPlayListForUser.add(resultSet.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid Playlist ID.");
		}
		return allPlayListForUser;
	}

	// Returns a new List of Playlist by User ID
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylist#getAllPlayListForUser(int)
	 */
	@Override
	public List<IPlaylist> getAllPlayListForUser(int userID) throws PlaylistException {
		PreparedStatement stmt;
		List<IPlaylist> allPlayListForUser = new ArrayList<IPlaylist>();
		try {
			stmt = getCon().prepareStatement(GET_ALL_PLAYLIST_BY_ID_FOR_USER);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {

				allPlayListForUser.add((IPlaylist) getPlaylistById(resultSet.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPlayListForUser;
	}

	// public List<Integer> playlistByID(int playlistID) {
	// List<Integer> clipsInThisPlaylist = new ArrayList<Integer>();
	// try {
	// PreparedStatement stmt =
	// getCon().prepareStatement(SELECT_PLAYLIST_BY_ID);
	// stmt.setInt(1, playlistID);
	// ResultSet resultSet = stmt.executeQuery();
	// resultSet = stmt.executeQuery();
	// while (resultSet.next()) {
	// clipsInThisPlaylist.add(resultSet.getInt(3));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return clipsInThisPlaylist;
	//
	// }

	// metoda vrashta playlist po ID ot tablicata s playlists
	/*
	 * (non-Javadoc)
	 * 
	 * @see DAO.IPlaylist#getPlaylistById(int)
	 */
	// Reutnrs list of Clip by playlist IDs 
	@Override
	public Playlist getPlaylistById(int playlistId) throws PlaylistException {
		PreparedStatement ps;
		Playlist playlist;
		try {
			ps = getCon().prepareStatement(SELECT_FROM_PLAYLISTS);
			ps.setInt(1, playlistId);

			ResultSet rs = ps.executeQuery();
			UserDAO user = new UserDAO();
			rs.next();
			String name = rs.getString(2);
			int views = rs.getInt(3);
			User owner = user.getUserById(rs.getInt(4));
			// tuk tryabva da se selectva state a ne da go podavam;
			playlist = new Playlist(name, owner, TYPE.PUBLIC);
			return playlist;
		} catch (SQLException | UserProblemException | PlaylistException e) {
			e.printStackTrace();
			throw new PlaylistException("Couldn't get playlist!", e);
		}
	}

}
