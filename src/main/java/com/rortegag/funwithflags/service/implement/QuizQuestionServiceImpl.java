package com.rortegag.funwithflags.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.QuizQuestion;
import com.rortegag.funwithflags.repository.QuizQuestionRepository;
import com.rortegag.funwithflags.service.QuizQuestionService;

@Service("quizquestionService")
public class QuizQuestionServiceImpl implements QuizQuestionService
{

	@Autowired
	QuizQuestionRepository quizquestionRepository;
	
	@Override
	public List<QuizQuestion> listQuizQuestions() 
	{
		return null;
	}

	@Override
	public Optional<QuizQuestion> listById(Long id) 
	{
		return null;
	}

	@Override
	public List<QuizQuestion> listQuizQuestionsByQuizgame(QuizGame quizGame) 
	{
		return quizquestionRepository.findByQuizgame(quizGame);
	}
	
	@Override
	public int save(QuizQuestion qq, QuizGame qg) 
	{
		int response = 0;
		qq.setQuizgame(qg);
		QuizQuestion quizQuestion = quizquestionRepository.save(qq);
		if(!quizQuestion.equals(null)) 
		{
			response = 1;
		}
		
		return response;
	}
	
	@Override
	public void saveAll(List<QuizQuestion> listQuizQuestions)
	{
		quizquestionRepository.saveAll(listQuizQuestions);
	}

}
