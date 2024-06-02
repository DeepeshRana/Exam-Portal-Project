package com.examserver.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.dto.QuestionDetailsWithQuizIdDto;
import com.examserver.entity.Question;
import com.examserver.service.QuestionService;

/**
 * Made a Seperate Two DTOs {@link QuestionDetailsWithQuizIdDto} and
 * {@link QuestionController}
 * 
 * Made a separate QuestionMapper class to perform mapping in service class
 * impls
 * 
 * Shifted logic written in COntroller methods into service layer
 * 
 * Removed Question get() method from service layer as it was similar to
 * getQuestion() method present in service layer
 * 
 * Update is not working cause their is no api call associated with updation in
 * the front end side
 */

@RestController
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private QuestionService questionService;

	// add question
	@PostMapping("/")
	public ResponseEntity<Question> add(@RequestBody QuestionDetailsWithQuizIdDto questionDto) {
		return ResponseEntity.ok(this.questionService.addQuestion(questionDto));
	}

	// update the question
	@PutMapping("/")
	public ResponseEntity<Question> update(@RequestBody QuestionDetailsWithQuizIdDto question) {
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}

	// get all question of any quiz
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
		return ResponseEntity.ok(questionService.getQuestionsOfQuiz(qid));

	}

	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {

		return ResponseEntity.ok(questionService.getQuestionsOfQuiz(qid));
	}

	// get single question
	@GetMapping("/{quesId}")
	public Question get(@PathVariable("quesId") Long quesId) {
		return this.questionService.getQuestion(quesId);
	}

	// delete question
	@DeleteMapping("/{quesId}")
	public void delete(@PathVariable("quesId") Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}

	// eval quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<QuestionDetailsWithQuizIdDto> questions) {

		double marksGot = 0;
		Integer correctAnswers = 0;
		Integer attempted = 0;
		for (QuestionDetailsWithQuizIdDto q : questions) {
			// single questions
			Question question = this.questionService.getQuestion(q.getQuesId());
			if (question.getAnswer().equals(q.getGivenAnswer())) {
				// correct
				correctAnswers++;

				double marksSingle = Double.parseDouble(question.getQuiz().getMaxMarks()) / questions.size();
				marksGot += marksSingle;
			}

			if (q.getGivenAnswer() != null) {
				attempted++;
			}
		}

		Map<Object, Object> result = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted",
				attempted);
		return ResponseEntity.ok(result);
	}
}
