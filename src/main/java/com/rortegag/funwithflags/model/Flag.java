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
@Table(name = "flags")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Flag
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "region")
	private String region;
	
	@Column(name = "information", columnDefinition = "LONGTEXT")
	private String information;

	@OneToMany(mappedBy = "flag", cascade = CascadeType.REMOVE)
	private List<QuizGame> quizgame;
	
	@OneToMany(mappedBy = "flag", cascade = CascadeType.REMOVE)
	private List<TrueFalseGame> truefalsegame;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
}
