package com.example.myControllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/upload")
public class Upload {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(Model model) {
		return "upload";
	}	

}