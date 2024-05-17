package com.example.Quizapp.controller;

import com.example.Quizapp.dto.QuestionDto;
import com.example.Quizapp.dto.Response;
import com.example.Quizapp.entities.Quiz;
import com.example.Quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    //This endPoint to create a quiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    //This endPoint to get the quiz questions
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionDto>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
    //The endPoint to submit the answers of certain quiz
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }


}
