package DAO;

import java.util.List;

import classes.Category;

public interface ICategoryDAO {

	Category getCategoryByID(int categoryID) throws CategoryException;

	List<Category> getAllCategories() throws CategoryException;

}