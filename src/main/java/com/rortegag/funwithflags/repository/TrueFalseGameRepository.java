package com.rortegag.funwithflags.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.User;

@Repository("truefalsegameRepository")
public interface TrueFalseGameRepository extends JpaRepository<TrueFalseGame, Long>
{
	
	List<TrueFalseGame> findByCode(String code);
	
	List<TrueFalseGame> findByUser(User user);
	
}
