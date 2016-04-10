package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.ClipDAO;
import com.example.classes.Clip;
import com.example.classes.User;
import com.example.exceptions.ClipException;

@Controller
@RequestMapping(value="/index")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model viewModel,HttpServletRequest request) {
		int count=0;
		request.getSession().setAttribute("count", 8);
		try {
			List<Clip> forFirstPage = new ArrayList<Clip>();
			List<Clip> clips=new ClipDAO().getAllClips();
			for(Clip clip:clips){
				forFirstPage.add(clip);
				if(count==7){
					break;
				}
				count++;
			}
			viewModel.addAttribute("clips", forFirstPage);
		} catch (ClipException e) {
			e.printStackTrace();
			System.out.println("Nyama Klipove");
		}
		
		User user=(User) request.getSession().getAttribute("user");
		viewModel.addAttribute("user", user);
		return "index";
	}	

}
