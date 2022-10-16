package com.rortegag.funwithflags.service;

import java.util.List;
import java.util.Optional;

import com.rortegag.funwithflags.model.RankingTrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseGame;

public interface RankingTrueFalseGameService 
{

	public List<RankingTrueFalseGame> listRankingTrueFalseGames();
	
	public List<RankingTrueFalseGame> listRankingByTruefalsegame(TrueFalseGame trueFalseGame);
	
	public Optional<RankingTrueFalseGame> listById(Long id);
	
	public int save(RankingTrueFalseGame rankingTrueFalseGame, TrueFalseGame trueFalseGame);
	
	public void delete(Long id);
	
}
