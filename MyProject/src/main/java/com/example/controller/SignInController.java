package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.UserDAO;
import com.example.classes.User;
import com.example.exceptions.UserProblemException;
@Controller
public class SignInController {
	@RequestMapping(method = RequestMethod.GET, value = "/signIn")
	public String showClientDetails(Model viewModel) {
		viewModel.addAttribute("user", new User());
		return "signIn";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/signIn")
	public String showNewClientForm(@ModelAttribute User user, Model viewModel) {
		UserDAO userDao = new UserDAO();
		try {
			User us=userDao.getUserByEmailAndPass(user.getUsername(), user.getPassword());
			//viewModel.addAttribute("user",us);
		} catch (UserProblemException e) {
			e.printStackTrace();
			viewModel.addAttribute("error","Wrong email or password" );
			return "signIn";
		}
		return "index";
	}
}
