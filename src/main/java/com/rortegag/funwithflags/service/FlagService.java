package com.rortegag.funwithflags.service;

import java.util.List;
import java.util.Optional;

import com.rortegag.funwithflags.model.Flag;
import com.rortegag.funwithflags.model.User;

public interface FlagService 
{
	
	public List<Flag> listFlags();
	
	public List<Flag> listFlagsByRegion(String region);
	
	public List<Flag> listFlagsByUser(User user);
	
	public Optional<Flag> listById(Long id);
	
	public int save(Flag flag, User user);
	
	public void delete(Long id);
	
}
