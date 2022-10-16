package com.rortegag.funwithflags.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rortegag.funwithflags.model.Flag;
import com.rortegag.funwithflags.service.FlagService;
import com.rortegag.funwithflags.util.Constants;

@Controller
public class ListFlagsRegionController
{
	
	@Autowired
	private FlagService flagService;
	
	//Africa Flags
	@RequestMapping(value = {"/country_flags/africa_flags"}, method = RequestMethod.GET)
	public ModelAndView showAfricaFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.AFRICA_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("Africa");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
	
	//Asia Flags
	@RequestMapping(value = {"/country_flags/asia_flags"}, method = RequestMethod.GET)
	public ModelAndView showAsiaFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.ASIA_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("Asia");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
	
	//Central America Flags
	@RequestMapping(value = {"/country_flags/centralamerica_flags"}, method = RequestMethod.GET)
	public ModelAndView showCentralAmericaFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.CENTRAL_AMERICA_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("Central America");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
	
	//Europe Flags
	@RequestMapping(value = {"/country_flags/europe_flags"}, method = RequestMethod.GET)
	public ModelAndView showEuropeFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.EUROPE_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("Europe");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
	
	//North America Flags
	@RequestMapping(value = {"/country_flags/northamerica_flags"}, method = RequestMethod.GET)
	public ModelAndView showNorthAmericaFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.NORTH_AMERICA_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("North America");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
		
	//Oceania Flags
	@RequestMapping(value = {"/country_flags/oceania_flags"}, method = RequestMethod.GET)
	public ModelAndView showOceaniaFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.OCEANIA_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("Oceania");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
	
	//South America Flags
	@RequestMapping(value = {"/country_flags/southamerica_flags"}, method = RequestMethod.GET)
	public ModelAndView showSouthAmericaFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.SOUTH_AMERICA_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("South America");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
	
	//Other Flags
	@RequestMapping(value = {"/other_flags"}, method = RequestMethod.GET)
	public ModelAndView showOtherFlags() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.OTHERS_FLAGS);
		List<Flag> listFlags = flagService.listFlagsByRegion("Other");
		modelAndView.addObject("listFlags", listFlags);
		
		return modelAndView;
	}
	
	
	//View selected flag
	@RequestMapping(value = {"/view/flag/{id}"}, method = RequestMethod.GET)
	public ModelAndView viewSelectedFlag(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_FLAG_REGION_PAGE);
		Optional<Flag> flag = flagService.listById(id);
		modelAndView.addObject("flag", flag.get());
		return modelAndView;
	}
	
}
