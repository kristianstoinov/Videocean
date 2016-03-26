package interfaces;

import exceptions.CommentException;

public interface IClip {

	void addLike();
	
	void removeLike();

	void addDislike();
	
	void removeDislike();

	void addViews();

	void addComment(String comment);

	void removeComment(String comment) throws CommentException;

	void addAnwer(String answer, String comment) throws CommentException;

	void removeAnswer(String answer, String comment) throws CommentException;

}