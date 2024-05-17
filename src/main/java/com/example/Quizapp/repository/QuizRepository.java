package com.example.Quizapp.repository;

import com.example.Quizapp.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
