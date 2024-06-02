package com.examserver.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.dto.QuizDTO;
import com.examserver.entity.Category;
import com.examserver.entity.Quiz;
import com.examserver.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private QuizService quizService;

	// add quiz service
	@PostMapping("/")
	public ResponseEntity<QuizDTO> add(@RequestBody QuizDTO quiz) {
		QuizDTO quizDtoResponse = quizService.addQuiz(quiz);
		return ResponseEntity.ok(quizDtoResponse);
	}

	// update quiz
	@PutMapping("/")
	public ResponseEntity<QuizDTO> update(@RequestBody Quiz quiz) {
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}

	// get quiz
	@GetMapping("/")
	public ResponseEntity<Set<QuizDTO>> quizzes() {
		Set<QuizDTO> quizdtoset = quizService.getQuizzes();
		return ResponseEntity.ok(quizdtoset);
	}

	// get single quiz
	@GetMapping("/{qid}")
	public ResponseEntity<Object> quiz(@PathVariable("qid") Long qid) {
		QuizDTO quizdto = quizService.getQuiz(qid);
		return new ResponseEntity<>(quizdto, HttpStatus.OK);
	}

	// delete the quiz
	@DeleteMapping("/{qid}")
	public void delete(@PathVariable("qid") Long qid) {
		quizService.deleteQuiz(qid);

	}

	@GetMapping("/category/{cid}")
	public ResponseEntity<List<QuizDTO>> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
		Category category = new Category();
		category.setCId(cid);
		List<QuizDTO> quizlist = quizService.getQuizzesOfCategory(category);
		return new ResponseEntity<>(quizlist, HttpStatus.OK);
	}

	// get active quizzes
	@GetMapping("/active")
	public ResponseEntity<List<QuizDTO>> getActiveQuizzes() {
		List<QuizDTO> quizlist = quizService.getActiveQuizzes();
		return new ResponseEntity<>(quizlist, HttpStatus.OK);
	}

	// get active quizzes of category
	@GetMapping("/category/active/{cid}")
	public ResponseEntity<List<QuizDTO>> getActiveQuizzes(@PathVariable("cid") Long cid) {
		Category category = new Category();
		category.setCId(cid);
		return new ResponseEntity<>(quizService.getActiveQuizzesOfCategory(category), HttpStatus.OK);
	}
}
