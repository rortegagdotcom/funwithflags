package com.rortegag.funwithflags.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rortegag.funwithflags.model.Flag;
import com.rortegag.funwithflags.model.QuizGame;
import com.rortegag.funwithflags.model.QuizQuestion;
import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.service.FlagService;
import com.rortegag.funwithflags.service.QuizGameService;
import com.rortegag.funwithflags.service.QuizQuestionService;
import com.rortegag.funwithflags.service.UserService;
import com.rortegag.funwithflags.util.CodeGameGenerator;
import com.rortegag.funwithflags.util.Constants;

@Controller
public class QuizGameController 
{

	@Autowired
	UserService userService;
	
	@Autowired
	FlagService flagService;
	
	@Autowired
	QuizGameService quizgameService;
	
	@Autowired
	QuizQuestionService quizquestionService;
	
	/*
	 * Create quiz game
	 */
	
	@RequestMapping(value = {"/home/add_quizgame"}, method = RequestMethod.GET)
	public ModelAndView addQuizGame() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.ADD_QUIZGAME_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<Flag> listFlags = flagService.listFlags();
		modelAndView.addObject("listFlags", listFlags);
		modelAndView.addObject("quizgame", new QuizGame());
		modelAndView.addObject("question", new QuizQuestion());
		
		return modelAndView;
	}
	
	/*
	 * Read quiz game
	 */
	
	@RequestMapping(value = {"/home/view_quizgames"}, method = RequestMethod.GET)
	public ModelAndView viewQuizGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_LIST_QUIZGAMES_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<QuizGame> listQuizGamesUser = quizgameService.listQuizGamesByUser(user);
		modelAndView.addObject("listQuizGamesUser", listQuizGamesUser);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/view/quizgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView viewSelectedQuizGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_QUIZGAME_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		Optional<QuizGame> quizGame = quizgameService.listById(id);
		modelAndView.addObject("quizGame", quizGame.get());
		List<QuizQuestion> listQuizQuestions = quizquestionService.listQuizQuestionsByQuizgame(quizGame.get());
		modelAndView.addObject("listQuizQuestions", listQuizQuestions);
		
		return modelAndView;
	}
	
	/*
	 * Update quiz game
	 */
	
	@RequestMapping(value = {"/home/edit_quizgames"}, method = RequestMethod.GET)
	public ModelAndView viewEditQuizGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.EDIT_LIST_QUIZGAMES_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<QuizGame> listQuizGamesUser = quizgameService.listQuizGamesByUser(user);
		modelAndView.addObject("listQuizGamesUser", listQuizGamesUser);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/edit/quizgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView editSelectedQuizGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.EDIT_QUIZGAME_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		Optional<QuizGame> quizGame = quizgameService.listById(id);
		modelAndView.addObject("quizGame", quizGame.get());
		List<Flag> listFlags = flagService.listFlags();
		modelAndView.addObject("listFlags", listFlags);
		List<QuizQuestion> listQuizQuestions = quizquestionService.listQuizQuestionsByQuizgame(quizGame.get());
		modelAndView.addObject("listQuizQuestions", listQuizQuestions);
		
		return modelAndView;
	}
	
	//
	
	@RequestMapping(value = {"/save_quizgame"}, method = RequestMethod.POST)
	public ModelAndView saveQuizGame(@Validated QuizGame qg, @Validated QuizQuestion qq)
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		qg.setCode(CodeGameGenerator.generateCode());
		String[] questions = qq.getQuestion().split(",");
		String[] answerAs = qq.getAnswerA().split(",");
		String[] answerBs = qq.getAnswerB().split(",");
		String[] answerCs = qq.getAnswerC().split(",");
		String[] answerDs = qq.getAnswerD().split(",");
		String[] answerCorrects = qq.getAnswerCorrect().split(",");
		List<QuizQuestion> tempListQuizQuestions = new ArrayList<>();
		for(int i = 0; i < questions.length; i++) 
		{
			QuizQuestion tempQuizQuestion = new QuizQuestion();
			tempQuizQuestion.setQuestion(questions[i]);
			tempQuizQuestion.setAnswerA(answerAs[i]);
			tempQuizQuestion.setAnswerB(answerBs[i]);
			tempQuizQuestion.setAnswerC(answerCs[i]);
			tempQuizQuestion.setAnswerD(answerDs[i]);
			tempQuizQuestion.setAnswerCorrect(answerCorrects[i]);
			tempQuizQuestion.setQuizgame(qg);
			tempListQuizQuestions.add(tempQuizQuestion);
		}
		quizgameService.save(qg, user);
		quizquestionService.saveAll(tempListQuizQuestions);
		modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/save_edit_quizgame"}, method = RequestMethod.POST)
	public ModelAndView saveEditQuizGame(@Validated QuizGame qg, @Validated QuizQuestion qq)
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		qg.setCode(CodeGameGenerator.generateCode());
		quizgameService.save(qg, user);
		modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		
		return modelAndView;
	}
	
	/*
	 * Delete quiz game
	 */
	
	@RequestMapping(value = {"/home/delete_quizgames"}, method = RequestMethod.GET)
	public ModelAndView viewDeleteQuizGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.DELETE_LIST_QUIZGAMES_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<QuizGame> listQuizGamesUser = quizgameService.listQuizGamesByUser(user);
		modelAndView.addObject("listQuizGamesUser", listQuizGamesUser);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/delete/quizgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView deleteSelectedQuizGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		quizgameService.delete(id);
		modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		
		return modelAndView;
	}
	
}
