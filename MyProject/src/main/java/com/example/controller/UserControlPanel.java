package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.ClipDAO;
import com.example.DAO.PlaylistDAO;
import com.example.DAO.UserDAO;
import com.example.classes.Clip;
import com.example.classes.Playlist;
import com.example.classes.User;
import com.example.exceptions.ClipException;
import com.example.exceptions.PlaylistException;
import com.example.exceptions.UserProblemException;

@Controller
public class UserControlPanel {

	@RequestMapping(method = RequestMethod.GET,  value="/user")
	public String userControl(Model viewModel,HttpServletRequest request) { 		
		if(request.getSession().getAttribute("user") == null) {
			return "error";
			}
		User user=(User) request.getSession().getAttribute("user");
		viewModel.addAttribute("user", user);
		UserDAO userDao= new UserDAO();
		try {
			
			int views =userDao.countTheViewsOfThisUserClips(user);
			   List<Clip> clipFromPlaylist=new ClipDAO().getAllClipsByOwnerId(user.getUserID());
			   List<Clip> forUserPage=new ArrayList<Clip>();
			   for(int max=clipFromPlaylist.size()-1;max>clipFromPlaylist.size()-4&&max>=0;max--){
				   forUserPage.add(clipFromPlaylist.get(max));
				 System.out.println(  clipFromPlaylist.get(max).getClipID());
			   }
			   viewModel.addAttribute("clips",forUserPage);
			if(views>0){
				viewModel.addAttribute("views",views);
			}
			else{
				views=0;
				viewModel.addAttribute("views",views);
			}
			
						
			return "userControlPanel";
		} catch (UserProblemException | ClipException e) {
			
			e.printStackTrace();
			return "redirect:index";
		}
		}	
	
	
	@RequestMapping(method = RequestMethod.GET, value="/user-{id}")
	public String otherUser(@PathVariable("id") Integer id,Model viewModel,HttpServletRequest request) { 		
		if(request.getSession().getAttribute("user") == null) {
			return "error";
			}
	
		try {
			UserDAO userDao= new UserDAO();
			User user=userDao.getUserById(id);
			viewModel.addAttribute("user", user);
			
			int views =userDao.countTheViewsOfThisUserClips(user);
			   List<Clip> clipFromPlaylist=new ClipDAO().getAllClipsByOwnerId(user.getUserID());
			   List<Clip> forUserPage=new ArrayList<Clip>();
			   for(int max=clipFromPlaylist.size()-1;max>clipFromPlaylist.size()-4&&max>=0;max--){
				   forUserPage.add(clipFromPlaylist.get(max));
				 System.out.println(  clipFromPlaylist.get(max).getClipID());
			   }
			   viewModel.addAttribute("clips",forUserPage);
			if(views>0){
				viewModel.addAttribute("views",views);
			}
			else{
				views=0;
				viewModel.addAttribute("views",views);
			}
			
						
			return "userControlPanel";
		} catch (UserProblemException | ClipException e) {
			
			e.printStackTrace();
			return "redirect:index";
		}
		
		
		
	}

}