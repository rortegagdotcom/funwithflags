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
@Table(name = "truefalsequestions")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class TrueFalseQuestion 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "answerCorrect")
	private String answerCorrect;
	
	@ManyToOne()
	@JoinColumn(name = "truefalsegame_id")
	private TrueFalseGame truefalsegame;
	
}
