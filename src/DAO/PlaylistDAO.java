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

	private static final String DELETE_PLAYLIST_BY_ID_QUERY = "DELETE FROM playlists WHERE playlist_id = ?";
	private static final String SELECT_FROM_PLAYLISTS = "SELECT * FROM playlists where playlist_id=?";
	private static final String ALL_CLIPS_QUERY = "SELECT * FROM clips_to_playlists WHERE playlist_id= ? ;";
	private static final String INCREASE_VIEWS_OF_PLAYLIST_QUERY = "UPDATE  playlists SET  playlist_views = playlist_views + 1  WHERE id = ? ;";
	private static final String REMOVE_CLIP_FROM_PLAYLIST_QUERY = "DELETE FROM clips_to_playlists WHERE clip_id=? AND playlist_id=?;";
	private static final String ADD_CLIP_TO_PLAYLIST_QUERY = "INSERT INTO clips_to_playlists(playlist_id, clip_id) VALUES(null,?,?);";
	private static final String CREATE_PLAYLIST_QUERY = "INSERT INTO playlists VALUES(null,?,?,?)";

	// Create Playlist
	@Override
	public int createPlaylist(IPlaylist playlist) throws PlaylistException {
		if (playlist != null) {
			try {
				PreparedStatement stmt = getCon().prepareStatement(CREATE_PLAYLIST_QUERY);
				stmt.setString(2, playlist.getName());
				stmt.setInt(3, 0);
				stmt.setInt(4, ((IUser) playlist.getOwner()).getUserID());
				stmt.executeUpdate();
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
	@Override
	public void addClipToPlaylist(int playlistID, int clipID) throws PlaylistException, ClipException {
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(ADD_CLIP_TO_PLAYLIST_QUERY);
			stmt.setInt(2, playlistID);
			stmt.setInt(3, clipID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Couldn't add clip to playlist");
		}
	}

	@Override
	public void removeClipFromPlaylist(int playlistId, int clipId) throws PlaylistException {
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(REMOVE_CLIP_FROM_PLAYLIST_QUERY);
			stmt.setInt(1, clipId);
			stmt.setInt(2, playlistId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Couldn't remove clip to playlist");
		}
	}

	@Override
	public void increaseViewsOfPlaylist(Playlist playlist) {
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
	@Override
	public IPlaylist getAllClipsForPlaylist(int playlistID) throws PlaylistException {
		// IPlaylist playlist = null;
		try {
			PreparedStatement stmt = getCon().prepareStatement(ALL_CLIPS_QUERY);
			stmt.setInt(1, playlistID);
			ResultSet resultSet = stmt.executeQuery();
			UserDAO user = new UserDAO();
			User owner = user.getUserById(resultSet.getInt(4));
			// tuk tryabva da se selectva state a ne da go podavam;
			// Trqbva li da castvam kym IPlaylist
			IPlaylist playlist = (IPlaylist) new Playlist(resultSet.getString(2), owner, TYPE.PUBLIC);
			ClipDAO clip = new ClipDAO();
			while (resultSet.next()) {
				playlist.addClipToPlaylist(clip.getClipByID(resultSet.getInt(3)));
			}
			return playlist;
		} catch (SQLException | ClipException | UserProblemException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid Playlist ID.");
		}
	}

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
			playlist = new Playlist(playlistId,name, owner, TYPE.PUBLIC);
			playlist.setViewsOfPlaylist(views);
			return playlist;
		} catch (SQLException | UserProblemException | PlaylistException e) {
			e.printStackTrace();
			throw new PlaylistException("Couldn't get playlist!", e);
		}
	}

	// Remove Playlist by ID
	@Override
	public void removePlaylistByID(int playlistID) throws PlaylistException {
		try {
			PreparedStatement stmt = getCon().prepareStatement(DELETE_PLAYLIST_BY_ID_QUERY);
			stmt.setInt(1, playlistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid id for Playlist");
		}
	}

	// Serach Playlists by name
	@Override
	public List<IPlaylist> serachPlaylistByName(String name) throws PlaylistException {
		if (name != null && !name.equals("")) {
			List<IPlaylist> playlistByThisName = new ArrayList<IPlaylist>();
			try {
				PreparedStatement stmt = getCon()
						.prepareStatement("SELECR * FROM playlists whrehe playlist_name LIKE %?%");
				stmt.setString(1, name);
				ResultSet resutlSet = stmt.executeQuery();
				while (resutlSet.next()) {
					playlistByThisName.add((IPlaylist) getPlaylistById(resutlSet.getInt(1)));
				}
				return playlistByThisName;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new PlaylistException("Ivalid name for Playlist");
			}
		} else {
			throw new PlaylistException("Ivalid name for Playlist");
		}
	}

	

	
}
