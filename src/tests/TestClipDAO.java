package tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import DAO.ClipDAO;
import DAO.UserDAO;
import classes.Category;
import classes.Clip;
import classes.TYPE;
import classes.User;
import exceptions.CategoryException;
import exceptions.ClipException;
import exceptions.UserProblemException;

public class TestClipDAO {

	ClipDAO clipDAO = new ClipDAO();

	@Test
	public void testGetID() throws SQLException, ClipException {
		Clip clip = clipDAO.getClipByID(1);
		assertNotNull(clip);
	}

	@Test
	public void testGetAll() throws SQLException, ClipException {
		List<Clip> clips = clipDAO.getAllClips();
		for (Clip c : clips) {
			assertNotNull(c);
		}
	}
//DA dobavq i UPDATE
	@Test
	public void testAddRemoveClip() throws ClipException, UserProblemException {
		int deleteThis;
		UserDAO user=new UserDAO();
		deleteThis = clipDAO.addClip(new Clip("DA",user.getUserById(1), "URL", TYPE.PUBLIC));
		clipDAO.removeClip(deleteThis);
	}

}
