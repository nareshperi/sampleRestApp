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
import org.springframework.stereotype.Service;

import com.webservice.crest.models.Role;
import com.webservice.crest.models.User;
import com.webservice.crest.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {    
    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> getUser(long id) {
        Optional<User> optUser = userRepo.findById(id);
        if(!optUser.isPresent()) {
            throw new EntityNotFoundException(String.format("UserId %s not found", id));
        }
        return new ResponseEntity<>(optUser.get(), HttpStatus.OK);        
    }

    public List<User> getAllUsers() {        
        return userRepo.findAll();
    }

    public List<User> getAllUsersByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepo.findAll(pageable);
        return usersPage.toList();        
    }

    public ResponseEntity<User> createUser(User user) {
        if(null == user.getRole()) {
            user.setRole(Role.USER);
        }
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);        
    }

    public ResponseEntity<?> batchCreateUsers(List<User> usersList) {
        List<CompletableFuture<Void>> futures = usersList.parallelStream()
                .map(user -> CompletableFuture.runAsync(() -> createUser(user)))
                .collect(Collectors.toList());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allFutures.get(); // Wait for all futures to complete
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
            log.error("Error while creating batchUsers", e.getMessage());
            return new ResponseEntity<>("Failed to create all users", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Batch user creation completed", HttpStatus.OK);
    }

    public ResponseEntity<?> updateUser(long id, User user) {
        User curUser = userRepo.getReferenceById(id);
        if(user.getFirstName() != null) {
            curUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            curUser.setLastName(user.getLastName());
        }
        if(user.getAge() != 0) {
            curUser.setAge(user.getAge());
        }
        if(user.getOccupation() != null) {
            curUser.setOccupation(user.getOccupation());
        }
        if(user.getRole() != null) {
            curUser.setRole(user.getRole());
        }                        
        userRepo.save(curUser);
        return new ResponseEntity<User>(curUser, HttpStatus.OK);
    }

    public void deleteUser(long id) {
        User curUser = userRepo.getReferenceById(id);
        userRepo.delete(curUser);
    }
}
