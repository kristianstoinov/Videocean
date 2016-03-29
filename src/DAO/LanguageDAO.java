package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.UserProblemException;
import interfaces.ILanguageDAO;

public class LanguageDAO extends AbstractDAO implements ILanguageDAO{
	private static final String SELECT_LANGUAGE_BY_ID_QUERY = "SELECT * FROM languages WHERE id = ?";
	private static final String ADD_LANGUAGE_QUERY = "INSERT INTO languages VALUES (null, ?)";
	private static final String SELECT_LANGUAGE_BY_NAME_QUERY = "SELECT * FROM languages WHERE name like ?";

	/* (non-Javadoc)
	 * @see DAO.ILanguage#addLanguage(java.lang.String)
	 */
	@Override
	public int addLanguage(String language) throws UserProblemException {
		if (language != null) {
			try {
				PreparedStatement ps = getCon().prepareStatement(ADD_LANGUAGE_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, language);
				ps.executeUpdate();

				ResultSet id = ps.getGeneratedKeys();
				id.next();
				return id.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new UserProblemException("Can't add a language", e);
			}

		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see DAO.ILanguage#getLanguageById(int)
	 */
	@Override
	public String getLanguageById(int languageId) throws UserProblemException {

		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_LANGUAGE_BY_ID_QUERY);
			ps.setInt(1, languageId);
			ResultSet result = ps.executeQuery();
			result.next();
			String languageName = result.getString(2);

			return languageName;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't find an language with ID : " + languageId, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see DAO.ILanguage#getLanguageByName(java.lang.String)
	 */
	@Override
	public int getLanguageByName(String language) throws UserProblemException {

		try {
			PreparedStatement ps = getCon().prepareStatement(SELECT_LANGUAGE_BY_NAME_QUERY);
			ps.setString(1, language);
			ResultSet result = ps.executeQuery();
			result.next();
			int languageId = result.getInt(1);

			return languageId;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("Can't find an language with this name : " + language, e);
		}
	}
}
