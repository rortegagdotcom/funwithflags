package com.rortegag.funwithflags.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class User
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "active")
	private int active;
	
	@OneToMany(mappedBy = "user")
	private List<Flag> flags;
	
	@OneToMany(mappedBy = "user")
	private List<QuizGame> quizgames;
	
	@OneToMany(mappedBy = "user")
	private List<TrueFalseGame> truefalsegames;
	
}
