package com.example.interfaces;

import java.util.List;

import com.example.classes.Clip;
import com.example.exceptions.ClipException;
import com.example.exceptions.UserProblemException;

public interface IClipDAO {

	int addClip(Clip clip) throws ClipException;

	void removeClip(int clipID) throws ClipException;

	Clip getClipByID(int clipID) throws ClipException;

	List<Clip> getAllClips() throws ClipException;

	void updateClip(Clip clip) throws UserProblemException, ClipException;

}