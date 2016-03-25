package com.youtube.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Clip {
private final String name;
private final IUser owner;
private int likes;
private int dislikes;
private String category;
private final String clipURL;
private Map<String,List<String>> comments;
private TYPE state;
private final LocalDate datePublished;
private String description;
private int views;
private Statistics statisticsForClip;

public Clip(String name,User owner,String clipURL,TYPE state){
	this.name=name;
	this.owner=owner;
	this.clipURL=clipURL;
	this.state=state;
	this.datePublished=LocalDate.now();
	this.likes=0;
	this.dislikes=0;
	//da dovursha defaultnite stoinosti
	this.category="None";
}

public int getLikes() {
	return likes;
}

public void addLike() {
	this.likes +=1;
}

public int getDislikes() {
	return dislikes;
}

public void addDislike() {
	this.dislikes +=1;
}

public String getCategory() {
	return category;
}
//Validacii
public void setCategory(String category) {
	if(category!=null)
	this.category = category;
}

public TYPE getState() {
	return state;
}

public void setState(TYPE state) {
	this.state = state;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public int getViews() {
	return views;
}

public void setViews(int views) {
	this.views = views;
}

public Statistics getStatisticsForClip() {
	return statisticsForClip;
}

public void setStatisticsForClip(Statistics statisticsForClip) {
	this.statisticsForClip = statisticsForClip;
}

public String getName() {
	return name;
}

public IUser getOwner() {
	return owner;
}

public String getClipURL() {
	return clipURL;
}

public LocalDate getDatePublished() {
	return datePublished;
}







	
	
	
}
