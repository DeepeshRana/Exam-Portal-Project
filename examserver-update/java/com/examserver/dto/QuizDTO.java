package com.examserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
	private Long qId;
	private String title;
	private String description;
	private String maxMarks;
	private String numberOfQuestions;
	private boolean active;
	private CategoryDTO category;

}
