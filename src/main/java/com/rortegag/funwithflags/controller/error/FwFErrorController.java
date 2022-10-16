package com.rortegag.funwithflags.controller.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rortegag.funwithflags.util.Constants;

@Controller
public class FwFErrorController implements ErrorController
{
	
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) 
	{
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) 
		{
			Integer statusCode = Integer.valueOf(status.toString());

			if(statusCode == HttpStatus.BAD_REQUEST.value()) 
			{
				return Constants.BAD_REQUEST_PAGE;
			}
			else if(statusCode == HttpStatus.FORBIDDEN.value())
			{
				return Constants.FORBIDDEN_PAGE;
			}
			else if(statusCode == HttpStatus.NOT_FOUND.value())
			{
				return Constants.NOT_FOUND_PAGE;
			}
			else if(statusCode == HttpStatus.REQUEST_TIMEOUT.value())
			{
				return Constants.REQUEST_TIME_OUT_PAGE;
			}
			else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
			{
				return Constants.INTERNAL_SERVER_PAGE;
			}
			else if(statusCode == HttpStatus.BAD_GATEWAY.value())
			{
				return Constants.BAD_GATEWAY_PAGE;
			}
			else if(statusCode == HttpStatus.SERVICE_UNAVAILABLE.value())
			{
				return Constants.SERVICE_TEMPORARILY_UNAVALIBLE_PAGE;
			}
		}
		
		return "error";
	}

}
