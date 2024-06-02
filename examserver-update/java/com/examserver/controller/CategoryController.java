package com.examserver.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.dto.CategoryDTO;
import com.examserver.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	// Add category
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
		CategoryDTO savedCategory = this.categoryService.addCategory(categoryDTO);
		return ResponseEntity.ok(savedCategory);
	}

	// Get category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") Long categoryId) {
		CategoryDTO category = this.categoryService.getCategory(categoryId);
		return ResponseEntity.ok(category);
	}

	// Get all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}

	// update category
	@PutMapping("/")
	public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
		CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO);
		return ResponseEntity.ok(updatedCategory);
	}

	// Delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") String categoryId) {
		try {
			Long id = Long.parseLong(categoryId); // Convert the string to a Long
			this.categoryService.deleteCategory(id);
			return ResponseEntity.noContent().build();
		} catch (NumberFormatException e) {
			// Handle the case where the categoryId is not a valid Long
			return ResponseEntity.badRequest().build();
		}
	}
}
