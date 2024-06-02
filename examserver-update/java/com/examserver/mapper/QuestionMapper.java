package com.examserver.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.examserver.dto.QuestionDetailsWithQuizIdDto;
import com.examserver.dto.QuizDtoWIthOnlyId;
import com.examserver.entity.Question;
import com.examserver.entity.Quiz;

@Component
public class QuestionMapper {

	public Question mapQuestionDtoToQuestionEntity(QuestionDetailsWithQuizIdDto questionDto) {
		Question question = new Question();
		Quiz quiz = new Quiz();
		quiz.setQId(questionDto.getQuiz().getQId());
		BeanUtils.copyProperties(questionDto, question);
		question.setQuiz(quiz);
		return question;
	}

	public QuestionDetailsWithQuizIdDto mapQuestionEntityToQuestionDto(Question question) {
		QuestionDetailsWithQuizIdDto dto = new QuestionDetailsWithQuizIdDto();
		QuizDtoWIthOnlyId quizDto = new QuizDtoWIthOnlyId();
		quizDto.setQId(question.getQuiz().getQId());
		BeanUtils.copyProperties(question, dto);
		dto.setQuiz(quizDto);
		return dto;
	}
}
