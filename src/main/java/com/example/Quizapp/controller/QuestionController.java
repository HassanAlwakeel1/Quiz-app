package com.example.Quizapp.controller;

import com.example.Quizapp.entities.Question;
import com.example.Quizapp.services.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/question")
public class QuestionController {

    @Autowired
    QuestionServiceImpl questionServiceImpl;

    //This endPoint to return all the questions that I have in DB
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionServiceImpl.getAllQuestions();
    }

    //This endPoint to return all the questions based on certain category
    @GetMapping("category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
        return questionServiceImpl.getQuestionsByCategory(category);
    }

    //This endPoint to add a question to the DB
    @PostMapping("addQuestion")
    public String addQuestion(@RequestBody Question question){
        return questionServiceImpl.addQuestion(question);
    }

    @PostMapping("updateQuestion")
    public String updateQuestion(@RequestBody Question question){
        return questionServiceImpl.updateQuestion(question);
    }
}
