package com.examserver.service;

import java.util.List;
import java.util.Set;

import com.examserver.dto.QuizDTO;
import com.examserver.entity.Category;
import com.examserver.entity.Quiz;

public interface QuizService {

	public QuizDTO addQuiz(QuizDTO quiz);

	public QuizDTO updateQuiz(Quiz quiz);

	public Set<QuizDTO> getQuizzes();

	public QuizDTO getQuiz(Long quizId);

	public void deleteQuiz(Long quizId);

	public List<QuizDTO> getQuizzesOfCategory(Category category);

	public List<QuizDTO> getActiveQuizzes();

	public List<QuizDTO> getActiveQuizzesOfCategory(Category c);

}
