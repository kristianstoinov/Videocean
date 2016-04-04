package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.DAO.UserDAO;
import com.example.classes.User;
import com.example.exceptions.UserProblemException;
import com.example.model.Client;

@Controller
// @SessionAttributes("user")
public class SignUpController {

	@RequestMapping(method = RequestMethod.GET, value = "/signUp")
	public String showClientDetails(Model viewModel) {
		viewModel.addAttribute("user", new User());
		return "signUp";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signUp")
	public String showNewClientForm(@ModelAttribute User user, Model viewModel) {
		UserDAO userDao = new UserDAO();
		try {
			userDao.addUser(user);
		} catch (UserProblemException e) {
			e.printStackTrace();
			viewModel.addAttribute("error", "The user with this email already exist");
			return "signUp";
		}
		return "index";
	}
}
