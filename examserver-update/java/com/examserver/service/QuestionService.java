package com.examserver.service;

import java.util.List;
import java.util.Set;

import com.examserver.dto.QuestionDetailsWithQuizIdDto;
import com.examserver.entity.Question;

public interface QuestionService {

	public Question addQuestion(QuestionDetailsWithQuizIdDto question);

	public Question updateQuestion(QuestionDetailsWithQuizIdDto question);

	public Set<Question> getQuestions();

	public Question getQuestion(Long questionId);

	public Set<Question> getQuestionsOfQuiz(Long quizId);

	public void deleteQuestion(Long quesId);

	public List<QuestionDetailsWithQuizIdDto> getQuestionsOfAnyQuiz(Long quizId);

}
