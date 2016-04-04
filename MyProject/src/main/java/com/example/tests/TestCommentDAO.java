package com.example.tests;

import org.junit.Test;

import com.example.DAO.ClipDAO;
import com.example.DAO.CommentDAO;
import com.example.classes.Clip;
import com.example.classes.Comment;
import com.example.exceptions.ClipException;
import com.example.exceptions.CommentException;
import com.example.exceptions.UserProblemException;

public class TestCommentDAO {

	CommentDAO commentDAO = new CommentDAO();

	@Test
	public void testAddDeleteComment() throws UserProblemException, CommentException, ClipException {
		int forDelete;
		Clip clip = new ClipDAO().getClipByID(1);
		Comment comment = new Comment(1, clip, "hello world");
		Comment answerComment = new Comment(10, clip, "mello world");
		forDelete = commentDAO.addComment(comment);
		int toDelete = commentDAO.addAnswerComment(answerComment, forDelete);
		commentDAO.removeComent(toDelete);
		commentDAO.removeComent(forDelete);

	}

}
