package com.examserver.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.examserver.dto.CategoryDTO;
import com.examserver.entity.Category;

@Component
public class CategoryMapper {

	public CategoryDTO toDto(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		BeanUtils.copyProperties(category, categoryDTO);
		return categoryDTO;
	}

	public Category toEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);
		return category;
	}

}
