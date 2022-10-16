package com.rortegag.funwithflags.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.service.UserService;
import com.rortegag.funwithflags.util.Constants;

@Controller
public class UserController
{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView login()
	{
		ModelAndView modelAndView = new ModelAndView(Constants.LOGIN_PAGE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
	public ModelAndView signup() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.SIGNUP_PAGE);
		User user = new User();
		modelAndView.addObject("user", user);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
	public ModelAndView createUser(@Validated User user, BindingResult bindingResult) 
	{
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		
		if(userExists != null) 
		{
			bindingResult.rejectValue("email", "error.user", "This email already exists");
		}
		
		if(bindingResult.hasErrors()) 
		{
			modelAndView.setViewName(Constants.SIGNUP_PAGE);
		} 
		else 
		{
			userService.saveUser(user);
			modelAndView.addObject("msg", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName(Constants.SIGNUP_PAGE);
		}
		
		return modelAndView;
	}

	@RequestMapping(value = {"/home"}, method = RequestMethod.GET)
	public ModelAndView admin() 
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		modelAndView.setViewName(Constants.HOME_PAGE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/access_denied"}, method = RequestMethod.GET)
	public ModelAndView accessDenied() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.ACCESS_DENIED_PAGE);
		
		return modelAndView;
	}
	
}
