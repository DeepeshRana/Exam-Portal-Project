package com.examserver.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.dto.QuizDTO;
import com.examserver.entity.Category;
import com.examserver.entity.Quiz;
import com.examserver.mapper.QuizMapper;
import com.examserver.repository.QuizRepository;
import com.examserver.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuizMapper quizMapper;

	@Override
	public QuizDTO addQuiz(QuizDTO quiz) {
		Quiz save = quizRepository.save(quizMapper.toQuizEntity(quiz));
		QuizDTO quizdto = quizMapper.toQuizDTO(save);
		return quizdto;
	}

	@Override
	public QuizDTO updateQuiz(Quiz quiz) {
		System.out.println(quiz);
		Quiz quizupdated = quizRepository.findById(quiz.getQId()).get();
//		Quiz quizupdated=new Quiz();

		quizupdated.setTitle(quiz.getTitle());
		quizupdated.setDescription(quiz.getDescription());
		quizupdated.setMaxMarks(quiz.getMaxMarks());
		quizupdated.setNumberOfQuestions(quiz.getNumberOfQuestions());
		quizupdated.setActive(quiz.isActive());

		quizRepository.save(quizupdated);
		QuizDTO quizdto = quizMapper.toQuizDTO(quizupdated);
		return quizdto;
	}

	@Override
	public Set<QuizDTO> getQuizzes() {
		List<Quiz> quizset = quizRepository.findAll();
		Set<QuizDTO> quizdtoset = new HashSet<>();
		for (Quiz quiz : quizset) {
			QuizDTO quizdto = quizMapper.toQuizDTO(quiz);
			quizdtoset.add(quizdto);

		}
		return quizdtoset;
	}

	@Override
	public QuizDTO getQuiz(Long quizId) {
		Quiz quiz = quizRepository.findById(quizId).get();
		QuizDTO quizdto = quizMapper.toQuizDTO(quiz);
		return quizdto;
	}

	@Override
	public void deleteQuiz(Long quizId) {
		Optional<Quiz> byId = quizRepository.findById(quizId);
		if (byId.isPresent()) {
			this.quizRepository.delete(byId.get());
		}
	}

	@Override
	public List<QuizDTO> getQuizzesOfCategory(Category category) {
		List<Quiz> quizlist = quizRepository.findBycategory(category);
		List<QuizDTO> quizdtolist = new ArrayList<>();
		for (Quiz quiz : quizlist) {
			QuizDTO quizdto = quizMapper.toQuizDTO(quiz);
			quizdtolist.add(quizdto);
		}
		return quizdtolist;
	}

	// get active quizzes
	@Override
	public List<QuizDTO> getActiveQuizzes() {
		List<Quiz> activequizlist = quizRepository.findByActive(true);
		List<QuizDTO> activequizdtolist = new ArrayList<>();
		for (Quiz q : activequizlist) {
			QuizDTO dto = quizMapper.toQuizDTO(q);
			activequizdtolist.add(dto);
		}
		return activequizdtolist;

	}

	@Override
	public List<QuizDTO> getActiveQuizzesOfCategory(Category c) {
		List<Quiz> quizlist = quizRepository.findByCategoryAndActive(c, true);
		List<QuizDTO> activequizdtolist = new ArrayList<>();
		for (Quiz q : quizlist) {
			QuizDTO dto = quizMapper.toQuizDTO(q);
			activequizdtolist.add(dto);
		}
		return activequizdtolist;
	}

}
