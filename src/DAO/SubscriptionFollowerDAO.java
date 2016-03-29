package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.UserProblemException;
import interfaces.ISubscriptionFollowersDAO;

public class SubscriptionFollowerDAO extends AbstractDAO implements ISubscriptionFollowersDAO {
	private static final String ADD_FOLLOWER_QUERY = "INSERT INTO followers VALUES (?, ?)";
	private static final String DELETE_FOLLOWER_QUERY = "DELETE FROM followers WHERE user_id= ? and follower_id=?";
	private static final String SELECT_SUBSCRIPTIONS_QUERY = "SELECT follower_id from followers where user_id=?";
	private static final String SELECT_FOLLWERS_QUERY = "SELECT user_id from followers where follower_id=?";

	/* (non-Javadoc)
	 * @see DAO.ISubscriptionFollowersDAO#addSubscriptionFollower(int, int)
	 */
	@Override
	public void addSubscriptionFollower(int subscriptionId, int followerId) throws UserProblemException {
		if (subscriptionId > 0 && followerId > 0) {
			if (subscriptionId != followerId) {
				try {
					PreparedStatement ps = getCon().prepareStatement(ADD_FOLLOWER_QUERY);
					ps.setInt(1, subscriptionId);
					ps.setInt(2, followerId);

					ps.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new UserProblemException("There is no compozition with this subscription and this follower", e);
				}

			}
		}
	}

	
	@Override
	public void deleteSubscription(int subscriptionId, int followerId) throws UserProblemException {
		try {
			PreparedStatement ps = getCon().prepareStatement(DELETE_FOLLOWER_QUERY);
			ps.setInt(1, subscriptionId);
			ps.setInt(2, followerId);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new UserProblemException("There is no compozition with this subscription and this follower", e);
		}
	}

	@Override
	public List<Integer> getSubscriptions(int userId) {
		return getFollowersOrSubscriptions(userId, SELECT_SUBSCRIPTIONS_QUERY);
	}

	@Override
	public List<Integer> getFollowers(int userId) {
		return getFollowersOrSubscriptions(userId, SELECT_FOLLWERS_QUERY);
	}

	private List<Integer> getFollowersOrSubscriptions(int userId, String query) {
		PreparedStatement ps;
		List<Integer> subscriptionsId = new ArrayList<Integer>();
		try {
			ps = getCon().prepareStatement(query);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subscriptionsId.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subscriptionsId;
	}

}
