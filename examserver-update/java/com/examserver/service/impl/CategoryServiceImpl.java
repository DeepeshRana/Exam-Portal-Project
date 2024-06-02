package com.examserver.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.examserver.dto.CategoryDTO;
import com.examserver.entity.Category;
import com.examserver.mapper.CategoryMapper;
import com.examserver.repository.CategoryRepository;
import com.examserver.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	private final CategoryMapper categoryMapper;

	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = categoryMapper.toEntity(categoryDTO);
		Category savedCategory = this.categoryRepository.save(category);
		return categoryMapper.toDto(savedCategory);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
		Category category = categoryMapper.toEntity(categoryDTO);
		Category updatedCategory = this.categoryRepository.save(category);
		return categoryMapper.toDto(updatedCategory);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories = this.categoryRepository.findAll();
		return categories.stream().map(categoryMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategory(Long categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NoSuchElementException("Category not found with id: " + categoryId));
		return categoryMapper.toDto(category);
	}

	@Override
	public void deleteCategory(Long categoryId) {
		this.categoryRepository.deleteById(categoryId);
	}
}
