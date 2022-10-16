package com.rortegag.funwithflags.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseQuestion;
import com.rortegag.funwithflags.repository.TrueFalseQuestionRepository;
import com.rortegag.funwithflags.service.TrueFalseQuestionService;

@Service("truefalsequestionService")
public class TrueFalseQuestionServiceImpl implements TrueFalseQuestionService
{

	@Autowired
	TrueFalseQuestionRepository truefalsequestionRepository;
	
	@Override
	public List<TrueFalseQuestion> listTrueFalseQuestions() 
	{
		return null;
	}

	@Override
	public Optional<TrueFalseQuestion> listById(Long id) 
	{
		return null;
	}
	
	@Override
	public List<TrueFalseQuestion> listTrueFalseQuestionsByTruefalsegame(TrueFalseGame trueFalseGame) 
	{
		return truefalsequestionRepository.findByTruefalsegame(trueFalseGame);
	}

	@Override
	public int save(TrueFalseQuestion tfq, TrueFalseGame tfg) 
	{
		int response = 0;
		tfq.setTruefalsegame(tfg);
		TrueFalseQuestion trueFalseQuestion = truefalsequestionRepository.save(tfq);
		if(!trueFalseQuestion.equals(null)) 
		{
			response = 1;
		}
		
		return response;
	}

	@Override
	public void saveAll(List<TrueFalseQuestion> listTrueFalseQuestions) 
	{
		truefalsequestionRepository.saveAll(listTrueFalseQuestions);
	}

}
