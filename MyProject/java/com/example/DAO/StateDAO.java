package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.classes.TYPE;
import com.example.exceptions.ClipException;
import com.example.interfaces.IStateDAO;

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
	
	public int getStateByName(TYPE type) throws ClipException{
		try {
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM state WHERE name like ? ;");
			ps.setString(1, type.toString());
			ResultSet rs=ps.executeQuery();
			rs.next();
			int id=rs.getInt(1);		
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClipException("Can't find a state with this name!");
		}
		
	}
	
}
