package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.classes.User;

@Controller
@RequestMapping(value="/user")
public class UserControlPanel {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model viewModel,HttpServletRequest request) { 		
		if(request.getSession().getAttribute("user") == null) {
			return "error";
			}
		
		User user=(User) request.getSession().getAttribute("user");
		viewModel.addAttribute("user", user);

		return "userControlPanel";
	}	

}