package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import classes.Clip;
import classes.User;
import exceptions.ClipException;
import interfaces.IClipDAO;

public class ClipDAO extends AbstractDAO implements IClipDAO {


	private static final String DELETE_CLIP = "DELETE FROM clips WHERE clip_id=? ;";
	private static final String ADD_CLIP = "INSERT INTO clips(clip_id,clip_name,owner_id,state_id,date_published,category_id) VALUES(null,?,?,1,LocalDate.now(),?);";

	@Override
	public int addClip(Clip clip) throws ClipException{
		if(clip!=null){
		  try{
			  PreparedStatement ps=getCon().prepareStatement(ADD_CLIP,PreparedStatement.RETURN_GENERATED_KEYS);
			  ps.setString(1,clip.getName());
			  ps.setInt(2, clip.getOwner().getUserID());
			  ps.setString(3, clip.getCategory());
			  
			  ps.executeUpdate();
			  
			  ResultSet id=ps.getGeneratedKeys();
			  id.next();
			  return id.getInt(1);
		  }catch(SQLException e){
			  e.printStackTrace();
			  throw new ClipException("You cannot add this clip!",e);
		  }
		}else{
			return 0;
		}
		
	}
	
	@Override
	public void removeClip(int clipID) throws ClipException{
		try{
			PreparedStatement ps=getCon().prepareStatement(DELETE_CLIP);
			ps.setInt(1, clipID);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ClipException("Cannot remove this clip with ID: " + clipID,e);
		}
	}
	
	@Override
	public Clip getClipByID(int clipID) throws ClipException{
		//da se dovurshi tozi metod 
		try{
			PreparedStatement ps= getCon().prepareStatement("SELECT * FROM clips WHERE clip_id = ? ;");
			ps.setInt(1, clipID);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int id=rs.getInt(1);
			String clipName=rs.getString(2);
			int clipOwnerID = rs.getInt(3);
			String clipPath=rs.getString(4);
			int clipState=rs.getInt(5);
			
			Date datePublished=rs.getDate(6);
			//tuka mu podavam greshni parametri ama ne znam kak da go opravq
//			Clip clip=new Clip(id, clipName, clipOwnerID, clipPath, clipState);
//			return clip;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ClipException("Such clip doesn't exist");
		}
		//da se popravi 
		
		return null;
		
	}
	
	@Override
	public List<Clip> getAllClips() throws ClipException{
		List<Clip> allClips=new ArrayList<Clip>();
		try{
			Statement statement = getCon().createStatement();
			ResultSet rs=statement.executeQuery("SELECT * FROM clips");
			
			while(rs.next()){
//				Clip clip=new Clip(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
//				allClips.add(clip);
			}
			return allClips;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ClipException("No clips found!");
		}
		
	}

}
