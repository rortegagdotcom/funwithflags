package com.rortegag.funwithflags.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.User;

@Repository("quizgameRepository")
public interface QuizGameRepository extends JpaRepository<QuizGame, Long>
{
	
	List<QuizGame> findByCode(String code);

	List<QuizGame> findByUser(User user);
	
}
