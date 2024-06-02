package com.examserver.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examserver.dto.QuestionDetailsWithQuizIdDto;
import com.examserver.dto.QuizDTO;
import com.examserver.entity.Question;
import com.examserver.entity.Quiz;
import com.examserver.mapper.QuestionMapper;
import com.examserver.mapper.QuizMapper;
import com.examserver.repository.QuestionRepository;
import com.examserver.service.QuestionService;
import com.examserver.service.QuizService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuizService quizService;

	@Autowired
	private QuestionMapper questionMapper;

	@Autowired
	private QuizMapper quizMapper;

	@Override
	public Question addQuestion(QuestionDetailsWithQuizIdDto questionDto) {
		Question question = questionMapper.mapQuestionDtoToQuestionEntity(questionDto);
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(QuestionDetailsWithQuizIdDto questionDto) {
		QuizDTO quizDTO = quizService.getQuiz(questionDto.getQuiz().getQId());
		Quiz quiz = quizMapper.toQuizEntity(quizDTO);
		Question question = new Question();
		BeanUtils.copyProperties(questionDto, question);
		question.setQuiz(quiz);
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		return new HashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public Set<Question> getQuestionsOfQuiz(Long quizId) {
		Quiz quiz = new Quiz();
		quiz.setQId(quizId);
		Set<Question> questionsOfQuiz = this.questionRepository.findByQuiz(quiz);
		return questionsOfQuiz;
	}

	@Override
	public void deleteQuestion(Long quesId) {
		Question question = new Question();
		question.setQuesId(quesId);
		this.questionRepository.delete(question);
	}

	@Override
	public List<QuestionDetailsWithQuizIdDto> getQuestionsOfAnyQuiz(Long quizId) {

		QuizDTO quizDto = this.quizService.getQuiz(quizId);
		Quiz quiz = quizMapper.toQuizEntity(quizDto);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList<>(questions);
		List<QuestionDetailsWithQuizIdDto> resultListOfQuestion = new ArrayList<>();
		if (list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions() + 1));
		}
		Collections.shuffle(list);
		for (Question question : list) {
			resultListOfQuestion.add(questionMapper.mapQuestionEntityToQuestionDto(question));
		}
		return resultListOfQuestion;
	}
}
