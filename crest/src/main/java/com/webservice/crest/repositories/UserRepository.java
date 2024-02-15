package com.webservice.crest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webservice.crest.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    /*
    @Query("select u from User u where u.lastName = ?1")
    List<User> findByLastname(String lastname);
    */
}
