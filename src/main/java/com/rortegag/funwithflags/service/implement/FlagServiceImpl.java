package com.rortegag.funwithflags.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.Flag;
import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.repository.FlagRepository;
import com.rortegag.funwithflags.service.FlagService;

@Service("flagService")
public class FlagServiceImpl implements FlagService
{
	
	@Autowired
	private FlagRepository flagRepository;

	@Override
	public List<Flag> listFlags() 
	{
		return flagRepository.findAll();
	}

	@Override
	public List<Flag> listFlagsByRegion(String region) 
	{
		return flagRepository.findByRegion(region);
	}
	
	@Override
	public List<Flag> listFlagsByUser(User user) 
	{
		return flagRepository.findByUser(user);
	}
	
	@Override
	public Optional<Flag> listById(Long id) 
	{
		return flagRepository.findById(id);
	}

	@Override
	public int save(Flag f, User u) 
	{
		int response = 0;
		f.setUser(u);
		Flag flag = flagRepository.save(f);
		if(!flag.equals(null)) 
		{
			response = 1;
		}
		
		return response;
	}

	@Override
	public void delete(Long id) 
	{
		flagRepository.deleteById(id);
	}

}
