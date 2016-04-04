package com.example.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.example.DAO.CategoryDAO;
import com.example.DAO.ClipDAO;
import com.example.DAO.UserDAO;
import com.example.classes.Category;
import com.example.classes.Clip;
import com.example.classes.TYPE;
import com.example.classes.User;
import com.example.exceptions.CategoryException;
import com.example.exceptions.ClipException;
import com.example.exceptions.UserProblemException;

public class TestClipDAO {

	ClipDAO clipDAO = new ClipDAO();

	@Test
	public void testGetID() throws SQLException, ClipException, UserProblemException, CategoryException {
		Clip clip = clipDAO.getClipByID(1);
		assertNotNull(clip);
		clip.setClipID(1);
		clip.setDescription("wooooow");
		clip.setViews(10000);
		clip.setCategory(new CategoryDAO().getCategoryByID(1));
		clipDAO.updateClip(clip);
	}

	@Test
	public void testGetAll() throws SQLException, ClipException {
		List<Clip> clips = clipDAO.getAllClips();
		for (Clip c : clips) {
			assertNotNull(c);
		}
	}

	@Test
	public void testAddRemoveClip() throws ClipException, UserProblemException {
		int deleteThis;
		UserDAO user = new UserDAO();
		deleteThis = clipDAO.addClip(new Clip("DA", user.getUserById(1), "URL", TYPE.PUBLIC));
		clipDAO.removeClip(deleteThis);
	}



}
