package com.examserver.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.examserver.dto.CategoryDTO;
import com.examserver.dto.QuizDTO;
import com.examserver.entity.Category;
import com.examserver.entity.Quiz;

@Component
public class QuizMapper {
	@Autowired
	private CategoryMapper categoryMapper;

	public QuizDTO toQuizDTO(Quiz quiz) {
		QuizDTO dto = new QuizDTO();
		BeanUtils.copyProperties(quiz, dto);
		CategoryDTO categoryDTO = categoryMapper.toDto(quiz.getCategory());
		dto.setCategory(categoryDTO);
		return dto;
	}

	public Quiz toQuizEntity(QuizDTO dto) {
		Quiz quiz = new Quiz();
		BeanUtils.copyProperties(dto, quiz);
		Category category = categoryMapper.toEntity(dto.getCategory());
		quiz.setCategory(category);
		return quiz;
	}
}
