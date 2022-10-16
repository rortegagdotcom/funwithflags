package com.rortegag.funwithflags.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rortegag.funwithflags.model.Flag;
import com.rortegag.funwithflags.model.User;

@Repository("flagRepository")
public interface FlagRepository extends JpaRepository<Flag, Long> 
{
	
	List<Flag> findByRegion(String region);
	
	List<Flag> findByUser(User user);

}