package classes;

import classes.User;

public class Admin extends User {

	public Admin(String username, String password, String fullName) {
		super(username, password, fullName);
		// TODO Auto-generated constructor stub
	}

	public Admin(int id, String email, String fullName) {
		super(id,email, fullName);
	}

}
