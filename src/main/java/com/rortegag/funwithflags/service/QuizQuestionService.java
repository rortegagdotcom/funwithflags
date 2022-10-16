package com.rortegag.funwithflags.service;

import java.util.List;
import java.util.Optional;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.QuizQuestion;

public interface QuizQuestionService 
{

	public List<QuizQuestion> listQuizQuestions();
	
	public Optional<QuizQuestion> listById(Long id);
	
	public List<QuizQuestion> listQuizQuestionsByQuizgame(QuizGame quizGame);
	
	public int save(QuizQuestion quizQuestion, QuizGame quizGame);
	
	public void saveAll(List<QuizQuestion> listQuizQuestions);
	
}
