package com.rortegag.funwithflags.service;

import java.util.List;
import java.util.Optional;

import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.User;

public interface TrueFalseGameService 
{

	public List<TrueFalseGame> listTrueFalseGames();
	
	public List<TrueFalseGame> listTrueFalseGamesByCode(String code);
	
	public List<TrueFalseGame> listTrueFalseGamesByUser(User user);
	
	public Optional<TrueFalseGame> listById(Long id);
	
	public int save(TrueFalseGame trueFalseGame, User user);
	
	public void delete(Long id);
	
}
