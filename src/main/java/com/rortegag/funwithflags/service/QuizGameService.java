package com.rortegag.funwithflags.service;

import java.util.List;
import java.util.Optional;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.User;

public interface QuizGameService 
{
	
	public List<QuizGame> listQuizGames();
	
	public List<QuizGame> listQuizGamesByCode(String code);
	
	public List<QuizGame> listQuizGamesByUser(User user);
	
	public Optional<QuizGame> listById(Long id);
	
	public int save(QuizGame quizGame, User user);
	
	public void delete(Long id);

}
