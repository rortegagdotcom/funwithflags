package com.rortegag.funwithflags.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rortegag.funwithflags.model.RankingTrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseGame;

@Repository("rankingtruefalsegameRepository")
public interface RankingTrueFalseGameRepository extends JpaRepository<RankingTrueFalseGame, Long> 
{
	
	List<RankingTrueFalseGame> findByTruefalsegame(TrueFalseGame truefalsegame);
	
}
