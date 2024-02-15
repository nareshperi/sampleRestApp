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
import com.webservice.crest.models.Answer;
import com.webservice.crest.models.AnswerRequest;
import com.webservice.crest.models.Question;
import com.webservice.crest.repositories.AnswerRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AnswerService {    
    @Autowired
    private AnswerRepository answerRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    public ResponseEntity<?> getAnswer(long id) {
        Optional<Answer> optAnswer = answerRepo.findById(id);
        if(!optAnswer.isPresent()) {
            throw new EntityNotFoundException(String.format("AnswerId %s not found", id));
        }
        return new ResponseEntity<>(optAnswer.get(), HttpStatus.OK);        
    }

    public List<Answer> getAllAnswersForQuestion(long questionId) {        
        return answerRepo.findByQuestionId(questionId);
    }
    /*
    public List<Answer> getAllAnswersByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Answer> AnswersPage = AnswerRepo.findAll(pageable);
        return AnswersPage.toList();        
    }*/

    public ResponseEntity<Answer> createAnswer(AnswerRequest aRequest) {
        ResponseEntity<?> userResponse = userService.getUser(aRequest.getUserId());
        User user = (userResponse != null)? (User) userResponse.getBody() : null;
        if(user == null) {
            throw new EntityNotFoundException("User - " + aRequest.getUserId());
        }
        ResponseEntity<?> questionResponse = questionService.getQuestion(aRequest.getQuestionId());
        Question question = (questionResponse != null)? (Question) questionResponse.getBody() : null;
        if(user == null) {
            throw new EntityNotFoundException("Question - " + aRequest.getQuestionId());
        }
        Answer reqAnswer = aRequest.getAnswer();
        reqAnswer.setUser(user);
        reqAnswer.setQuestion(question);
        Answer savedAns = answerRepo.save(reqAnswer);
        return new ResponseEntity<>(savedAns, HttpStatus.OK);        
    }

    /*
    public ResponseEntity<?> batchCreateAnswers(List<Answer> AnswersList) {
        List<CompletableFuture<Void>> futures = AnswersList.parallelStream()
                .map(Answer -> CompletableFuture.runAsync(() -> createAnswer(Answer)))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allFutures.get(); // Wait for all futures to complete
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
            log.error("Error while creating batchAnswers", e.getMessage());
            return new ResponseEntity<>("Failed to create all Answers", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Batch Answer creation completed", HttpStatus.OK);
    }

    public ResponseEntity<?> updateAnswer(long id, Answer Answer) {
        Answer curAnswer = AnswerRepo.getReferenceById(id);
        if(Answer.getFirstName() != null) {
            curAnswer.setFirstName(Answer.getFirstName());
        }
        if(Answer.getLastName() != null) {
            curAnswer.setLastName(Answer.getLastName());
        }
        if(Answer.getAge() != 0) {
            curAnswer.setAge(Answer.getAge());
        }
        if(Answer.getOccupation() != null) {
            curAnswer.setOccupation(Answer.getOccupation());
        }
        if(Answer.getRole() != null) {
            curAnswer.setRole(Answer.getRole());
        }                        
        AnswerRepo.save(curAnswer);
        return new ResponseEntity<Answer>(curAnswer, HttpStatus.OK);
    }

    public void deleteAnswer(long id) {
        Answer curAnswer = AnswerRepo.getReferenceById(id);
        AnswerRepo.delete(curAnswer);
    }
    */
}
