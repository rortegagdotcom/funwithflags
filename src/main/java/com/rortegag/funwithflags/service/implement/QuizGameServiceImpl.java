package com.rortegag.funwithflags.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.repository.QuizGameRepository;
import com.rortegag.funwithflags.service.QuizGameService;

@Service("quizgameService")
public class QuizGameServiceImpl implements QuizGameService 
{

	@Autowired
	private QuizGameRepository quizgameRepository;
	
	@Override
	public List<QuizGame> listQuizGames() 
	{
		return quizgameRepository.findAll();
	}
	
	@Override
	public List<QuizGame> listQuizGamesByCode(String code)
	{
		return quizgameRepository.findByCode(code);
	}

	@Override
	public List<QuizGame> listQuizGamesByUser(User user) 
	{
		return quizgameRepository.findByUser(user);
	}

	@Override
	public Optional<QuizGame> listById(Long id) 
	{
		return quizgameRepository.findById(id);
	}

	@Override
	public int save(QuizGame qg, User u) 
	{
		int response = 0;
		qg.setUser(u);
		QuizGame quizGame = quizgameRepository.save(qg);
		if(!quizGame.equals(null)) 
		{
			response = 1;
		}
		
		return response;
	}

	@Override
	public void delete(Long id) 
	{
		quizgameRepository.deleteById(id);
	}

}
