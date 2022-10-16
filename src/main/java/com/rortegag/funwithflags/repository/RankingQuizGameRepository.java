package com.rortegag.funwithflags.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.RankingQuizGame;

@Repository("rankingquizgameRepository")
public interface RankingQuizGameRepository extends JpaRepository<RankingQuizGame, Long> 
{

	List<RankingQuizGame> findByQuizgame(QuizGame quizgame);
	
}
