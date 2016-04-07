package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.ClipDAO;
import com.example.DAO.UserDAO;
import com.example.classes.Clip;
import com.example.classes.User;
import com.example.exceptions.ClipException;
import com.example.exceptions.UserProblemException;
@Controller
public class singleClipController {
	
	@RequestMapping(method=RequestMethod.GET, value="/single-{id}")
	public String showClientDetails( @PathVariable("id") Integer id,Model viewModel,HttpServletRequest request) {
		Clip clip;
		List<Clip> clips;
		try {
			if (request.getSession().getAttribute("user") != null) {
				User user=(User)request.getSession().getAttribute("user");
				new UserDAO().addClipToHistory(id,user.getUserID());
			}
			clip = new ClipDAO().getClipByID(id);
		    clips=new ClipDAO().getAllClips();
		    viewModel.addAttribute("clip", clip);
			viewModel.addAttribute("clips", clips);
		    clip.addViews();
		    new ClipDAO().updateClip(clip);
			
		
		} catch (ClipException | UserProblemException e) {
			e.printStackTrace();
			System.out.println("nyama takav clip");
			return "redirect:index:";
		}
		return "single";
	}
	

	
	


}
