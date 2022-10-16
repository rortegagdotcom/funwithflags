package com.rortegag.funwithflags.service;

import java.util.List;
import java.util.Optional;

import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseQuestion;

public interface TrueFalseQuestionService 
{

	public List<TrueFalseQuestion> listTrueFalseQuestions();
	
	public Optional<TrueFalseQuestion> listById(Long id);
	
	public List<TrueFalseQuestion> listTrueFalseQuestionsByTruefalsegame(TrueFalseGame trueFalseGame);
	
	public int save(TrueFalseQuestion trueFalseQuestion, TrueFalseGame trueFalseGame);
	
	public void saveAll(List<TrueFalseQuestion> listTrueFalseQuestions);
	
}
