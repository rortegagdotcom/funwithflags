package com.rortegag.funwithflags.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.RankingQuizGame;
import com.rortegag.funwithflags.model.RankingTrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.service.QuizGameService;
import com.rortegag.funwithflags.service.RankingQuizGameService;
import com.rortegag.funwithflags.service.RankingTrueFalseGameService;
import com.rortegag.funwithflags.service.TrueFalseGameService;
import com.rortegag.funwithflags.util.Constants;

@Controller
public class RankingGamesController 
{

	@Autowired
	private QuizGameService quizgameService;
	
	@Autowired
	private RankingQuizGameService rankingquizgameService;
	
	@Autowired
	private TrueFalseGameService truefalsegameService;
	
	@Autowired
	private RankingTrueFalseGameService rankingtruefalsegameService;
	
	@RequestMapping(value = {"/quizgames"}, method = RequestMethod.GET)
	public ModelAndView viewQuizGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.LIST_RANKING_QUIZGAMES_PAGE);
		List<QuizGame> listQuizGames = quizgameService.listQuizGames();
		modelAndView.addObject("listQuizGames", listQuizGames);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/ranking_quizgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView viewSelectedQuizGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_RANKING_QUIZGAME_PAGE);
		Optional<QuizGame> quizGame = quizgameService.listById(id);
		List<RankingQuizGame> rankingQuizGame = rankingquizgameService.listRankingByQuizgame(quizGame.get());
		modelAndView.addObject("quizgame", quizGame.get());
		modelAndView.addObject("ranking", rankingQuizGame);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/tfgames"}, method = RequestMethod.GET)
	public ModelAndView viewTrueFalseGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.LIST_RANKING_TRUEFALSEGAMES_PAGE);
		List<TrueFalseGame> listTrueFalseGames = truefalsegameService.listTrueFalseGames();
		modelAndView.addObject("listTrueFalseGames", listTrueFalseGames);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/ranking_tfgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView viewSelectedTrueFalseGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_RANKING_TRUEFALSEGAME_PAGE);
		Optional<TrueFalseGame> trueFalseGame = truefalsegameService.listById(id);
		List<RankingTrueFalseGame> rankingTrueFalseGame = rankingtruefalsegameService.listRankingByTruefalsegame(trueFalseGame.get());
		modelAndView.addObject("tfgame", trueFalseGame.get());
		modelAndView.addObject("ranking", rankingTrueFalseGame);
		
		return modelAndView;
	}
	
}
