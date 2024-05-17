package com.example.Quizapp.services;

import com.example.Quizapp.dto.QuestionDto;
import com.example.Quizapp.dto.Response;
import com.example.Quizapp.entities.Question;
import com.example.Quizapp.entities.Quiz;
import com.example.Quizapp.repository.QuestionRepository;
import com.example.Quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        /*Here to set the questions we should first bring it from the DB
        and use it in our method
        * */
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Quiz created Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionDto>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionDto> questionDtos = new ArrayList<>();

        for(Question q : questionsFromDB){
            QuestionDto questionDto = new QuestionDto(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionDtos.add(questionDto);
        }

        return new ResponseEntity<>(questionDtos,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0 ;
        /*
        * quesions.get(i) here to fetch the number i question to compare its right answer with the one comming from the user
        * */
        for(Response response :responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }

    public String updateQuizQuestions(Quiz updatedQuiz){
        Optional<Quiz> oldQuiz = quizRepository.findById(updatedQuiz.getId());
        if(oldQuiz.isPresent()){
            Quiz newQuiz = oldQuiz.get();
            newQuiz.setTitle(updatedQuiz.getTitle());
            newQuiz.setQuestions(updatedQuiz.getQuestions());
            quizRepository.save(newQuiz);
        }

        return "Quiz updated Successfully";
    }
}
