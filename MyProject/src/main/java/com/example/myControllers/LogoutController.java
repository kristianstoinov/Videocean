package com.example.myControllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.classes.User;

@Controller
public class LogoutController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/logout")
	public String showClientDetails(HttpServletRequest request) {
		request.getSession().invalidate();
		return "index";
	}

}
