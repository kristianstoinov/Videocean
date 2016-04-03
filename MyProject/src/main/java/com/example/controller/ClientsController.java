package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Client;

@Controller
public class ClientsController {
	
	@RequestMapping(method=RequestMethod.GET, value="/clients/{id}")
	public String showClientDetails(Model viewModel, @PathVariable("id") Integer id) {
		viewModel.addAttribute("client", ClientDAO.getClientById(id));
		
		return "clientDetails";
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/clients")
	public String showNewClientForm(Model viewModel) {
		Client newClient = new Client(null, 0);
		
		// map to 'çlient' attribute
		viewModel.addAttribute(newClient);
		
		return "clients";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/clients")
	public String showNewClientForm(@ModelAttribute Client client) {
		ClientsDAO.addNewClient(client);
		return "clients";
	}
}
