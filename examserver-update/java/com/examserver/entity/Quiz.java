package com.examserver.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Quiz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4368119164250709711L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qId;

	private String title;

	private String description;

	private String maxMarks;

	private String numberOfQuestions;

	private boolean active = false;
	// add..

	@ManyToOne(fetch = FetchType.LAZY)
	private Category category;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Question> questions = new HashSet<>();
}
