package com.example.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.CategoryDAO;
import com.example.DAO.ClipDAO;
import com.example.classes.Category;
import com.example.classes.Clip;
import com.example.exceptions.CategoryException;
import com.example.exceptions.ClipException;

@Controller
public class Categories {

	@RequestMapping(method=RequestMethod.GET, value="/categories-{id}")
	public String sayHello(@PathVariable("id") Integer id,Model viewModel) {
		try {
			Category thisCategory = new CategoryDAO().getCategoryByID(id);
			List<Clip> clips=new ClipDAO().getAllClipsByCategory(thisCategory);
			viewModel.addAttribute("category", thisCategory);
			viewModel.addAttribute("clips", clips);
			
		} catch (ClipException | CategoryException e) {
			e.printStackTrace();
			return "redirect:index:";
		}
		
		return "movies";
	}	

}