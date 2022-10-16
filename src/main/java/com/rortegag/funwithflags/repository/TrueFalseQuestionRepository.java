package com.rortegag.funwithflags.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseQuestion;

@Repository("truefalsequestionRepository")
public interface TrueFalseQuestionRepository extends JpaRepository<TrueFalseQuestion, Long>
{

	List<TrueFalseQuestion> findByTruefalsegame(TrueFalseGame truefalsegame);
	
}
