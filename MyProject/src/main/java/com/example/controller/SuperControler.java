package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SuperControler {

	@RequestMapping(method=RequestMethod.GET,value={"/super","/admin/allProducts"})
	public String printSomething(Model viewModel){
	TextDAO dao=new TextDAO();
	viewModel.addAttribute("text",dao.getTextById(1)); 
	
		return "superView";
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/duper","/admin/allProducts"})
	public String print(Model viewModel){
	TextDAO dao=new TextDAO(); 
	viewModel.addAttribute("text",dao.getTextById(2)); 
	
		return "superView";
	}
	

	
}
