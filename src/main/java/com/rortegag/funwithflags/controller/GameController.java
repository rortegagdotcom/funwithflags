package com.rortegag.funwithflags.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.QuizQuestion;
import com.rortegag.funwithflags.model.RankingQuizGame;
import com.rortegag.funwithflags.model.RankingTrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseQuestion;
import com.rortegag.funwithflags.service.QuizGameService;
import com.rortegag.funwithflags.service.QuizQuestionService;
import com.rortegag.funwithflags.service.RankingQuizGameService;
import com.rortegag.funwithflags.service.RankingTrueFalseGameService;
import com.rortegag.funwithflags.service.TrueFalseGameService;
import com.rortegag.funwithflags.service.TrueFalseQuestionService;
import com.rortegag.funwithflags.util.Constants;

@Controller
public class GameController
{

	@Autowired
	private QuizGameService quizgameService;
	
	@Autowired
	private QuizQuestionService quizquestionService;
	
	@Autowired
	private RankingQuizGameService rankingquizgameService;
	
	@Autowired
	private TrueFalseGameService truefalsegameService;
	
	@Autowired
	private TrueFalseQuestionService truefalsequestionService;
	
	@Autowired
	private RankingTrueFalseGameService rankingtruefalsegameService;
	
	//Quiz Game
	
	@RequestMapping(value = {"/join/quizgame"}, method = RequestMethod.GET)
	public ModelAndView joinQuizGame() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.JOIN_QUIZGAME_PAGE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/quizgame/"}, method = RequestMethod.POST)
	public ModelAndView quizGame(@RequestParam(value = "player") String player, @RequestParam(value = "codegame") String codeGame) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.QUIZGAME_PAGE);
		List<QuizGame> listQuizGamesCode = quizgameService.listQuizGamesByCode(codeGame);
		if(listQuizGamesCode.isEmpty()) 
		{
			modelAndView.setViewName(Constants.JOIN_QUIZGAME_PAGE);
		} 
		else 
		{
			modelAndView.addObject("quizgame", listQuizGamesCode.get(0));
			List<QuizQuestion> listQuizQuesitons = quizquestionService.listQuizQuestionsByQuizgame(listQuizGamesCode.get(0));
			modelAndView.addObject("quizquestions", listQuizQuesitons);
			modelAndView.addObject("player", player);
			modelAndView.addObject("codegame", codeGame);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/results_quiz_game"}, method = RequestMethod.POST)
	public ModelAndView resultsQuizGame(
			@RequestParam(value = "answer1") String answer1,
			@RequestParam(value = "answer2") String answer2,
			@RequestParam(value = "answer3") String answer3,
			@RequestParam(value = "answer4") String answer4,
			@RequestParam(value = "answer5") String answer5,
			@RequestParam(value = "player") String player,
			@RequestParam(value = "quizgame") Long id)
	{
		ModelAndView modelAndView = new ModelAndView(Constants.RESULTS_QUIZGAME_PAGE);
		Integer score = 0;
		Optional<QuizGame> quizgame = quizgameService.listById(id);
		List<QuizQuestion> quizquestions = quizquestionService.listQuizQuestionsByQuizgame(quizgame.get());
		for (QuizQuestion quizQuestion : quizquestions) 
		{
			String correctAnswer = quizQuestion.getAnswerCorrect();
			if(correctAnswer.equals(answer1)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer2)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer3)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer4)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer5)) 
			{
				score += 10;
			}
		}
		RankingQuizGame rankingQuizGame = new RankingQuizGame();
		rankingQuizGame.setPlayer(player);
		rankingQuizGame.setScore(score);
		rankingquizgameService.save(rankingQuizGame, quizgame.get());
		modelAndView.addObject("quizquestions", quizquestions);
		modelAndView.addObject("answer1", answer1);
		modelAndView.addObject("answer2", answer2);
		modelAndView.addObject("answer3", answer3);
		modelAndView.addObject("answer4", answer4);
		modelAndView.addObject("answer5", answer5);
		modelAndView.addObject("player", player);
		modelAndView.addObject("score", score);
		
		return modelAndView;
	}
	
	//T/F Game
	
	@RequestMapping(value = {"/join/tfgame"}, method = RequestMethod.GET)
	public ModelAndView joinTrueFalseGame() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.JOIN_TRUEFALSEGAME_PAGE);
		
		return modelAndView;
	}
			
	@RequestMapping(value = {"/tfgame/"}, method = RequestMethod.POST)
	public ModelAndView trueFalseGame(@RequestParam(value = "player") String player, @RequestParam(value = "codegame") String codeGame) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.TRUEFALSEGAME_PAGE);
		List<TrueFalseGame> listTrueFalseGamesCode = truefalsegameService.listTrueFalseGamesByCode(codeGame);
		if(listTrueFalseGamesCode.isEmpty()) 
		{
			modelAndView.setViewName(Constants.JOIN_TRUEFALSEGAME_PAGE);
		} 
		else 
		{
			modelAndView.addObject("truefalsegame", listTrueFalseGamesCode.get(0));
			List<TrueFalseQuestion> listTrueFalseQuestions = truefalsequestionService.listTrueFalseQuestionsByTruefalsegame(listTrueFalseGamesCode.get(0));
			modelAndView.addObject("truefalsequestions", listTrueFalseQuestions);
			modelAndView.addObject("player", player);
			modelAndView.addObject("codegame", codeGame);
		}
		
		return modelAndView;
	}
			
	@RequestMapping(value = {"/results_tf_game"}, method = RequestMethod.POST)
	public ModelAndView resultsTrueFalseGame(
			@RequestParam(value = "answer1") String answer1,
			@RequestParam(value = "answer2") String answer2,
			@RequestParam(value = "answer3") String answer3,
			@RequestParam(value = "answer4") String answer4,
			@RequestParam(value = "answer5") String answer5,
			@RequestParam(value = "player") String player,
			@RequestParam(value = "truefalsegame") Long id)
	{
		ModelAndView modelAndView = new ModelAndView(Constants.RESULTS_TRUEFALSEGAME_PAGE);
		Integer score = 0;
		Optional<TrueFalseGame> truefalsegame = truefalsegameService.listById(id);
		List<TrueFalseQuestion> truefalsequestions = truefalsequestionService.listTrueFalseQuestionsByTruefalsegame(truefalsegame.get());
		for (TrueFalseQuestion trueFalseQuestion : truefalsequestions) 
		{
			String correctAnswer = trueFalseQuestion.getAnswerCorrect();
			if(correctAnswer.equals(answer1)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer2)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer3)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer4)) 
			{
				score += 10;
			}
			else if (correctAnswer.equals(answer5)) 
			{
				score += 10;
			}
			else 
			{
				score -= 10;
			}
		}
		RankingTrueFalseGame rankingTrueFalseGame = new RankingTrueFalseGame();
		rankingTrueFalseGame.setPlayer(player);
		rankingTrueFalseGame.setScore(score);
		rankingtruefalsegameService.save(rankingTrueFalseGame, truefalsegame.get());
		modelAndView.addObject("truefalsequestions", truefalsequestions);
		modelAndView.addObject("answer1", answer1);
		modelAndView.addObject("answer2", answer2);
		modelAndView.addObject("answer3", answer3);
		modelAndView.addObject("answer4", answer4);
		modelAndView.addObject("answer5", answer5);
		modelAndView.addObject("player", player);
		modelAndView.addObject("score", score);
	
		return modelAndView;
	}
	
}
