package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Client;
import com.example.model.ClientsDAO;

@Controller
public class ClientsController {
	@RequestMapping(method = RequestMethod.GET, value = "/clients/{id}")
	public String showClientDetails(Model viewModel,@PathVariable("id") Integer id) {
		Client client = new Client("Gosho " + id, 2000);
		
		
		
		viewModel.addAttribute("client",client);
		
		
		
//		Client newClient = new Client();
//		viewModel.addAttribute("client", newClient);
		return "clientDetails";
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/clients")
	public String showNewClientForm(Model viewModel) {
	Client newClient=new Client();
	viewModel.addAttribute(newClient);
		return "clients";
	}
	
}
