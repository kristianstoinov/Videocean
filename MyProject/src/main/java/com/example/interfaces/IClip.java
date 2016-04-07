package com.example.interfaces;

import com.example.classes.Comment;
import com.example.exceptions.CommentException;

public interface IClip {

	void addLike();
	
	void removeLike();

	void addDislike();
	
	void removeDislike();

	void addViews();

	void addComment(Comment comment) throws CommentException;

	void removeComment(Comment comment) throws CommentException;

	void removeAnswer(Comment answer, Comment comment) throws CommentException;



	void addAnswer(Comment answer, Comment comment) throws CommentException;

}