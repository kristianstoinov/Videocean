package interfaces;

import java.util.List;

import exceptions.UserProblemException;

public interface ISubscriptionFollowersDAO {

	void addSubscriptionFollower(int subscriptionId, int followerId);

	void deleteSubscription(int subscriptionId, int followerId) throws UserProblemException;

	List<Integer> getSubscriptions(int userId);

	List<Integer> getFollowers(int userId);

}