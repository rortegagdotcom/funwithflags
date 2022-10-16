package com.rortegag.funwithflags.service;

import com.rortegag.funwithflags.model.User;

public interface UserService
{

	public User findUserByEmail(String email);
	
	public void saveUser(User user);
	
}
