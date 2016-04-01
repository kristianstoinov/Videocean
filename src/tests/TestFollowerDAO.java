package tests;

import java.sql.SQLException;
import org.junit.Test;
import DAO.SubscriptionFollowerDAO;
import exceptions.UserProblemException;

public class TestFollowerDAO {

	SubscriptionFollowerDAO subDAO = new SubscriptionFollowerDAO();

	@Test
	public void testAddRemoveFollower() throws UserProblemException {
		subDAO.addSubscriptionFollower(1, 2);
		subDAO.deleteSubscription(1, 2);
	}

	// TRQBVA DA OPRAVIM METODITE V DAO-TO
	@Test
	public void testGetAllSubs() throws SQLException {

		// List<Category> categories = categoryDAO.getAllCategories();
		// for (Category c : categories) {
		// assertNotNull(c);
		// }
	}

}
