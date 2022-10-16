package com.rortegag.funwithflags.service;

import java.util.List;
import java.util.Optional;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.RankingQuizGame;

public interface RankingQuizGameService 
{

	public List<RankingQuizGame> listRankingQuizGames();
	
	public List<RankingQuizGame> listRankingByQuizgame(QuizGame quizGame);
	
	public Optional<RankingQuizGame> listById(Long id);
	
	public int save(RankingQuizGame rankingQuizGame, QuizGame quizGame);
	
	public void delete(Long id);
	
}
