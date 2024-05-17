package com.example.Quizapp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_title")
    private String questionTitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    @Column(name = "right_answer")
    private String rightAnswer;

    @Column(name = "difficulty_level")
    private String difficultyLevel;

    private String category;
}
