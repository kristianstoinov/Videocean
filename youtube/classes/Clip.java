package com.youtube.classes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Clip {
private String name;
private IUser owner;
private int likes;
private int dislikes;
private String category;
private String clipURL;
private Map<String,List<String>> comments;
private TYPE state;
//public,private,hidden
//private Map<String,List<String>> 
private LocalDate datePublished;
private String description;
private int views;
private Statistics statisticsForClip;

public Clip(String name,User owner,String clipURL,TYPE state){
	this.name=name;
	this.owner=owner;
	this.clipURL=clipURL;
	this.state=state;
	this.datePublished=LocalDate.now();

}



	
	
	
}
