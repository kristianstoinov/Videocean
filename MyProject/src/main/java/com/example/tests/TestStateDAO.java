package com.example.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;

import com.example.DAO.StateDAO;
import com.example.classes.TYPE;
import com.example.exceptions.ClipException;

public class TestStateDAO {
StateDAO stateDao=new StateDAO();

	@Test
	public void testGetID() throws SQLException, ClipException {
		TYPE state = stateDao.getStateByID(1);
		assertNotNull(state);
	}
	
	@Test
	public void testGetName() throws SQLException, ClipException {
		TYPE type= TYPE.PUBLIC;
		int id=stateDao.getStateByName(type);
		assertNotNull(id);
	}



}
