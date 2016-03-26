package com.youtube.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Clip implements IClip {
private final String name;
private final IUser owner;
private int likes;
private int dislikes;
private String category;
private final String clipURL;

//private List<Comment> comments;
private Map<String,List<String>> comments;
private TYPE state;
private final LocalDate datePublished;
private String description;
private int views;
private Statistics statisticsForClip;

public Clip(String name,IUser owner,String clipURL,TYPE state){
	if(name!=null && owner!=null && clipURL!=null){
	this.name=name;
	this.owner=owner;
	this.clipURL=clipURL;
	this.state=state;
	this.datePublished=LocalDate.now();
	this.likes=0;
	this.dislikes=0;
	this.category="None";
	}else{
		throw new ClipException("Invalid arguments!");
	}
}

public int getLikes() {
	return likes;
}

@Override
public void addLike() {
	this.likes +=1;
}
public void removeLike() {
	this.likes -=1;
}

public int getDislikes() {
	return dislikes;
}

@Override
public void addDislike() {
	this.dislikes +=1;
}

public void removeDislike() {
	this.dislikes -=1;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	if(category!=null)
	this.category = category;
}

public TYPE getState() {
	return state;
}

public void setState(TYPE state) {
	if(state!=null)
	this.state = state;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	if(description!=null)
	this.description = description;
}

public int getViews() {
	return views;
}


@Override
public void addViews() {
	if(views>0)
	this.views++;
}

public Statistics getStatisticsForClip() {
	return statisticsForClip;
}

public void setStatisticsForClip(Statistics statisticsForClip) {
	if(statisticsForClip!=null)
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



@Override
public void addComment(String comment){
	if(comment!=null){
	List<String> answerComments=new LinkedList<String>();
	comments.put(comment, answerComments);
	}
}

@Override
public void removeComment(String comment){
	if(comment!=null){
	if(comments.containsKey(comment)){
		comments.remove(comment);
	}else{
		throw new CommentException("No such comment! Please rethink!");
	}
	}
}


@Override
public void addAnwer(String answer,String comment){
	if(comment!=null && answer!=null){
	if(comments.containsKey(comment)){
	List<String> answerComments=comments.get(comment);
	answerComments.add(answer);
	comments.put(comment, answerComments);
	}else{
		throw new CommentException("No such comment! Please rethink!");
	}
	}
}

	@Override
	public void removeAnswer(String answer,String comment){
		if(comment!=null && answer!=null){
		if(comments.containsKey(comment)){
		List<String> answerComments=comments.get(comment);
		if(answerComments.contains(answer)){
			answerComments.remove(answer);
		}
		comments.put(comment, answerComments);
	}
		else{
			throw new CommentException("No such comment! Please rethink!");
		}
	}
	}
	
	
}
