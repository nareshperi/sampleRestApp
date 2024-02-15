package com.webservice.crest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webservice.crest.models.User;
import com.webservice.crest.models.Answer;
import com.webservice.crest.models.AnswerRequest;
import com.webservice.crest.models.BatchUserRequest;
import com.webservice.crest.models.Question;
import com.webservice.crest.models.QuestionRequest;
import com.webservice.crest.services.AnswerService;
import com.webservice.crest.services.QuestionService;
import com.webservice.crest.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class APIControllers {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id) {
        return userService.getUser(id);        
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/users", params = { "page", "size"})
    public List<User> getAllUsersByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        return userService.getAllUsersByPage(page, size);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);        
    }

    @PostMapping("/users/batch")
    public ResponseEntity<?> createUsersFromList(@RequestBody BatchUserRequest batchUserRequest) {        
        return userService.batchCreateUsers(batchUserRequest.getUsers());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {                
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "Deleted...UserId-" + id;
    }

    @GetMapping("")
    public ResponseEntity<String> getWelcomeText(@RequestHeader HttpHeaders headers) {
        return new ResponseEntity<String>("Welcome", HttpStatus.OK);
    }

    @GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/questions")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionRequest qRequest) {        
        return questionService.createQuestion(qRequest);
    }


    @GetMapping("/questions/{id}/answers")
    public List<Answer> getAnswersForQuestion(@PathVariable long id) {
        return answerService.getAllAnswersForQuestion(id);
    }

    @PostMapping("/answers")
    public ResponseEntity<Answer> createAnswer(@RequestBody AnswerRequest aRequest) {        
        return answerService.createAnswer(aRequest);
    }
}
