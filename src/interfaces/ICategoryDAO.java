package interfaces;

import java.util.List;

import DAO.CategoryException;
import classes.Category;

public interface ICategoryDAO {

	Category getCategoryByID(int categoryID) throws CategoryException;

	List<Category> getAllCategories() throws CategoryException;

}