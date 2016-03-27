package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.State;
import exceptions.ClipException;

public class StateDAO extends AbstractDAO implements IStateDAO {

	
	
	@Override
	public State getStateByID(int stateID) throws ClipException{
		
		try {
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM state WHERE state_id = ? ;");
			ps.setInt(1, stateID);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int id=rs.getInt(1);
			String name=rs.getString(2);
			
			return new State(id,name);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClipException("Can't find a state with ID : " + stateID, e);
		}
		
	}
	
	@Override
	public List<State> getAllStates() throws ClipException{
		List<State> statesList= new ArrayList<State>();
		try{
			Statement statement=getCon().createStatement();
			ResultSet rs=statement.executeQuery("SELECT * FROM state ;");
			
			while(rs.next()){
				State state=new State(rs.getInt(1),rs.getString(2));
				statesList.add(state);
			}
			return statesList;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ClipException("No states found",e);
		}
		
	}
	
	
}
