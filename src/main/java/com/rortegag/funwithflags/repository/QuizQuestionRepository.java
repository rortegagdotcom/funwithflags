package com.rortegag.funwithflags.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.QuizQuestion;

@Repository("quizquestionRepository")
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long> 
{

	List<QuizQuestion> findByQuizgame(QuizGame quizgame);
	
}
