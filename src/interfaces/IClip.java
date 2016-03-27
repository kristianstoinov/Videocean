package interfaces;

import classes.Comment;
import exceptions.CommentException;

public interface IClip {

	void addLike();
	
	void removeLike();

	void addDislike();
	
	void removeDislike();

	void addViews();

	void addComment(Comment comment) throws CommentException;

	void removeComment(Comment comment) throws CommentException;

	void removeAnswer(Comment answer, Comment comment) throws CommentException;

	void addAnwer(Comment answer, Comment comment) throws CommentException;

}