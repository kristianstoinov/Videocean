package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UserProblemException;

public class CountryDAO extends AbstractDAO {
	private static final String SELECT_COUTRY_BY_ID_QUERY = "SELECT * FROM countries WHERE id = ?";
	private static final String ADD_COUNTRY_QUERY = "INSERT INTO countries VALUES (null, ?)";
	private static final String SELECT_COUTRY_BY_NAME_QUERY = "SELECT * FROM countries WHERE country_name like ?";

	public int addCountry(String country) throws UserProblemException {
		if (country != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_COUNTRY_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, country);
				ps.executeUpdate();

				ResultSet id = ps.getGeneratedKeys();
				id.next();
				return id.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("Can't add a country", e);
			}

		}
		return 0;
	}

	public String getCountryById(int countryId) throws UserProblemException {

		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_COUTRY_BY_ID_QUERY);
			ps.setInt(1, countryId);
			ResultSet result = ps.executeQuery();
			result.next();
			String country_name = result.getString(2);

			return country_name;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't find an country name with ID : " + countryId, e);
		}
	}

	public int getCountryByName(String countryName) throws UserProblemException {

		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_COUTRY_BY_NAME_QUERY);
			ps.setString(1, countryName);
			ResultSet result = ps.executeQuery();
			result.next();
			int countryId = result.getInt(1);

			return countryId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't find an country with this name : " + countryName, e);
		}
	}

}