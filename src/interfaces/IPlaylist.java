package interfaces;

import classes.Clip;
import classes.TYPE;
import exceptions.PlaylistException;

public interface IPlaylist {

	void addClipToPlaylist(Clip clip);

	void removeClipFromPlaylist(Clip clip) throws PlaylistException;

	void changeState(TYPE state);

	void increaseViewsOfPlaylist();

}