package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.DAO.ClipDAO;
import com.example.classes.Clip;
import com.example.exceptions.ClipException;

@Controller
@RequestMapping(value="/index")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model model) {
		try {
			List<Clip> clips=new ClipDAO().getAllClips();
			model.addAttribute("clips", clips);
		} catch (ClipException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Nyama Klipove");
		}
		return "index";
	}	

}
