package com.rortegag.funwithflags.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quizgames")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class QuizGame 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "code", unique = true)
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "flag_id")
	private Flag flag;
	
	@OneToMany(mappedBy = "quizgame", cascade = CascadeType.REMOVE)
	private List<QuizQuestion> quizquestions;
	
	@OneToMany(mappedBy = "quizgame", cascade = CascadeType.REMOVE)
	private List<RankingQuizGame> rankingquizgames;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
}
