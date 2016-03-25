package pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
private String username;
private String password;
private String fullName;
private String picture;
private String backGroundPicture;
private String country;
private String language;
private List<Clip> history;
private Playlist myClips;
private List<Playlist> playlists;
private boolean isVerified;
private List<User> followers;
private List<User> subscriptions;

public User(String username,String password,String fullName){
	setUsername(username);
	setPassword(password);
	setFullName(fullName);
	playlists=new ArrayList<Playlist>();
    history=new ArrayList<Clip>();
    followers=new ArrayList<User>();
    subscriptions=new ArrayList<User>();
	
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFullName() {
	return fullName;
}

public void setFullName(String fullName) {
	this.fullName = fullName;
}

public String getPicture() {
	return picture;
}

public void setPicture(String picture) {
	this.picture = picture;
}

public String getBackGroundPicture() {
	return backGroundPicture;
}

public void setBackGroundPicture(String backGroundPicture) {
	this.backGroundPicture = backGroundPicture;
}

public String getCountry() {
	return country;
}

public void setCountry(String country) {
	this.country = country;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}

public boolean isVerified() {
	return isVerified;
}

public void setVerified(boolean isVerified) {
	this.isVerified = isVerified;
}

public Playlist getMyClips() {
	return myClips;
}

public void setMyClips(Playlist myClips) {
	this.myClips = myClips;
}








}
