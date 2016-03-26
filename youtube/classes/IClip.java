package com.youtube.classes;

public interface IClip {

	void addLike();
	
	void removeLike();

	void addDislike();
	
	void removeDislike();

	void addViews();

	void addComment(String comment);

	void removeComment(String comment);

	void addAnwer(String answer, String comment);

	void removeAnswer(String answer, String comment);

}