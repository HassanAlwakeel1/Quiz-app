package com.example.Quizapp.services.impl;

import com.example.Quizapp.entities.Question;
import com.example.Quizapp.repository.QuestionRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl {

    @Autowired
    QuestionRepository questionRepository;

    /*
    * Try and Catch and try here :
    * If a execption happend it will print the stack trace in the console and
    * retrun an empty array list to the user
    * */

    //We should make all the endpoints like this
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
        return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public List<Question> getQuestionsByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public String addQuestion(Question question) {
        questionRepository.save(question);
        return "Question added successfully";
    }

    public String updateQuestion(Question question){
        Optional<Question> questionOptional=questionRepository.findById(question.getId());
        if(questionOptional.isPresent()){
            questionRepository.save(question);
        }
        return "Updated Successfully";
    }
}
