package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.classes.Comment;
import com.example.exceptions.CommentException;

public class CommentDAO extends AbstractDAO{
	
	
	private static final String ADD_ANSWER_COMMENT = "INSERT INTO comments VALUES(NULL,?,?,?);";
	private static final String ADD_MAIN_COMMENT = "INSERT INTO comments VALUES(NULL,?,?,NULL);";
	private static final String SELECT_MAIN_COMMENT = "SELECT * FROM comments WHERE clip_id=? AND answer_comment_id IS NULL ;";
	private static final String SELECT_ASNWER_COMMENTS = "SELECT * FROM comments WHERE clip_id=? AND answer_comment_id= ? ;";
	private static final String DELETE_COMMENT = "DELETE FROM comments WHERE comment_id = ?;";

	public List<Comment> getListMainComments(int clipID) throws CommentException{
		
		try{
			PreparedStatement ps=getCon().prepareStatement(SELECT_MAIN_COMMENT);
			
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
	
	
	
	
	public List<Comment> getListAnswerComments(int clipID,int commentID) throws CommentException{

		try{
			PreparedStatement ps=getCon().prepareStatement(SELECT_ASNWER_COMMENTS);
			
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
	
	public void removeComent(int commentId) throws CommentException {
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(DELETE_COMMENT);
			ps.setInt(1, commentId);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new CommentException("Problem with comment removing", e);
		}
	}
	
	public int addComment(Comment comment) throws CommentException{
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(ADD_MAIN_COMMENT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, comment.getThisClip().getClipID());
			ps.setString(2, comment.getCommentDescription());

			ps.executeUpdate();
			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommentException("Can't add main comment", e);
		}
	}
	
	public int addAnswerComment(Comment comment,int mainCommentId) throws CommentException{
		PreparedStatement ps;
		try {
			ps = getCon().prepareStatement(ADD_ANSWER_COMMENT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, comment.getThisClip().getClipID());
			ps.setString(2, comment.getCommentDescription());
			ps.setInt(3,mainCommentId);

			ps.executeUpdate();
			ResultSet id = ps.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CommentException("Can't add answer comment", e);
		}
	}
	
	
	
	

}
