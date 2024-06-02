package com.examserver.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "category")
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -206242140695919381L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cId;

	private String title;

	private String description;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private Set<Quiz> quizzes = new LinkedHashSet<>();

}