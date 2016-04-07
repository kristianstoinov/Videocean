package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.DAO.UserDAO;
import com.example.classes.Playlist;
import com.example.classes.User;
import com.example.exceptions.UserProblemException;

@Controller
@RequestMapping(value="/library")
public class Library {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model model,Model viewModel,HttpServletRequest request,HttpServletResponse response) {
		
	if(request.getSession().getAttribute("user") == null) {
		return "error";
		}
		
		User user=(User)request.getSession().getAttribute("user");
		 try {
			List<Playlist> library=new UserDAO().getAllPlaylistsForUser(user.getUserID());
		viewModel.addAttribute(library);
			
		} catch (UserProblemException e) {
			e.printStackTrace();
		}
			viewModel.addAttribute("user", user);
		
		
			return "library";
	}	

}
