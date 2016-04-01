package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import DAO.ClipDAO;
import DAO.PlaylistDAO;
import DAO.UserDAO;
import classes.Clip;
import classes.Playlist;
import classes.TYPE;
import classes.User;
import exceptions.ClipException;
import exceptions.PlaylistException;
import exceptions.UserProblemException;
import interfaces.IPlaylist;
import interfaces.IPlaylistDAO;

public class TestPlaylistDAO {

	@Test
	public void creatPlaylist() throws UserProblemException, PlaylistException {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserById(2);
		IPlaylistDAO playlistDAO = new PlaylistDAO();
		Playlist playlist = new Playlist("MoqPlaylist", user, TYPE.PUBLIC);
		int id = playlistDAO.createPlaylist(playlist);
		playlistDAO.removePlaylistByID(id);
	}

	@Test
	public void addOrRemoveClipToPlayList() throws ClipException, PlaylistException {
		ClipDAO clipDAO = new ClipDAO();
		Clip clip = clipDAO.getClipByID(2);
		PlaylistDAO playlistDAO = new PlaylistDAO();
		playlistDAO.addClipToPlaylist(10, clip.getClipID());
		playlistDAO.removeClipFromPlaylist(10, 2);
	}
	@Test
	public void increaseViewsOfPlaylist() throws PlaylistException{
		IPlaylistDAO playlistDAO = new PlaylistDAO();
		Playlist playlist = playlistDAO.getPlaylistById(10);
		playlistDAO.increaseViewsOfPlaylist(playlist);
	}
	

}
