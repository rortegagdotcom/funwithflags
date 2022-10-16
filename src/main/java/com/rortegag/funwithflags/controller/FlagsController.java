package com.rortegag.funwithflags.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.rortegag.funwithflags.model.Flag;
import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.service.FlagService;
import com.rortegag.funwithflags.service.UserService;
import com.rortegag.funwithflags.service.storage.StorageService;
import com.rortegag.funwithflags.util.Constants;

@Controller
public class FlagsController 
{

	@Autowired
	UserService userService;
	
	@Autowired
	FlagService flagService;
	
	@Autowired
	StorageService storageService;
	
	/*
	 * Create Flag
	 */
	
	@RequestMapping(value = {"/home/add_flag"}, method = RequestMethod.GET)
	public ModelAndView addFlag() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.ADD_FLAG_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		modelAndView.addObject("flag", new Flag());
		return modelAndView;
	}
	
	/*
	 * Read Flag
	 */
	
	@RequestMapping(value = {"/home/view_flags"}, method = RequestMethod.GET)
	public ModelAndView viewFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_LIST_FLAGS_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<Flag> listFlagsUser = flagService.listFlagsByUser(user);
		modelAndView.addObject("listFlagsUser", listFlagsUser);
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/view/flag/{id}"}, method = RequestMethod.GET)
	public ModelAndView viewSelectedFlag(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_FLAG_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		Optional<Flag> flag = flagService.listById(id);
		modelAndView.addObject("flag", flag.get());
		return modelAndView;
	}
	
	/*
	 * Update Flag
	 */
	
	@RequestMapping(value = {"/home/edit_flags"}, method = RequestMethod.GET)
	public ModelAndView viewEditFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.EDIT_LIST_FLAGS_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<Flag> listFlagsUser = flagService.listFlagsByUser(user);
		modelAndView.addObject("listFlagsUser", listFlagsUser);
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/edit/flag/{id}"}, method = RequestMethod.GET)
	public ModelAndView editSelectedFlag(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.EDIT_FLAG_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		Optional<Flag> flag = flagService.listById(id);
		modelAndView.addObject("flag", flag.get());
		return modelAndView;
	}
	
	//
	
	@RequestMapping(value = {"/save_flag"}, method = RequestMethod.POST, consumes = {"multipart/form-data"})
	public ModelAndView saveFlag(@Validated Flag f, @RequestParam("image-flag") MultipartFile file) 
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		if(!file.isEmpty()) 
		{
			String image = storageService.store(file, f.getName());
			f.setImage(MvcUriComponentsBuilder
					.fromMethodName(FlagsController.class, "serveFile", image)
					.build()
					.toUriString());
			flagService.save(f, user);
			modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		} 
		else 
		{
			modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		}
		
		return modelAndView;
	}
	
	/*
	 * Delete Flag
	 */
	
	@RequestMapping(value = {"/home/delete_flags"}, method = RequestMethod.GET)
	public ModelAndView viewDeleteFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.DELETE_LIST_FLAGS_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<Flag> listFlagsUser = flagService.listFlagsByUser(user);
		modelAndView.addObject("listFlagsUser", listFlagsUser);
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/delete/flag/{id}"}, method = RequestMethod.GET)
	public ModelAndView deleteSelectedFlag(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		flagService.delete(id);
		modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		return modelAndView;
	}
	
	//
	
	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().body(file);
	}
	
}
