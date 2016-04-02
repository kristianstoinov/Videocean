package com.example.model;

public class Client {

	private String name;
	private int salary;
	
	public Client(){
		
	}
	public Client(String name,int salary){
		this.name=name;
		this.salary=salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
}
