package tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;
import DAO.StateDAO;
import classes.TYPE;
import exceptions.ClipException;

public class TestStateDAO {
StateDAO stateDao=new StateDAO();

	@Test
	public void testGetID() throws SQLException, ClipException {
		TYPE state = stateDao.getStateByID(1);
		assertNotNull(state);
	}



}
