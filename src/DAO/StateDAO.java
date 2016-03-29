package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.TYPE;
import exceptions.ClipException;
import interfaces.IStateDAO;

public class StateDAO extends AbstractDAO implements IStateDAO {

	
	
	@Override
	public TYPE getStateByID(int stateID) throws ClipException{
		TYPE state;
		try {
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM state WHERE state_id = ? ;");
			ps.setInt(1, stateID);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			String stateName = rs.getString(2);
			state = TYPE.valueOf(stateName);		
			return state;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClipException("Can't find a state with ID : " + stateID, e);
		}
		
	}
	
}
