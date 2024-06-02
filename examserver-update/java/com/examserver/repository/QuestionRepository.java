package com.examserver.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.entity.Question;
import com.examserver.entity.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Set<Question> findByQuiz(Quiz quiz);
}
