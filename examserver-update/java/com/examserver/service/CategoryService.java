package com.examserver.service;

import java.util.List;

import com.examserver.dto.CategoryDTO;

public interface CategoryService {

	CategoryDTO addCategory(CategoryDTO categoryDTO);

	CategoryDTO updateCategory(CategoryDTO categoryDTO);

	List<CategoryDTO> getCategories();

	CategoryDTO getCategory(Long categoryId);

	void deleteCategory(Long categoryId);

}
