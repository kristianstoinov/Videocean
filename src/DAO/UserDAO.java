package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.User;
import exceptions.PictureFormatException;
import exceptions.UserProblemException;

public class UserDAO extends AbstractDAO implements IUserDAO {
	private static final String SELECT_FROM_USERS_WHERE_FULL_NAME_LIKE = "SELECT * from users where full_name like ?;";
	private static final String SELECT_USER_BY_PASS_AND_EMAIL = "SELECT * from users where email like ? and password like ?;";
	private static final String SELECT_FROM_USERS_BY_USER_ID = "SELECT * from users where user_id=?;";
	private static final String DELETE_FROM_USERS_BY_ID = "DELETE FROM users WHERE id = ?;";
	private static final String ADD_USER_QUERY = "INSERT INTO users (email,password,full_name) VALUES (?,?,?);";
	private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, password = ?, full_name = ? , picture=?,country_id=?,language_id=?,backgroung_picture=? WHERE id = ?;";

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

				ICountryDAO country = new CountryDAO();
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
				return getData(rs);
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
					return getData(rs);
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
				ps.setString(1, name);

				ResultSet rs = ps.executeQuery();
				while(rs.next()){
				wantedUsers.add(getData(rs));
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

	private User getData(ResultSet rs) throws SQLException, PictureFormatException, UserProblemException {
		User wantedUser;

		ICountryDAO country = new CountryDAO();
		ILanguageDAO language = new LanguageDAO();
		int id = rs.getInt(1);
		String email = rs.getString(2);
		String password = rs.getString(3);
		String fullName = rs.getString(4);
		String picture = rs.getString(5);
		int countryId = rs.getInt(6);
		int languageId = rs.getInt(7);
		String backgroundPicture = rs.getString(8);
		boolean isAdmin = rs.getBoolean(9);
		if (isAdmin == false) {
			wantedUser = new User(id, email, password, fullName);
			wantedUser.setPicture(picture);
			wantedUser.setCountry(country.getCountryById(countryId));
			wantedUser.setLanguage(language.getLanguageById(languageId));
			wantedUser.setBackgroundPicture(backgroundPicture);
		} else {
			wantedUser = new Admin(id, email, password, fullName);
			wantedUser.setPicture(picture);
			wantedUser.setCountry(country.getCountryById(countryId));
			wantedUser.setLanguage(language.getLanguageById(languageId));
			wantedUser.setBackgroundPicture(backgroundPicture);
		}
		return wantedUser;
	}
}
