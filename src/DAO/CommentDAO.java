package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classes.Comment;
import exceptions.CommentException;

public class CommentDAO extends AbstractDAO{
	
	
	public List<Comment> getListMainComments(int clipID) throws CommentException{
		
		try{
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM comments WHERE clip_id=? AND answer_comment_id IS NULL ;");
			
			ps.setInt(1, clipID);
			ResultSet rs=ps.executeQuery();
			List<Comment> mainComments=new ArrayList<Comment>();
			
			while(rs.next()){
				int id=rs.getInt(1);
				int clipsID=rs.getInt(2);
				String description=rs.getString(3);
				Comment comment=new Comment(id, clipsID, description);
				mainComments.add(comment);
			}
			return mainComments;
		}catch(SQLException e){
			e.printStackTrace();
			throw new CommentException("No comments to show! ");
		}
	}
	
	//ADD and REMOVE!!!!!!!!!!!!!!!!!!!!!!!
	
	
	public List<Comment> getListAnswerComments(int clipID,int commentID) throws CommentException{

		try{
			PreparedStatement ps=getCon().prepareStatement("SELECT * FROM comments WHERE clip_id=? AND answer_comment_id= ? ;");
			
			ps.setInt(1, clipID);
			ps.setInt(2, commentID);
			ResultSet rs=ps.executeQuery();
			List<Comment> answerComments=new ArrayList<Comment>();
			
			while(rs.next()){
				int id=rs.getInt(1);
				int clipsID=rs.getInt(2);
				String description=rs.getString(3);
				Comment comment=new Comment(id, clipsID, description);
				answerComments.add(comment);
			}
			return answerComments;
		}catch(SQLException e){
			e.printStackTrace();
			throw new CommentException("No answer comments to show! ");
		}
	}
	
	//ADD and REMOVE!!!!!!!!!!!!!!!!!!!!!!!
	
	

}
