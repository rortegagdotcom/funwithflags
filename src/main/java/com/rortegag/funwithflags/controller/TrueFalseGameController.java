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
import com.rortegag.funwithflags.model.TrueFalseGame;
import com.rortegag.funwithflags.model.TrueFalseQuestion;
import com.rortegag.funwithflags.model.User;
import com.rortegag.funwithflags.service.FlagService;
import com.rortegag.funwithflags.service.RankingTrueFalseGameService;
import com.rortegag.funwithflags.service.TrueFalseGameService;
import com.rortegag.funwithflags.service.TrueFalseQuestionService;
import com.rortegag.funwithflags.service.UserService;
import com.rortegag.funwithflags.util.CodeGameGenerator;
import com.rortegag.funwithflags.util.Constants;

@Controller
public class TrueFalseGameController 
{

	@Autowired
	UserService userService;
	
	@Autowired
	FlagService flagService;
	
	@Autowired
	TrueFalseGameService truefalsegameService;
	
	@Autowired
	TrueFalseQuestionService truefalsequestionService;
	
	@Autowired
	RankingTrueFalseGameService rankingtruefalsegameService;
	
	/*
	 * Create T/F game
	 */
	
	@RequestMapping(value = {"/home/add_tfgame"}, method = RequestMethod.GET)
	public ModelAndView addTrueFalseGame() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.ADD_TRUEFALSEGAME_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<Flag> listFlags = flagService.listFlags();
		modelAndView.addObject("listFlags", listFlags);
		modelAndView.addObject("truefalsegame", new TrueFalseGame());
		modelAndView.addObject("question", new TrueFalseQuestion());
		
		return modelAndView;
	}
	
	/*
	 * Read T/F game
	 */
	
	@RequestMapping(value = {"/home/view_tfgames"}, method = RequestMethod.GET)
	public ModelAndView viewTrueFalseGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_LIST_TRUEFALSEGAME_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<TrueFalseGame> listTrueFalseGamesUser = truefalsegameService.listTrueFalseGamesByUser(user);
		modelAndView.addObject("listTrueFalseGamesUser", listTrueFalseGamesUser);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/view/tfgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView viewSelectedTrueFalseGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.VIEW_TRUEFALSEGAME_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		Optional<TrueFalseGame> truefalseGame = truefalsegameService.listById(id);
		modelAndView.addObject("truefalseGame", truefalseGame.get());
		List<TrueFalseQuestion> listTrueFalseQuestions = truefalsequestionService.listTrueFalseQuestionsByTruefalsegame(truefalseGame.get());
		modelAndView.addObject("listTrueFalseQuestions", listTrueFalseQuestions);
		
		return modelAndView;
	}
	
	/*
	 * Update T/F game
	 */
	
	@RequestMapping(value = {"/home/edit_tfgames"}, method = RequestMethod.GET)
	public ModelAndView viewEditTrueFalseGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.EDIT_LIST_TRUEFALSEGAMES_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<TrueFalseGame> listTrueFalseGamesUser = truefalsegameService.listTrueFalseGamesByUser(user);
		modelAndView.addObject("listTrueFalseGamesUser", listTrueFalseGamesUser);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/edit/tfgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView editSelectedTrueFalseGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.EDIT_TRUEFALSEGAME_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		Optional<TrueFalseGame> truefalseGame = truefalsegameService.listById(id);
		modelAndView.addObject("truefalseGame", truefalseGame.get());
		List<Flag> listFlags = flagService.listFlags();
		modelAndView.addObject("listFlags", listFlags);
		List<TrueFalseQuestion> listTrueFalseQuestions = truefalsequestionService.listTrueFalseQuestionsByTruefalsegame(truefalseGame.get());
		modelAndView.addObject("listTrueFalseQuestions", listTrueFalseQuestions);
		
		return modelAndView;
	}
	
	//
	
	@RequestMapping(value = {"/save_tfgame"}, method = RequestMethod.POST)
	public ModelAndView saveQuizGame(@Validated TrueFalseGame tfg, @Validated TrueFalseQuestion tfq)
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		tfg.setCode(CodeGameGenerator.generateCode());
		String[] questions = tfq.getQuestion().split(",");
		String[] answers = tfq.getAnswerCorrect().split(",");
		List<TrueFalseQuestion> tempListTrueFalseQuestions = new ArrayList<>();
		for(int i = 0; i < questions.length; i++) 
		{
			TrueFalseQuestion tempTrueFalseQuestion = new TrueFalseQuestion();
			tempTrueFalseQuestion.setQuestion(questions[i]);
			tempTrueFalseQuestion.setAnswerCorrect(answers[i]);
			tempTrueFalseQuestion.setTruefalsegame(tfg);
			tempListTrueFalseQuestions.add(tempTrueFalseQuestion);
		}
		truefalsegameService.save(tfg, user);
		truefalsequestionService.saveAll(tempListTrueFalseQuestions);
		modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/save_edit_tfgame"}, method = RequestMethod.POST)
	public ModelAndView saveEditTrueFalseGame(@Validated TrueFalseGame tfg, @Validated TrueFalseQuestion tfq)
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		tfg.setCode(CodeGameGenerator.generateCode());
		truefalsegameService.save(tfg, user);
		modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		
		return modelAndView;
	}
	
	/*
	 * Delete T/F game
	 */
	
	@RequestMapping(value = {"/home/delete_tfgames"}, method = RequestMethod.GET)
	public ModelAndView viewDeleteTrueFalseGames() 
	{
		ModelAndView modelAndView = new ModelAndView(Constants.DELETE_LIST_TRUEFALSEGAMES_PAGE);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		List<TrueFalseGame> listTrueFalseGamesUser = truefalsegameService.listTrueFalseGamesByUser(user);
		modelAndView.addObject("listTrueFalseGamesUser", listTrueFalseGamesUser);
		
		return modelAndView;
	}
	
	@RequestMapping(value = {"/home/delete/tfgame/{id}"}, method = RequestMethod.GET)
	public ModelAndView deleteSelectedTrueFalseGame(@PathVariable Long id) 
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("username", user.getFirstName() + " " + user.getLastName());
		truefalsegameService.delete(id);
		modelAndView.setViewName(Constants.REDIRECT_HOME_PAGE);
		
		return modelAndView;
	}
	
}
