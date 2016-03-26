package com.youtube.classes;

public interface IClip {

	void addLike();

	void addDislike();

	void addViews();

	void addComment(String comment);

	void removeComment(String comment);

	void addAnwer(String answer, String comment);

	void removeAnswer(String answer, String comment);

}