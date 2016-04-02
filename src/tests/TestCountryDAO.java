package tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;
import DAO.CountryDAO;
import exceptions.UserProblemException;

public class TestCountryDAO {

	CountryDAO countryDAO= new CountryDAO();
	
	@Test
	public void testAddCountry() throws SQLException, UserProblemException {
	countryDAO.addCountry("Bulgaria");
	}

	@Test
	public void testGetID() throws SQLException, UserProblemException {
		String country = countryDAO.getCountryById(1);
		assertNotNull(country);
	}
	
	@Test
	public void testGetName() throws SQLException, UserProblemException {
		countryDAO.getCountryByName("Bulgaria");
		
	}
	
	
	
	
	
}
