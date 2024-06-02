package com.examserver.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
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
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2799965674258099612L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quesId;
	@Column(length = 5000)
	private String content;

	private String image;

	private String option1;
	private String option2;
	private String option3;
	private String option4;

	private String answer;

	@Transient
	private String givenAnswer;

	@ManyToOne(fetch = FetchType.LAZY)
	private Quiz quiz;

}
