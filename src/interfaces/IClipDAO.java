package interfaces;

import java.util.List;

import classes.Clip;
import exceptions.ClipException;

public interface IClipDAO {

	int addClip(Clip clip) throws ClipException;

	void removeClip(int clipID) throws ClipException;

	Clip getClipByID(int clipID) throws ClipException;

	List<Clip> getAllClips() throws ClipException;

}