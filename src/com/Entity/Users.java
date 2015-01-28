package com.Entity;

import java.util.List;

public class Users {
	List<UserInf> users=null;
	
	public Users(){
		super();
	}
	
	public void addUser(UserInf userInf){
		users.add(userInf);
	}
	
	public List<UserInf> getUsers(){
		return this.users;
	}
}
