package com.example.model;

public class ClientsDAO {

	
	public static void addNewClient(Client client){
		System.out.println("New client added with name = " + client.getName() + " and salary " + client.getSalary());
	}
}
