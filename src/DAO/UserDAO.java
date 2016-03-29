package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import classes.Admin;
import classes.Clip;
import classes.Playlist;
import classes.User;
import exceptions.ClipException;
import exceptions.PictureFormatException;
import exceptions.PlaylistException;
import exceptions.UserProblemException;
import interfaces.ICountryDAO;
import interfaces.ILanguageDAO;
import interfaces.IUserDAO;

public class UserDAO extends AbstractDAO implements IUserDAO {
	private static final String GET_HISTORY = "SELECT clip_id FROM history WHERE user_id =? ;";
	private static final String DELETE_ALL_CLIPS_OF_USER = "DELETE FROM history WHERE user_id=?;";
	private static final String DELETE_CLIP_FROM_HISTORY = "DELETE FROM history WHERE user_id= ? and clip_id=? ;";
	private static final String ADD_TO_HISTORY = "INSERT INTO history VALUES(null,?,?);";
	private static final String SELECT_COUNT_LIKES_OR_DISLIKES = "SELECT count(user_id) from likes where clip_id=? and preference=?;";
	private static final String DELETE_FROM_LIKES = "DELETE from likes where user_id=?,clip_id=?;";
	private static final String UPDATE_LIKES = "UPDATE likes SET preference=? where user_id=? and clip_id=?";
	private static final String ADD_INTO_LIKES = "INSERT INTO likes VALUES(?,?,?);";
	private static final String SELECT_PLAYLISTS_FROM_LIBRARY = "SELECT playlist_id FROM library where user_id=?;";
	private static final String DELETE_FROM_LIBRARY = "DELETE from library where user_id=? and playlist_id=?;";
	private static final String ADD_INTO_LIBRARY = "INSERT INTO library VALUES(?,?);";
	private static final String SELECT_FROM_USERS_WHERE_FULL_NAME_LIKE = "SELECT * from users where full_name like ?;";
	private static final String SELECT_USER_BY_PASS_AND_EMAIL = "SELECT * from users where email like ? and password like md5(?);";
	private static final String SELECT_FROM_USERS_BY_USER_ID = "SELECT * from users where user_id=?;";
	private static final String DELETE_FROM_USERS_BY_ID = "DELETE FROM users WHERE id = ?;";
	private static final String ADD_USER_QUERY = "INSERT INTO users (email,md5(password),full_name) VALUES (?,?,?);";
	private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, password = md5(?), full_name = ? , picture=?,country_id=?,language_id=?,backgroung_picture=? WHERE id = ?;";

