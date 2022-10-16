package com.rortegag.funwithflags.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.repository.UserRepository;
import com.rortegag.funwithflags.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService
{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) 
	{
		return userRepository.findByEmail(email);
	}

	@Override
	public void saveUser(User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		userRepository.save(user);
	}
	
}
