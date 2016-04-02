package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import classes.Playlist;
import classes.TYPE;
import classes.User;
import exceptions.ClipException;
import exceptions.PlaylistException;
import exceptions.UserProblemException;
import interfaces.IPlaylistDAO;
import interfaces.IUser;

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
			try {
				PreparedStatement stmt = getCon().prepareStatement(CREATE_PLAYLIST_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, playlist.getName());
				stmt.setInt(2, 0);
				stmt.setInt(3, ((IUser) playlist.getOwner()).getUserID());
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

	@Override
	public void addClipToPlaylist(int playlistID, int clipID) throws PlaylistException, ClipException {
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(ADD_CLIP_TO_PLAYLIST_QUERY);
			stmt.setInt(1, playlistID);
			stmt.setInt(2, clipID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void increaseViewsOfPlaylist(Playlist playlist) {
		try {
			PreparedStatement stmt = getCon().prepareStatement(INCREASE_VIEWS_OF_PLAYLIST_QUERY);
			stmt.setInt(1, playlist.getPlaylistID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Playlist getAllClipsForPlaylist(int playlistID) throws PlaylistException {
		try {
			PreparedStatement stmt = getCon().prepareStatement(ALL_CLIPS_QUERY);
			stmt.setInt(1, playlistID);
			ResultSet resultSet = stmt.executeQuery();
			UserDAO user = new UserDAO();
			User owner = user.getUserById(resultSet.getInt(4));
			// tuk tryabva da se selectva state a ne da go podavam;
			Playlist playlist = new Playlist(resultSet.getString(2), owner, TYPE.PUBLIC);
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
			playlist = new Playlist(playlistId, name, owner, TYPE.PUBLIC);
			playlist.setViewsOfPlaylist(views);
			return playlist;
		} catch (SQLException | UserProblemException | PlaylistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PlaylistException("Something got wrong.Please try again later", e);
		}
	}

	@Override
	public void removePlaylistByID(int playlistID) throws PlaylistException {
		try {
			PreparedStatement stmt = getCon().prepareStatement(REMOVE_PLAYLIST_BY_ID_QUERY);
			stmt.setInt(1, playlistID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid id for Playlist");
		}
	}

}