	/* (non-Javadoc)
	 * @see DAO.IUser#addUser(classes.User)
	 */
	@Override
	public int addUser(User user) throws UserProblemException {
		if (user != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_USER_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getFullName());

				ps.executeUpdate();
				ResultSet id = ps.getGeneratedKeys();
				id.next();
				return id.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("invalid user");
			}
		} else {
			throw new UserProblemException("Invalid user");
		}
	}

	/* (non-Javadoc)
	 * @see DAO.IUser#updateUser(classes.User)
	 */
	@Override
	public void updateUser(User user) throws UserProblemException {
		if (user != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(UPDATE_USER_QUERY);

				ICountryDAO country = (ICountryDAO) new CountryDAO();
				ILanguageDAO language = new LanguageDAO();

				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getFullName());
				ps.setString(4, user.getPicture());
				ps.setInt(5, country.getCountryByName(user.getCountry()));
				ps.setInt(6, language.getLanguageByName(user.getLanguage()));
				ps.setString(7, user.getBackgroundPicture());
				ps.setInt(8, user.getUserID());

				ps.executeUpdate();
			} catch (SQLException | UserProblemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("Update failed");
			}
		}
	}

	/* (non-Javadoc)
	 * @see DAO.IUser#removeUser(int)
	 */
	@Override
	public void removeUser(int userID) throws UserProblemException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(DELETE_FROM_USERS_BY_ID);
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new UserProblemException("The driver cannot be deleted right now. Thank you.", e);
		}
	}

	/* (non-Javadoc)
	 * @see DAO.IUser#getUserById(int)
	 */
	@Override
	public User getUserById(int userId) throws UserProblemException {
		PreparedStatement ps;
		if (userId > 0) {
			try {
				ps = getCon().prepareStatement(SELECT_FROM_USERS_BY_USER_ID);
				ps.setInt(1, userId);

				ResultSet rs = ps.executeQuery();
				rs.next();
				return getWantedUser(rs);
			} catch (SQLException | PictureFormatException | UserProblemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("Can't return user.Please try again", e);
			}
		} else {
			throw new UserProblemException("Wrong Id");
		}
	}

	/* (non-Javadoc)
	 * @see DAO.IUser#getUserByEmailAndPass(java.lang.String, java.lang.String)
	 */
	@Override
	public User getUserByEmailAndPass(String email, String password) throws UserProblemException {
		PreparedStatement ps;
		if (email != null && password != null) {
			try {
				ps = getCon().prepareStatement(SELECT_USER_BY_PASS_AND_EMAIL);
				ps.setString(1, email);
				ps.setString(2, password);

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return getWantedUser(rs);
				} else {
					throw new UserProblemException("Wrong email or password");
				}
			} catch (SQLException | PictureFormatException | UserProblemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("Wrong email or password", e);
			}
		} else {
			throw new UserProblemException("Wrong email or password");
		}
	}
	
	/* (non-Javadoc)
	 * @see DAO.IUser#getUsersByName(java.lang.String)
	 */
	@Override
	public List<User> getUsersByName(String name) throws UserProblemException{
		PreparedStatement ps;
		List<User> wantedUsers=new ArrayList<User>();
		if (name!=null) {
			try {
				ps = getCon().prepareStatement(SELECT_FROM_USERS_WHERE_FULL_NAME_LIKE);
				ps.setString(1, "%"+name+"%");

				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				wantedUsers.add(getWantedUser(rs));
				}
				return wantedUsers;
			} catch (SQLException | PictureFormatException | UserProblemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("Can't return user.Please try again", e);
			}
		} else {
			throw new UserProblemException("Wrong Id");
		}
	}

	private User getWantedUser(ResultSet rs) throws SQLException, PictureFormatException, UserProblemException {
		User wantedUser;

		ICountryDAO country = (ICountryDAO) new CountryDAO();
		ILanguageDAO language = (ILanguageDAO) new LanguageDAO();
		int id = rs.getInt(1);
		String email = rs.getString(2);
		String fullName = rs.getString(4);
		String picture = rs.getString(5);
		int countryId = rs.getInt(6);
		int languageId = rs.getInt(7);
		String backgroundPicture = rs.getString(8);
		boolean isAdmin = rs.getBoolean(9);
		if (isAdmin == false) {
			wantedUser = new User(id, email, fullName);
		} else {
			wantedUser = new Admin(id, email, fullName);
		}
		wantedUser.setPicture(picture);
		wantedUser.setCountry(country.getCountryById(countryId));
		wantedUser.setLanguage(language.getLanguageById(languageId));
		wantedUser.setBackgroundPicture(backgroundPicture);
		return wantedUser;
	}
	
	public void addPlaylistIntoLibrary(int playlistId,int userId) throws UserProblemException{
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(ADD_INTO_LIBRARY);
			ps.setInt(1, userId);
			ps.setInt(2,playlistId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't add playlist.Please try again", e);
		}
		}
	
	public void deletePlaylistFromLibrary(int playlistId,int userId) throws UserProblemException{
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(DELETE_FROM_LIBRARY);
			ps.setInt(1,userId);
			ps.setInt(2,playlistId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't remove users playlist",e);
		}
	}
	
	public List<Playlist> getAllPlaylistsForUser(int userId) throws UserProblemException{
		List<Playlist> playlists=new ArrayList<Playlist>();
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(SELECT_PLAYLISTS_FROM_LIBRARY);
			ps.setInt(1,userId);
			
			ResultSet rs=ps.executeQuery();
			PlaylistDAO playlist=new PlaylistDAO();
			while(rs.next()){
				playlists.add(playlist.getPlaylistById(rs.getInt(1)));
			}
			return playlists;
		} catch (SQLException | PlaylistException e) {
			e.printStackTrace();
			throw new UserProblemException("There is a problem.We can't get you back your playlists for now",e);
		}
	}
	
	public void addLike(int userId,int clipId,int preference) throws UserProblemException{
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(ADD_INTO_LIKES);
			ps.setInt(1,userId);
			ps.setInt(2,clipId);
			ps.setInt(3,preference);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Like doesn't insert", e);
		}
	}
	
	public void updateLike(int userId,int clipId,int preference) throws UserProblemException{
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(UPDATE_LIKES);
			ps.setInt(1,preference);
			ps.setInt(2,userId);
			ps.setInt(3,clipId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Like doesn't update", e);
		}
	}
	
	public void deleteLike(int userId,int clipId) throws UserProblemException{
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(DELETE_FROM_LIKES);
			ps.setInt(1,userId);
			ps.setInt(2,clipId);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Like doesn't delete", e);
		}
	}
	
	public int getCountFromLikes(int clipId,int preference) throws UserProblemException{
		PreparedStatement ps;
		int count=0;
		try {
			ps=getCon().prepareStatement(SELECT_COUNT_LIKES_OR_DISLIKES);
			ps.setInt(1,clipId);
			ps.setInt(2,preference);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't count likes/dislikes",e);
		}
	}
	
	public void addClipToHistory(int clipID,int userID) throws UserProblemException
	{
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(ADD_TO_HISTORY);
			ps.setInt(1, userID);
			ps.setInt(2,clipID);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't insert into history", e);
		}
	}
	
	public void deleteClipFromHistory(int clipID,int userID) throws UserProblemException{
		PreparedStatement ps;
		try {
			ps=getCon().prepareStatement(DELETE_CLIP_FROM_HISTORY);
			ps.setInt(1,userID);
			ps.setInt(2,clipID);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't remove clip",e);
		}
	}
	
	public void deleteAllClipsFromHistory(int userID) throws UserProblemException
	{
		PreparedStatement ps;
	try {
		ps=getCon().prepareStatement(DELETE_ALL_CLIPS_OF_USER);
		ps.setInt(1,userID);
		ps.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
		throw new UserProblemException("Can't remove clips",e);
	}
	}
	
	public List<Clip> getHistory(int userID) throws UserProblemException{
	List<Clip> history=new ArrayList<Clip>();
	PreparedStatement ps;
	try {
		ps=getCon().prepareStatement(GET_HISTORY);
		ps.setInt(1,userID);
		
		ResultSet rs=ps.executeQuery();
		ClipDAO clipDAO=new ClipDAO();
		while(rs.next()){
			history.add(clipDAO.getClipByID(rs.getInt(1)));
		}
		return history;
	} catch (SQLException | ClipException e) {
		e.printStackTrace();
		throw new UserProblemException("There is a problem.We can't get your history",e);
	}
	}
	
}
