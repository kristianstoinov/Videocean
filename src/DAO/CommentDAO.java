package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Clip;
import classes.Comment;
//NE ZNAM DALI TOZI KLAS NE TRQBVA DA BUDE PRENAPISAN
public class CommentDAO extends AbstractDAO{
	
	//DA PITAME NIKI UTRE
	public Comment getCommentByID(int commentID){
		
		try{
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM comments WHERE comment_id=? ;");
			ps.setInt(1, commentID);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			int id=rs.getInt(1);
			int clipID=rs.getInt(2);
			String description=rs.getString(3);
			
			return new Comment(id, clipID, description);
			
			
		}catch(SQLException e){
			
		}
		//da se popravi! 
		return  null;
	}

}
