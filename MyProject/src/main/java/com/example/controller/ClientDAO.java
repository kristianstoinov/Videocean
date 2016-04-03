package com.example.controller;

import com.example.model.Client;

public class ClientDAO {



	public static Client getClientById(Integer id) {
		return new Client("Pesho", 3);
	}

}
