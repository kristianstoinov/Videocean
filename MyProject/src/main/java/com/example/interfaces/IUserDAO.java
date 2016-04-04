package com.example.interfaces;

import java.util.List;

import com.example.classes.User;
import com.example.exceptions.UserProblemException;

public interface IUserDAO {

	int addUser(User user) throws UserProblemException;

	void updateUser(User user) throws UserProblemException;

	void removeUser(int userID) throws UserProblemException;

	User getUserById(int userId) throws UserProblemException;

	User getUserByEmailAndPass(String email, String password) throws UserProblemException;

	List<User> getUsersByName(String name) throws UserProblemException;

}