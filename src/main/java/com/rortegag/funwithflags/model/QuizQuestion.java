package com.rortegag.funwithflags.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quizquestions")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class QuizQuestion
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "answer_correct")
	private String answerCorrect;
	
	@Column(name = "answer_a")
	private String answerA;
	
	@Column(name = "answer_b")
	private String answerB;
	
	@Column(name = "answer_c")
	private String answerC;
	
	@Column(name = "answer_d")
	private String answerD;
	
	@ManyToOne()
	@JoinColumn(name = "quizgame_id")
	private QuizGame quizgame;

}
