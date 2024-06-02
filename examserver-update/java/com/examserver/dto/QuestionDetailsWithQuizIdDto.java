package com.examserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDetailsWithQuizIdDto {

	private Long quesId;
	private String content;
	private String image;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private String givenAnswer;
	private QuizDtoWIthOnlyId quiz;
}
