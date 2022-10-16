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
@Table(name = "rankingtruefalsegames")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class RankingTrueFalseGame
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "player")
	private String player;
	
	@Column(name = "score")
	private Integer score;
	
	@ManyToOne
    @JoinColumn(name = "truefalsegame_id")
	private TrueFalseGame truefalsegame;
	
}
