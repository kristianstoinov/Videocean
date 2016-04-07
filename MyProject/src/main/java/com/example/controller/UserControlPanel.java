package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.UserDAO;
import com.example.classes.User;
import com.example.exceptions.UserProblemException;

@Controller
@RequestMapping(value="/user")
public class UserControlPanel {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model viewModel,HttpServletRequest request) { 		
//		if(request.getSession().getAttribute("user") == null) {
//			return "error";
//			}
		User user=(User) request.getSession().getAttribute("user");
		viewModel.addAttribute("user", user);

		UserDAO userDao= new UserDAO();
		try {
			
			int views =userDao.countTheViewsOfThisUserClips(user);
			
			if(views>0){
				viewModel.addAttribute("views",views);
			}
			else{
				views=0;
				viewModel.addAttribute("views",views);
			}
			
						
			
		} catch (UserProblemException e) {
			
			e.printStackTrace();
		}
		
		
		return "userControlPanel";
	}	

}