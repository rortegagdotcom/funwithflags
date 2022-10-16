package com.rortegag.funwithflags.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.repository.TrueFalseGameRepository;
import com.rortegag.funwithflags.service.TrueFalseGameService;

@Service("truefalsegameService")
public class TrueFalseGameServiceImpl implements TrueFalseGameService
{

	@Autowired
	private TrueFalseGameRepository truefalsegameRepository;
	
	@Override
	public List<TrueFalseGame> listTrueFalseGames() 
	{
		return truefalsegameRepository.findAll();
	}
	
	@Override
	public List<TrueFalseGame> listTrueFalseGamesByCode(String code)
	{
		return truefalsegameRepository.findByCode(code);
	}

	@Override
	public List<TrueFalseGame> listTrueFalseGamesByUser(User user) 
	{
		return truefalsegameRepository.findByUser(user);
	}

	@Override
	public Optional<TrueFalseGame> listById(Long id) 
	{
		return truefalsegameRepository.findById(id);
	}

	@Override
	public int save(TrueFalseGame tfg, User u) 
	{
		int response = 0;
		tfg.setUser(u);
		TrueFalseGame trueFalseGame = truefalsegameRepository.save(tfg);
		if(!trueFalseGame.equals(null)) 
		{
			response = 1;
		}
		
		return response;
	}

	@Override
	public void delete(Long id) 
	{
		truefalsegameRepository.deleteById(id);
	}

}
