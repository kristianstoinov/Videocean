package com.example.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.classes.Category;
import com.example.classes.Clip;
import com.example.exceptions.CategoryException;
import com.example.exceptions.ClipException;
import com.example.exceptions.UserProblemException;
import com.example.interfaces.IClipDAO;

public class ClipDAO extends AbstractDAO implements IClipDAO {

	private static final String UPDATE_CLIP = "UPDATE clips SET state_id=?, description=? , views=? , category_id=?  WHERE clip_id=?";
	private static final String SELECT_CLIP_BY_ID = "SELECT * FROM clips WHERE clip_id = ? ;";
	private static final String DELETE_CLIP = "DELETE FROM clips WHERE clip_id=? ;";
	private static final String ADD_CLIP = "INSERT INTO clips(clip_id,clip_name,owner_id,clip_path,state_id,date_published,category_id) VALUES(null,?,?,?,1,CURDATE(),?);";
	private static final String SELECT_FROM_CLIPS_BY_CONTAINED_STRING_IN_NAME = "SELECT * FROM clips WHERE state_id=1 AND clip_name LIKE ?";

	@Override
	public void updateClip(Clip clip) throws UserProblemException, ClipException {
		PreparedStatement ps = null;
		if (clip != null) {
			try {
				ps = getCon().prepareStatement(UPDATE_CLIP);

				ps.setInt(1, new StateDAO().getStateByName(clip.getState()));
				ps.setString(2, clip.getDescription());
				ps.setInt(3, clip.getViews());
				ps.setInt(4, clip.getCategory().getCategoryID());
				ps.setInt(5, clip.getClipID());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new UserProblemException("Update failed");
			} finally {
				try {
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public int addClip(Clip clip) throws ClipException {
		PreparedStatement ps = null;
		ResultSet id = null;
		if (clip != null) {
			try {
				ps = getCon().prepareStatement(ADD_CLIP, PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setString(1, clip.getName());
				ps.setInt(2, clip.getOwner().getUserID());
				ps.setString(3, clip.getClipURL());
				if (clip.getCategory() != null) {
					ps.setInt(4, clip.getCategory().getCategoryID());
				} else {
					ps.setInt(4, 1);
				}
				ps.executeUpdate();

				id = ps.getGeneratedKeys();
				id.next();
				return id.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ClipException("You cannot add this clip!", e);
			} finally {
				try {
					if (ps != null)
						ps.close();
					if (id != null)
						id.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			throw new ClipException("You cannot add this clip!");
		}

	}

	@Override
	public void removeClip(int clipID) throws ClipException {
		PreparedStatement ps = null;
		try {
			ps = getCon().prepareStatement(DELETE_CLIP);
			ps.setInt(1, clipID);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ClipException("Cannot remove this clip with ID: " + clipID, e);
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Clip getClipByID(int clipID) throws ClipException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement(SELECT_CLIP_BY_ID);
			ps.setInt(1, clipID);
			rs = ps.executeQuery();
			rs.next();
			int id = rs.getInt(1);
			String clipName = rs.getString(2);
			int clipOwnerID = rs.getInt(3);
			String clipPath = rs.getString(4);
			int clipState = rs.getInt(5);
			Date datePublished = rs.getDate(6);
			String description = rs.getString(7);
			int views = rs.getInt(8);
			int categoryID = rs.getInt(9);

			CategoryDAO categoryDao = new CategoryDAO();
			UserDAO userDao = new UserDAO();
			StateDAO stateDao = new StateDAO();
			String categoryName = (String) (categoryDao.getCategoryByID(categoryID).getName());
			Clip clip = new Clip(id, clipName, userDao.getUserById(clipOwnerID), clipPath,
					stateDao.getStateByID(clipState));
			clip.setCategory(new Category(categoryID, categoryName));
			clip.setDescription(description);
			clip.setViews(views);
			// clip.setDatePublished(datePublished);
			return clip;
		} catch (SQLException | UserProblemException | CategoryException e) {
			e.printStackTrace();
			throw new ClipException("Such clip doesn't exist");
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Clip> getAllClips() throws ClipException {
		List<Clip> allClips = new ArrayList<Clip>();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = getCon().createStatement();
			rs = statement.executeQuery("SELECT * FROM clips  WHERE state_id=1 LIMIT 20");
			UserDAO userDao = new UserDAO();
			StateDAO stateDao = new StateDAO();
			while (rs.next()) {
				Clip clip = new Clip(rs.getInt(1), rs.getString(2), userDao.getUserById(rs.getInt(3)), rs.getString(4),
						stateDao.getStateByID(rs.getInt(5)));
				clip.setViews(rs.getInt(8));
				allClips.add(clip);
			}
			return allClips;
		} catch (SQLException | UserProblemException e) {
			e.printStackTrace();
			throw new ClipException("No clips found!");
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Clip> getClipsByStrinInName(String serachString) throws ClipException {
		List<Clip> allClips = new ArrayList<Clip>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getCon().prepareStatement(SELECT_FROM_CLIPS_BY_CONTAINED_STRING_IN_NAME);
			ps.setString(1, "%" + serachString + "%");
			rs = ps.executeQuery();
			UserDAO userDao = new UserDAO();
			StateDAO stateDao = new StateDAO();
			while (rs.next()) {
				Clip clip = new Clip(rs.getInt(1), rs.getString(2), userDao.getUserById(rs.getInt(3)), rs.getString(4),
						stateDao.getStateByID(rs.getInt(5)));
				allClips.add(clip);
			}
			return allClips;
		} catch (SQLException | UserProblemException e) {
			e.printStackTrace();
			throw new ClipException("No clips found!");
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
