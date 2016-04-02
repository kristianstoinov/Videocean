package tests;

import org.junit.Test;

import DAO.ClipDAO;
import DAO.CommentDAO;
import classes.Clip;
import classes.Comment;
import exceptions.ClipException;
import exceptions.CommentException;
import exceptions.UserProblemException;

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
