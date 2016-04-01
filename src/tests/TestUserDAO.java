import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;



import com.mysql.jdbc.Connection;

import DAO.AbstractDAO;
import DAO.DBConnection;
import DAO.UserDAO;
import classes.User;
import exceptions.PictureFormatException;
import exceptions.UserProblemException;

public class TestUserDAO extends AbstractDAO{
UserDAO user=new UserDAO();
	@Test
    public void testConnection() {
	java.sql.Connection con=DBConnection.getInstance().getConnection();
	assertNotNull(con);
	}
	
	@Test
	public void testAddUser() throws UserProblemException {
	int forDelete;
	forDelete=user.addUser(new User("mrayan@abv.bg", "123456789", "trayan muchev"));
	user.removeUser(forDelete);
	}
	
	@Test
	public void testUpdateUser() throws UserProblemException, PictureFormatException {
		User us=new User(1, "tdra@abv.bg", "stoina");
		us.setPassword("1232445436");
		us.setLanguage("ENG");
		us.setBackgroundPicture("neshto.jpg");
		us.setPicture("picture.jpg");
		user.updateUser(us);
	}
	
	@Test
	public void testGetUserById() throws UserProblemException, SQLException {
		User us=user.getUserById(1);
		assertNotNull(us);
		}
	
	@Test
	public void testEmailAndName() throws UserProblemException
	{
		User user1=user.getUserByEmailAndPass("tdra@abv.bg", "1232445436");
		assertNotNull(user1);
	}
	@Test
	public void testFullName() throws UserProblemException
	{
		List<User> users=user.getUsersByName("stoina");
		for(User u:users){
			assertNotNull(u);
		}
	}
	
	@Test
	public void testAllUsers() throws UserProblemException
	{
		List<User> users=user.getAllUsers();
		for(User u:users){
			assertNotNull(u);
		}
	}
	
	@Test
	public void testAddPlaylist() throws UserProblemException
	{
		user.addPlaylistIntoLibrary(1, 1);
		user.deletePlaylistFromLibrary(1, 1);
	}
	
	
	
}
