package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.TextDAO;

@Controller
public class SuperController {
	
	@RequestMapping(method=RequestMethod.GET, value={"/super"})
	public String printSmth(Model viewModel) {
		// work with Model
		TextDAO dao = new TextDAO();
		viewModel.addAttribute("text",dao.getTextById(1));
		
		return "superView";
	}
	
	@RequestMapping(method=RequestMethod.GET, value={"/duper"})
	public String print(Model viewModel) {
		// work with Model
		TextDAO dao = new TextDAO();
		viewModel.addAttribute("text",dao.getTextById(2));
		
		return "superView";
	}
	
}
