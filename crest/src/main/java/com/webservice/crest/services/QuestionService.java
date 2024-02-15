package com.webservice.crest.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webservice.crest.models.Role;
import com.webservice.crest.models.User;
import com.webservice.crest.models.Question;
import com.webservice.crest.models.QuestionRequest;
import com.webservice.crest.repositories.QuestionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionService {    
    @Autowired
    private QuestionRepository QuestionRepo;

    @Autowired
    private UserService userService;

    public ResponseEntity<?> getQuestion(long id) {
        Optional<Question> optQuestion = QuestionRepo.findById(id);
        if(!optQuestion.isPresent()) {
            throw new EntityNotFoundException(String.format("QuestionId %s not found", id));
        }
        return new ResponseEntity<>(optQuestion.get(), HttpStatus.OK);        
    }

    public List<Question> getAllQuestions() {        
        return QuestionRepo.findAll();
    }
    /*
    public List<Question> getAllQuestionsByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Question> QuestionsPage = QuestionRepo.findAll(pageable);
        return QuestionsPage.toList();        
    }*/

    public ResponseEntity<Question> createQuestion(QuestionRequest qRequest) {
        ResponseEntity<?> userResponse = userService.getUser(qRequest.getUserId());
        User user = (userResponse != null)? (User) userResponse.getBody() : null;
        if(user == null) {
            throw new EntityNotFoundException("User - " + qRequest.getUserId());
        }
        Question reqQuestion = qRequest.getQuestion();
        reqQuestion.setUser(user);
        Question savedQn = QuestionRepo.save(reqQuestion);
        return new ResponseEntity<>(savedQn, HttpStatus.OK);        
    }

    /*
    public ResponseEntity<?> batchCreateQuestions(List<Question> QuestionsList) {
        List<CompletableFuture<Void>> futures = QuestionsList.parallelStream()
                .map(Question -> CompletableFuture.runAsync(() -> createQuestion(Question)))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allFutures.get(); // Wait for all futures to complete
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
            log.error("Error while creating batchQuestions", e.getMessage());
            return new ResponseEntity<>("Failed to create all Questions", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Batch Question creation completed", HttpStatus.OK);
    }

    public ResponseEntity<?> updateQuestion(long id, Question Question) {
        Question curQuestion = QuestionRepo.getReferenceById(id);
        if(Question.getFirstName() != null) {
            curQuestion.setFirstName(Question.getFirstName());
        }
        if(Question.getLastName() != null) {
            curQuestion.setLastName(Question.getLastName());
        }
        if(Question.getAge() != 0) {
            curQuestion.setAge(Question.getAge());
        }
        if(Question.getOccupation() != null) {
            curQuestion.setOccupation(Question.getOccupation());
        }
        if(Question.getRole() != null) {
            curQuestion.setRole(Question.getRole());
        }                        
        QuestionRepo.save(curQuestion);
        return new ResponseEntity<Question>(curQuestion, HttpStatus.OK);
    }

    public void deleteQuestion(long id) {
        Question curQuestion = QuestionRepo.getReferenceById(id);
        QuestionRepo.delete(curQuestion);
    }
    */
}
