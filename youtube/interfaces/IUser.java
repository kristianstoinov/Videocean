package pojo;

public interface IUser {

	void addClipToMyClips(String name, String clipUrl);

	void removeClipFromMyClips(String url) throws PlaylistException;

	void addClipIntoPlaylist(Playlist playlist, Clip clip);

	void removeClipFromPlaylist(Playlist playlist, String url) throws PlaylistException;

	void makePlaylist(String name) throws PlaylistException;

	void addPlaylist(Playlist playlist);

	void removePlaylist(Playlist playlist) throws PlaylistException;

	void addFollower(User user) throws UserProblemException;

	void addSubscrition(User user) throws UserProblemException;

	void removeFollower(IUser user) throws UserProblemException;

	void removeSubscription(IUser user) throws UserProblemException;

}