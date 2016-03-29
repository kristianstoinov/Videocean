package interfaces;

import java.util.List;

import classes.Category;
import exceptions.CategoryException;

public interface ICategoryDAO {

	Category getCategoryByID(int categoryID) throws CategoryException;

	List<Category> getAllCategories() throws CategoryException;

}