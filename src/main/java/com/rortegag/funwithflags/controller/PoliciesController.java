package com.rortegag.funwithflags.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rortegag.funwithflags.util.Constants;

@Controller
public class PoliciesController 
{

	@RequestMapping(value = {"/legal_notice"}, method = RequestMethod.GET)
	public ModelAndView viewLegalNotice() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.LEGAL_NOTICE_PAGE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/privacy_policy"}, method = RequestMethod.GET)
	public ModelAndView viewPrivacyPolicy() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.PRIVACY_POLICY_PAGE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/cookies_policy"}, method = RequestMethod.GET)
	public ModelAndView viewCookiesPolicy() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.COOKIES_POLICY_PAGE);
		
		return modelAndView;
	}
	
}
