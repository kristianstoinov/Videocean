package interfaces;

import java.util.List;

import classes.Clip;
import classes.Playlist;
import classes.User;
import exceptions.ClipException;
import exceptions.PlaylistException;
import exceptions.UserProblemException;

public interface IUser {

	void addClipToMyClips(String name, String clipUrl) throws ClipException, PlaylistException;

	void addClipIntoPlaylist(Playlist playlist, Clip clip);

	void removeClipFromPlaylist(Playlist playlist, Clip clip) throws PlaylistException;

	void makePlaylist(String name) throws PlaylistException;

	void addPlaylist(Playlist playlist);

	void removePlaylist(Playlist playlist) throws PlaylistException;

	void addFollower(User user) throws UserProblemException;

	void addSubscrition(User user) throws UserProblemException;

	void removeFollower(IUser user) throws UserProblemException;

	void removeSubscription(IUser user) throws UserProblemException;

	void removeClipFromMyClips(Clip clip) throws PlaylistException;
	
	int getUserID();

	public void AddClipToHistory(Clip clip);
	
	public List<Clip> getClipsFromHistory();
	
	public List<User> getUsersFromSupscription();
	
	public List<User> getUsersFromFollowers();
	
	public List<Playlist> getPlaylistFromPlaylists();
}