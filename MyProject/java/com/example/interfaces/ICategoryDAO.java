package com.example.interfaces;

import java.util.List;

import com.example.classes.Category;
import com.example.exceptions.CategoryException;

public interface ICategoryDAO {

	Category getCategoryByID(int categoryID) throws CategoryException;

	List<Category> getAllCategories() throws CategoryException;

}