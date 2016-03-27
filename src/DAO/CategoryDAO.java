package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import classes.Category;

public class CategoryDAO extends AbstractDAO implements ICategoryDAO {
	
	
	private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM category WHERE category_id= ? ;";

	@Override
	public Category getCategoryByID(int categoryID) throws CategoryException{		
		try {
			PreparedStatement ps=getCon().prepareStatement(SELECT_CATEGORY_BY_ID);
			ps.setInt(1, categoryID);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int id =rs.getInt(1);
			String name = rs.getString(2);
			
			return new Category(id, name);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CategoryException("Can't find a category with ID : " + categoryID, e);
			
		}
	}
	
	
	@Override
	public List<Category> getAllCategories() throws CategoryException{
		List<Category> categoriesList = new ArrayList<Category>();
		try{
		Statement statement=getCon().createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM category;");
		
		while(rs.next()){
			Category category=new Category(rs.getInt(1), rs.getString(2));
			categoriesList.add(category);
		}
		return categoriesList;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new CategoryException("No categories found!", e);
		}
		
		
	} 
	
	
	
	
	
	
}

