package com.example.interfaces;

import java.util.List;

import com.example.classes.User;
import com.example.exceptions.UserProblemException;

public interface ISubscriptionFollowersDAO {

	void addSubscriptionFollower(int subscriptionId, int followerId) throws UserProblemException;

	void deleteSubscription(int subscriptionId, int followerId) throws UserProblemException;

	List<User> getSubscriptions(int userId) throws UserProblemException;

	List<User> getFollowers(int userId) throws UserProblemException;

}