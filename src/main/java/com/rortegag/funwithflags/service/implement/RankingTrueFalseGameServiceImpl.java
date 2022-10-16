package com.rortegag.funwithflags.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.RankingTrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.repository.RankingTrueFalseGameRepository;
import com.rortegag.funwithflags.service.RankingTrueFalseGameService;

@Service("rankingtruefalsegameService")
public class RankingTrueFalseGameServiceImpl implements RankingTrueFalseGameService
{
	
	@Autowired
	private RankingTrueFalseGameRepository rankingtruefalsegameRepository;

	@Override
	public List<RankingTrueFalseGame> listRankingTrueFalseGames() 
	{
		return rankingtruefalsegameRepository.findAll();
	}
	
	@Override
	public List<RankingTrueFalseGame> listRankingByTruefalsegame(TrueFalseGame trueFalseGame) 
	{
		return rankingtruefalsegameRepository.findByTruefalsegame(trueFalseGame);
	}

	@Override
	public Optional<RankingTrueFalseGame> listById(Long id) 
	{
		return rankingtruefalsegameRepository.findById(id);
	}

	@Override
	public int save(RankingTrueFalseGame rtfg, TrueFalseGame tfg) 
	{
		int response = 0;
		rtfg.setTruefalsegame(tfg);
		RankingTrueFalseGame rankingTrueFalseGame = rankingtruefalsegameRepository.save(rtfg);
		if(!rankingTrueFalseGame.equals(null)) 
		{
			response = 1;
		}
		
		return response;
	}

	@Override
	public void delete(Long id) 
	{
		rankingtruefalsegameRepository.deleteById(id);
	}

}
