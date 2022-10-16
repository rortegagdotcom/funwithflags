package com.rortegag.funwithflags.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.RankingQuizGame;
import com.rortegag.funwithflags.repository.RankingQuizGameRepository;
import com.rortegag.funwithflags.service.RankingQuizGameService;

@Service("rankingquizgameService")
public class RankingQuizGameServiceImpl implements RankingQuizGameService
{
	
	@Autowired
	private RankingQuizGameRepository rankingquizgameRepository;

	@Override
	public List<RankingQuizGame> listRankingQuizGames() 
	{
		return rankingquizgameRepository.findAll();
	}

	@Override
	public List<RankingQuizGame> listRankingByQuizgame(QuizGame quizGame) 
	{
		return rankingquizgameRepository.findByQuizgame(quizGame);
	}
	
	@Override
	public Optional<RankingQuizGame> listById(Long id) 
	{
		return rankingquizgameRepository.findById(id);
	}

	@Override
	public int save(RankingQuizGame rqg, QuizGame qg) 
	{
		int response = 0;
		rqg.setQuizgame(qg);
		RankingQuizGame rankingQuizGame = rankingquizgameRepository.save(rqg);
		if(!rankingQuizGame.equals(null)) 
		{
			response = 1;
		}
		
		return response;
	}

	@Override
	public void delete(Long id) 
	{
		rankingquizgameRepository.deleteById(id);
	}

	

}
