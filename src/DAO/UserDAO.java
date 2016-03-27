package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.User;
import exceptions.UserProblemException;

public class UserDAO extends AbstractDAO {
	private static final String ADD_USER_QUERY = "INSERT INTO users (email,password,full_name) VALUES (?,?,?)";	
	private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, password = ?, full_name = ? , picture=?,country_id=?,language_id=?,backgroung_picture=? WHERE id = ?;";	
	
	public int addUser(User user) throws UserProblemException{
		if(user!=null){
		try {
			PreparedStatement ps=getCon().prepareStatement(ADD_USER_QUERY,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getFullName());
			
			ps.executeUpdate();
			ResultSet id=ps.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserProblemException("invalid user");
		}
		}
		else{
			throw new UserProblemException("Invalid user");
		}
	}
	
	public void updateUser(User user) throws UserProblemException{
		if(user!=null){
		  try {
			PreparedStatement ps=getCon().prepareStatement(UPDATE_USER_QUERY);
			
			CountryDAO country=new CountryDAO();
			LanguageDAO language=new LanguageDAO();
			
			ps.setString(1, user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3, user.getFullName());
			ps.setString(4,user.getPicture());
			ps.setInt(5,country.getCountryByName(user.getCountry()));
			ps.setInt(6,language.getLanguageByName(user.getLanguage()));
			ps.setString(7,user.getBackgroundPicture());
			ps.setInt(8, user.getUserID());
			
			ps.executeUpdate();
		} catch (SQLException | UserProblemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UserProblemException("Update failed");
		}
		}
	}
	
	public void removeUser(int userID) throws UserProblemException{
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement("DELETE FROM users WHERE id = ?");
			ps.setInt(1, userID);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new UserProblemException("The driver cannot be deleted right now. Thank you.", e);
		}
	}

}
