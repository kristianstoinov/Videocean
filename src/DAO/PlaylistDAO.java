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

	private static final String SELECT_FROM_PLAYLISTS = "SELECT * FROM playlists where playlist_id=?";
	private static final String SELECT_PLAYLIST_BY_ID = "SELECT * FROM clips_to_playlists WHERE playlist_id=?";
	private static final String ALL_CLIPS_QUERY = "SELECT * FROM clips_to_playlists WHERE playlist_id= ? ;";
	private static final String INCREASE_VIEWS_OF_PLAYLIST_QUERY = "UPDATE  playlists SET  playlist_views = playlist_views + 1  WHERE id = ? ;";
	private static final String REMOVE_CLIP_FROM_PLAYLIST_QUERY = "DELETE FROM clips_to_playlists WHERE clip_id=? AND playlist_id=?;";
	private static final String ADD_CLIP_TO_PLAYLIST_QUERY = "INSERT INTO clips_to_playlists(playlist_id, clip_id) VALUES(null,?,?);";
	private static final String CREATE_PLAYLIST_QUERY = "INSERT INTO playlists VALUES(null,?,?,?)";

	// Create Playlist
	@Override
	public int createPlaylist(IPlaylist playlist) throws PlaylistException {
		if (playlist != null) {
			String insertSQL = CREATE_PLAYLIST_QUERY;
			try {
				PreparedStatement stmt = getCon().prepareStatement(insertSQL);
				stmt.setString(2, playlist.getName());
				stmt.setInt(3, 0);
				stmt.setInt(4,  ((IUser) playlist.getOwner()).getUserID());
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
	@Override
	public void removeClipFromPlaylist(int playlistId, int clipId)
			throws PlaylistException, ClipException, SQLException {
		PreparedStatement stmt = getCon().prepareStatement(REMOVE_CLIP_FROM_PLAYLIST_QUERY);
		stmt.setInt(1, clipId);
		stmt.setInt(2, playlistId);
	}

	// Increase Views of playlist by playlist ID
	@Override
	public void increaseViewsOfPlaylist(Playlist playlist) {
		String insertSQL = INCREASE_VIEWS_OF_PLAYLIST_QUERY;
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(INCREASE_VIEWS_OF_PLAYLIST_QUERY);
			stmt.setInt(1, playlist.getPlaylistID());
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Return list of ID of clips from playlist
	@Override
	public List<Integer> AllClips(int playlistID) throws PlaylistException {
		List allClips = new ArrayList<Clip>();
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement(ALL_CLIPS_QUERY);
			stmt.setInt(1, playlistID);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				allClips.add(resultSet.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PlaylistException("Invalid Playlist ID.");
		}
		return allClips;
	}

	// Returns a list of IDs of Playlist by User ID
	@Override
	public List<Integer> allPlayListForUser(int userID) throws PlaylistException {
		List<Integer> allPlayListForUser = new ArrayList<Integer>();
		PreparedStatement stmt;
		try {
			stmt = getCon().prepareStatement("SELECT * FROM library WHERE user_id=?");
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
	
	public List<Integer> playlistByID(int playlistID){
			List<Integer> clipsInThisPlaylist = new ArrayList<Integer>();
		try {
			PreparedStatement stmt = getCon().prepareStatement(SELECT_PLAYLIST_BY_ID);
			stmt.setInt(1, playlistID);
			ResultSet resultSet = stmt.executeQuery();
			resultSet = stmt.executeQuery();
			while(resultSet.next()){
				clipsInThisPlaylist.add(resultSet.getInt(3));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clipsInThisPlaylist;
		
		}
	
	public Playlist getPlaylistById(int playlistId) throws PlaylistException{//metoda vrashta playlist po ID ot tablicata s playlists
		PreparedStatement ps;
		Playlist playlist;
		try {
			ps=getCon().prepareStatement(SELECT_FROM_PLAYLISTS);
			ps.setInt(1,playlistId);
			
			ResultSet rs=ps.executeQuery();
			UserDAO user=new UserDAO();
			rs.next();
			String name=rs.getString(2);
			int views=rs.getInt(3);
			User owner=user.getUserById(rs.getInt(4));
			playlist=new Playlist(name,owner ,TYPE.PUBLIC);// tuk tryabva da se selectva state a ne da go podavam;
			return playlist;
		} catch (SQLException | UserProblemException | PlaylistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PlaylistException("Something got wrong.Please try again later",e);
		}
	}

}
